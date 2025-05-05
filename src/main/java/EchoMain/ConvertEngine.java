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

	public static String getOldFormat(EMAudioFile src) {
		String original = src.getFile().getName();
		String baseN = original.contains(".") ? original.substring(0,original.lastIndexOf(".")) : original;
		return baseN;
	}

	public static String newFileName(File f, EMAudioFile src) {
		String original = f.getName();
		String baseN = original.contains(".") ? original.substring(0,original.lastIndexOf(".")) : original;
		return baseN + "." + (src.getNewFormat() != null ? src.getNewFormat() : "mp3"); // If no new format is selected, default to mp3
	}

	public static File getTargetSrc(File sourceFile, EMAudioFile src) {
		File toReturn = new File(sourceFile.getPath());
		Path toParent = toReturn.toPath().getParent();

		String newFile = newFileName(sourceFile,src);
		Path targetPath = toParent.resolve(newFile);
		return targetPath.toFile();
	}
	
	public static void convert(ArrayList<EMAudioFile> files) {

		int filesDone = 0;

		for(EMAudioFile af : files) {

			try {

				

				File source = new File(af.getPath());		                 
				File target = getTargetSrc(source,af);     
				//(Paths.get(af.getPath()).getParent())                    
										
				//System.out.println(source.toString());
				//System.out.println(target.toString());

				if(!getOldFormat(af).equals(af.getNewFormat())) {

				//Audio Attributes                                       
				AudioAttributes audio = new AudioAttributes();              
				/*audio.setCodec(AudioAttributes.DIRECT_STREAM_COPY);
				audio.setBitRate(audio.getBitRate().orElse(null));                                   
				audio.setChannels(audio.getChannels().orElse(null));                                       
				audio.setSamplingRate(audio.getSamplingRate().orElse(null));*/                               
				
                                
				//Encoding attributes                                       
				EncodingAttributes attrs = new EncodingAttributes();        
				attrs.setOutputFormat(af.getNewFormat());                                     
				attrs.setAudioAttributes(audio);
				attrs.setVideoAttributes(null);                          
																			
				//Encode                                                    
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
			
		}
		UI.sendNotification("gen", "Processed "+filesDone+"/"+files.size()+" files!\nErrors: "+(files.size()-filesDone));
	}

}

