package EchoMain;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import java.nio.file.Path;



public class ConvertEngine {
	
	public EMAudioFile applyMetaData(EMAudioFile file, MetaLink data) {
		return null; // Returns audiofile with new metadata, run after file is converted.
	}



	public static String DuplicateCheck(File f,EMAudioFile src) {
		
		if(f.getName().equals(newFileName(f, src))) {

			int overWrite = UI.sendOptionsDialog("Duplicate Found", "The File: "+f.getName()+" already exists, do you wish to overwrite it?\nAnswering 'No' will create another file\nAnswering 'cancel' will skip the file", "Yes", "No");
			
			if(overWrite==JOptionPane.NO_OPTION) { // Rename
				return "Rename";
			} else if(overWrite==JOptionPane.YES_OPTION) { return "Overwrite";
				
			} else { // Cancel Option

				return "Skip";
				
			}

		}

		return "Ignore";
	}

	/**
	 * Returns File Extension of current file
	 * @param src Audio File
	 * @return File Extension
	 */
	public static String getOldFormat(EMAudioFile src) {
		String original = src.getFile().getName();
		return original.contains(".") ? original.substring(original.lastIndexOf(".")+1,original.length()) : original;
	}
	/**
	 * Sets the path of the converted file with new file format and correct path
	 * @param f Original File
	 * @param src New File
	 * @return String path of new file with correct extension, defaults to mp3 if no option is found or invalid
	 */
	public static String newFileName(File f, EMAudioFile src, boolean doDuplicate) {
		
		String original = f.getName();
		String baseN = original.contains(".") ? original.substring(0,original.lastIndexOf(".")) : original;

		if(doDuplicate) {
			System.out.println("doDuplicate running...");
			

				File newF = new File(f.getParent(),(baseN+"."+src.getNewFormat()));
				System.out.println(baseN);
				System.out.println(newF.getName());
				while(newF.exists()) {
					Pattern pattern = Pattern.compile("\\((\\d+)\\)$");
					Matcher matcher = pattern.matcher(baseN);
					if (matcher.find()) {
						String number = matcher.group(1); // this gets the digits inside
    					int numb = Integer.parseInt(number);
						System.out.println(numb);
    					numb++;
						baseN = baseN.replaceFirst("\\(\\d+\\)$", "(" + numb + ")");
						newF = new File(f.getParent(),baseN+"."+src.getNewFormat());
						System.out.println(numb);
				} else {
					baseN=baseN+"(1)";
						newF = new File(f.getParent(),baseN+"."+src.getNewFormat());
				}
		}
	}

		return baseN + "." + (src.getNewFormat() != null ? src.getNewFormat() : getOldFormat(src)); // If no new format is selected, default to mp3
	}

	/**
	 * Sets the path of the converted file with new file format and correct path
	 * @param f Original File
	 * @param src New File
	 * @return String path of new file with correct extension, defaults to mp3 if no option is found or invalid
	 */
	public static String newFileName(File f, EMAudioFile src) {
		
		String original = f.getName();
		String baseN = original.contains(".") ? original.substring(0,original.lastIndexOf(".")) : original;
		return baseN + "." + (src.getNewFormat() != null ? src.getNewFormat() : getOldFormat(src)); // If no new format is selected, default to mp3
	}

	/**
	 * Get source File of converted file
	 * @param sourceFile File object
	 * @param src AudioFile object
	 * @return Target source
	 */
	public static File getTargetSrc(File sourceFile, EMAudioFile src,boolean duplicate) {
		File toReturn = new File(sourceFile.getPath());
		Path toParent = toReturn.toPath().getParent();

		String newFile = newFileName(sourceFile,src,duplicate);
		Path targetPath = toParent.resolve(newFile);
		return targetPath.toFile();
	}
	
	/**
	 * Master conversion function, converts all files using Encoder system and applies tags using MetaLink object
	 * @param files ArrayList of files for the system to convert
	 */
	public static void convert(ArrayList<EMAudioFile> files) {

		int filesDone = 0; // # of files converted, used to calculate amount of errors

		for(EMAudioFile af : files) {

			try {

				
				
				File source = new File(af.getPath());		                 
				File target = getTargetSrc(source,af,false);     

				//System.out.println(getOldFormat(af));
				//System.out.println(af.getNewFormat());
				//System.out.println(!getOldFormat(af).equals(af.getNewFormat()));

				if(af.getNewFormat()!=null && !getOldFormat(af).equals(af.getNewFormat())) { // Used to check if file formats are same, only metadata changes?
					//boolean DuplicateProtocol = false;

					String DC = DuplicateCheck(target,af);
					if(DC.equals("Skip")) {
						return;

					} else if(DC.equals("Rename")) {
						//DuplicateProtocol = true;
						target = getTargetSrc(source,af,true);  
					}

					//Audio Attributes
					AudioAttributes audio = new AudioAttributes();                                           
				
					//Encoding attributes                                       
					EncodingAttributes attrs = new EncodingAttributes();        
					attrs.setOutputFormat(af.getNewFormat());                                     
					attrs.setAudioAttributes(audio);
					attrs.setVideoAttributes(null);
																			
					//Encodes file                                                    
					Encoder encoder = new Encoder();                            
					encoder.encode(new MultimediaObject(source), target, attrs);
					} // End Conversion Changes
				// Metadata Changes
				System.out.println("Attempting to set metadata...");
				af.getMetaLink().applyData(source,target);
				System.out.println("Set MetaData successfully");
				filesDone++;
			} catch(EncoderException e) {

                UI.sendNotification("err","Error when converting file: "+e);

			}
			
		} // Processed #/# files!\nErrors: #
		UI.sendNotification("gen", "Processed "+filesDone+"/"+files.size()+" files!\nErrors: "+(files.size()-filesDone));
	}

}

