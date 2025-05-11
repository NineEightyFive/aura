package EchoMain;

import java.io.File;
import java.util.ArrayList;

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

	/**
	 * Returns File Extension of current file
	 * @param src Audio File
	 * @return File Extension
	 */
	public static String getOldFormat(EMAudioFile src) {
		String original = src.getFile().getName();
		String baseN = original.contains(".") ? original.substring(0,original.lastIndexOf(".")) : original;
		return baseN;
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
		return baseN + "." + (src.getNewFormat() != null ? src.getNewFormat() : "mp3"); // If no new format is selected, default to mp3
	}

	/**
	 * Get source File of converted file
	 * @param sourceFile File object
	 * @param src AudioFile object
	 * @return Target source
	 */
	public static File getTargetSrc(File sourceFile, EMAudioFile src) {
		File toReturn = new File(sourceFile.getPath());
		Path toParent = toReturn.toPath().getParent();

		String newFile = newFileName(sourceFile,src);
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
				File target = getTargetSrc(source,af);     

				if(!getOldFormat(af).equals(af.getNewFormat())) { // Used to check if file formats are same, only metadata changes?

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
				}
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

