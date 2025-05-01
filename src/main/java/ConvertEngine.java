import java.io.File;
import java.util.ArrayList;

import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ConvertEngine {
	
	public AudioFile applyMetaData(AudioFile file, MetaLink data) {
		return null; // Returns audiofile with new metadata, run after file is converted.
	}
	
	public static ArrayList<AudioFile> convert(ArrayList<AudioFile> files) {

		int filesDone = 0;

		for(AudioFile af : files) {

			try {

				File source = new File(af.getPath());		                 
				File target = new File();     
				//(Paths.get(af.getPath()).getParent())                    
																			
				//Audio Attributes                                       
				AudioAttributes audio = new AudioAttributes();              
				audio.setCodec(AudioAttributes.DIRECT_STREAM_COPY);
				audio.setBitRate(audio.getBitRate().orElse(null));                                   
				audio.setChannels(audio.getChannels().orElse(null));                                       
				audio.setSamplingRate(audio.getSamplingRate().orElse(null));                               
				
                                
				//Encoding attributes                                       
				EncodingAttributes attrs = new EncodingAttributes();        
				attrs.setOutputFormat(af.getNewFormat());                                     
				attrs.setAudioAttributes(audio);
				attrs.setVideoAttributes(null);                          
																			
				//Encode                                                    
				Encoder encoder = new Encoder();                            
				encoder.encode(new MultimediaObject(source), target, attrs);

				filesDone++;
			} catch(Exception e) {
				System.err.println("Exception occured when converting file... "+e);

                                UI.sendNotification("err","Error when converting file: "+e);

			}
			UI.sendNotification("gen", "Processed "+filesDone+"/"+files.size()+" files! Yay");
		}

		return null; // Would return an array of converted audiofiles
	}
	public static void main(String[] args) {
		System.out.println("I will test the converter");
            ArrayList<AudioFile> fil = new ArrayList<AudioFile>();
			fil.add(new AudioFile("C:/Users/Owain Lucas/Downloads/500+Chunks.mp3"));
			fil.get(0.).setSingleAudioMetadata("format","m4a");
	}

}

}
