package EchoMain;

import UI.FileListPanel;
import UI.MetadataPanel;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import ws.schild.jave.info.MultimediaInfo;
@SuppressWarnings("unused")
public class UI {
	public static FileListPanel filePanel;
        public static MetadataPanel metadataPanel;
	public static ArrayList<EMAudioFile> files = new ArrayList<EMAudioFile>();
	
	public static void sendNotification(String type, String msg) {
		//TODO: Have UI setup for this so it actually goes to a popup...
		if(type.equals("err")) {
			System.out.println("ERROR: "+msg);
		} else if(type.equals("gen")) {
			System.out.println("INFORMATION: "+msg);
		} else if(type.equals("war")) {
			System.out.println("WARNING: "+msg);
		}
	}
	
	public static void doConvert(ArrayList<EMAudioFile> filesToConvert) {
		if(filesToConvert.size()==0) {
			UI.sendNotification("err", "Converter Failed As There Are No Files To Convert"); return; }
		System.out.println("-- STARTING CONVERSION PROCESS --");
		ConvertEngine.convert(filesToConvert);
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
System.out.println("womp womp");
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
            //HashMap<String, String> desiredOutput = metadataPanel.getOutputValues();
            int[] indices = filePanel.getSelectedIndices();
            for(int i: indices){
                EMAudioFile emf = files.get(i);
                emf.setSingleAudioMetadata(key, value);
            }
        }
        
        public static void refreshSelection(){
            int[] indices = filePanel.getSelectedIndices();
            HashSet<String> keyset = new HashSet<>();
            HashMap<String, ArrayList<String>> inputValues = new HashMap<>();
            for(int i: indices){
                EMAudioFile emf = files.get(i);
                MultimediaInfo mmi = emf.getMMInfo();
                Map<String,String> metadata = mmi.getMetadata();
                keyset.addAll(metadata.keySet());
                for(Map.Entry<String,String> entry: metadata.entrySet()){
                    inputValues.putIfAbsent(entry.getKey(), new ArrayList<String>());
                    inputValues.get(entry.getKey()).add(entry.getValue());
                }
            }
            metadataPanel.setKeyset(keyset);
            metadataPanel.setInputValues(inputValues);
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

		ArrayList<EMAudioFile> toReturn = new ArrayList<EMAudioFile>();

		for(String f : list) {
			if(checkIfIn(f)!=null) toReturn.add(checkIfIn(f));
		}

return toReturn;
	}
	
}
