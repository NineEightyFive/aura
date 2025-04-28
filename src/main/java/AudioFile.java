import java.io.File;

import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.MultimediaInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/*
 * 
 * MultimediaObject mmObject= new MultimediaObject(myFile);
MultimediaInfo infos= mmObject.getInfo();
 */

@SuppressWarnings("unused")
public class AudioFile {
	
	String format;
	MetaLink metaData;
	String newFormat;
	File file;
	MultimediaObject mmObject;
	MultimediaInfo info;

	
	public String getSingleMetadata(String key) {



		if (key.equals("samplingrate")) return info.getAudio().getSamplingRate();
		if (key.equals("decoder")) return info.getAudio().getDecoder();
		if (key.equals("bitrate")) return info.getAudio().getBitRate();
		if (key.equals("channels")) return info.getAudio().getChannels();
		

		return null; // Would return data
	};
	
	public boolean setSingleMetadata(String key, String value) {
		return true; // Would return true if valid, false if invalid
	};
	
	public boolean setSingleMetadata(String key, int value) {
		return true; // Would return true if valid, false if invalid
	};
	
	public MetaLink getMetaLink() {
		return metaData;
	}

	public String getPath() {
		return file.getPath();
	}

	public String getNewFormat() {
		return newFormat;
	}

	public MultimediaInfo getMMInfo() {
		return info;
	}

	public AudioFile(String path) {
		file = new File(path);
		mmObject = new MultimediaObject(file);
		info = mmObject.getInfo();
	}

}
