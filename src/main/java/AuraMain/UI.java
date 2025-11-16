package AuraMain;

import UI.AuraFrame;
import UI.FileListPanel;
import UI.MetadataPanel;
import UI.PrimaryButtonPanel;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.swing.*;

import UI.ProgressFrame;

@SuppressWarnings("unused")
public class UI {
    private static JFrame mainFrame;
        public static AuraFrame echoFrame;
	    public static FileListPanel filePanel;
        public static MetadataPanel metadataPanel;
        public static PrimaryButtonPanel buttonPanel;
	public static ArrayList<EMAudioFile> files = new ArrayList<>();
	
    /**
     * 
     * @param type Type of message to send (Error - err, Information - gen, Warning - war)
     * @param msg The message to be displayed to the user
     */
	public static void sendNotification(String type, String msg) {
		String title;
                int messageType;
            switch (type) {
                case "err" -> {
                    System.out.println("ERROR: "+msg);
                    title = "Error";
                    messageType = JOptionPane.ERROR_MESSAGE;
                }
                case "gen" -> {
                    System.out.println("INFORMATION: "+msg);
                    title = "Information";
                    messageType = JOptionPane.INFORMATION_MESSAGE;
                }
                case "war" -> {
                    System.out.println("WARNING: "+msg);
                    title = "Warning";
                    messageType = JOptionPane.WARNING_MESSAGE;
                }
                default -> {
                    System.out.println("Unknown Message Type: "+type);
                    title = "Unknown";
                    messageType = JOptionPane.WARNING_MESSAGE;
                }
            }
                JOptionPane.showMessageDialog(echoFrame, msg, title, messageType);
	}
	
    public static int sendOptionsDialog(String title, String msg, String c1, String c2) {
        return JOptionPane.showConfirmDialog(echoFrame, msg, title, JOptionPane.YES_NO_CANCEL_OPTION);
    }

    /**
     * Executes the converter engine to start the conversion process
     * @param filesToConvert Full list of files to run conversion on
     */
	public static void doConvert(ArrayList<EMAudioFile> filesToConvert) {
		if(filesToConvert.isEmpty()) {
			UI.sendNotification("err", "Converter Failed As There Are No Files To Convert"); return; }
		System.out.println("-- STARTING CONVERSION PROCESS --");
		//ConvertEngine.convert(filesToConvert);
        ProgressFrame progressFrame = new ProgressFrame(mainFrame);
        ConverterThread worker = new ConverterThread(filesToConvert, progressFrame.getProgressBar(), progressFrame.getCancel());
        worker.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName()) && evt.getNewValue() == SwingWorker.StateValue.DONE) {
                progressFrame.closeDialog();
            }
        });
        worker.execute();

        progressFrame.setVisible(true);




	}

    public static void setMainFrame(JFrame frame) {
        mainFrame = frame;
    }
	
	public static void doUpload(File f) {

		try {
                EMAudioFile emf = new EMAudioFile(f.getPath());
		files.add(emf);
                filePanel.addToList(emf.getFileName());
                refreshSelection();
		UI.sendNotification("gen", "File "+f.getName()+" Added To List");
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public static void wipeAllFiles() {
			files.clear();
                        filePanel.clearList();
                        refreshSelection();
			UI.sendNotification("gen", "The list of all files have been cleared");
	}

        public static void removeSelectedFiles() {
            int[]  indices = filePanel.getSelectedIndices();
            Arrays.sort(indices);
            for(int j = indices.length-1; j>=0; j--){
                int i = indices[j];
                files.remove(i);
                filePanel.removeAt(i);
            }
            refreshSelection();
        }
        
        public static void setSingleMetadata(String key, String value){
            System.out.println("Setting metadata for selected files");
            //HashMap<String, String> desiredOutput = metadataPanel.getOutputValues();
            int[] indices = filePanel.getSelectedIndices();
            for(int i: indices){
                EMAudioFile emf = files.get(i);
                System.out.println("File: "+ emf.getFileName());
                emf.getMetaLink().applyMeta(key, value);
            }
        }

        public static int[] getPanelIndices() {
            return filePanel.getSelectedIndices();
        }
        
		static String[] metadataKeys = {
            "TITLE",
            "ARTIST",
            "ALBUM_ARTIST",
            "COMPOSER",
            "ALBUM",
            "TRACK",
            "DISC_NO",
        };
		
        public static void refreshSelection(){
            System.out.println("Refreshing Selected Files");
            int[] indices = filePanel.getSelectedIndices();
            HashSet<String> keyset = new HashSet<>();
            HashSet<String> formatset = new HashSet<>();
            HashMap<String, ArrayList<String>> inputValues = new HashMap<>();
            for(int i: indices){
                EMAudioFile emf = files.get(i);
                System.out.println("File: "+emf.getFileName());
                MetaLink mmi = emf.getMetaLink();
                Map<String,String> metadata = new HashMap<>();

				for(int j=0; j<metadataKeys.length; j++) {

					metadata.put(metadataKeys[j],mmi.getMeta(metadataKeys[j]));
				}

				
                keyset.addAll(metadata.keySet());
                formatset.add(emf.getNewFormat());
                for(Map.Entry<String,String> entry: metadata.entrySet()){
                    inputValues.putIfAbsent(entry.getKey(), new ArrayList<String>());
                    inputValues.get(entry.getKey()).add(entry.getValue());
                }
            }
            metadataPanel.setKeyset(keyset);
            metadataPanel.setInputValues(inputValues);
            if(formatset.isEmpty()){
                //nothing to do
            }else if(formatset.size() == 1){
                buttonPanel.setFormat((String)formatset.toArray()[0]);
            }else{
                buttonPanel.setFormat("<keep>");
            }
        }
        
        public static void formatChanged(String format){
            System.out.println("Format changed to " + format);
            if(format.equals("<keep>")){
                return;
            }
            int[] indices = filePanel.getSelectedIndices();
            for(int i: indices){
                EMAudioFile emf = files.get(i);
                System.out.println("File: "+ emf.getFileName());
                emf.setNewFormat(format);
            }
        }
        
	public static ArrayList<EMAudioFile> getFilesEnqueued() {
		return files;
	}

	public static EMAudioFile checkIfIn(String va) {
		for(EMAudioFile f : files) {
			if(f.getFileName().equals(va)) return f;
		}
		return null;
	}

	public static ArrayList<EMAudioFile> getSelectedFilesInList(ArrayList<String> list) {

		ArrayList<EMAudioFile> toReturn = new ArrayList<>();

		for(String f : list) {
			if(checkIfIn(f)!=null) toReturn.add(checkIfIn(f));
		}

return toReturn;
	}
	
}
