import java.io.File;

import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;


public class ConvertEngine {
	
	public AudioFile applyMetaData(AudioFile file, MetaLink data) {
		return null; // Returns audiofile with new metadata, run after file is converted.
	}
	
	public AudioFile[] convert(AudioFile[] files) {

		int filesDone = 0;

		for(int i=0; i<files.length; i++) {

			try {

				File source = new File(files[i].getPath());		                 
				File target = new File("file path");                         
																			
				//Audio Attributes                                       
				AudioAttributes audio = new AudioAttributes();              
				audio.setCodec(AudioAttributes.DIRECT_STREAM_COPY);
                                /* No idea how to quickly fix this, -Dan
				audio.setBitRate(audio.getBitRate());                                   
				audio.setChannels(audio.getChannels());                                       
				audio.setSamplingRate(audio.getSamplingRate());                               
				*/
                                
				//Encoding attributes                                       
				EncodingAttributes attrs = new EncodingAttributes();        
				attrs.setOutputFormat(files[i].getNewFormat());                                     
				attrs.setAudioAttributes(audio);
				attrs.setVideoAttributes(null);                          
																			
				//Encode                                                    
				Encoder encoder = new Encoder();                            
				encoder.encode(new MultimediaObject(source), target, attrs);

				filesDone++;
			} catch(Exception e) {
				System.err.println("Exception occured when converting file... "+e);
				/* No idea how to quickly fix this, -Dan
                                UI.sendNotification("error","Error when converting file: "+e);
                                */
			}

		}

		return null; // Would return an array of converted audiofiles
	}
}
