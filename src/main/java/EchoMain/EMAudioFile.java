package EchoMain;

import java.io.File;

import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.MultimediaInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import ws.schild.jave.EncoderException;

/*
 * 
 * MultimediaObject mmObject= new MultimediaObject(myFile);
MultimediaInfo infos= mmObject.getInfo();
 */

@SuppressWarnings("unused")
public class EMAudioFile {
	
	String format;
	MetaLink metaData;
	String newFormat;
	File file;
	MultimediaObject mmObject;
	MultimediaInfo info;

	
	/**
	 * @param key
	 * @return
	 */
	public String getSingleAudioMetadata(String key) {
		if (key.equals("samplingrate")) return String.valueOf(info.getAudio().getSamplingRate());
		else if (key.equals("decoder")) return info.getAudio().getDecoder();
		else if (key.equals("bitrate")) return String.valueOf(info.getAudio().getBitRate());
		else if (key.equals("channels")) return String.valueOf(info.getAudio().getChannels());
		return null; // Would return data
	};
	
	public void setSingleAudioMetadata(String key, String value) {
		if (key.equals("samplingrate")) info.getAudio().setSamplingRate(0);
		else if (key.equals("decoder")) info.getAudio().setDecoder(value);
		else if (key.equals("bitrate")) info.getAudio().setBitRate(0);
		else if (key.equals("channels")) info.getAudio().setChannels(0);

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

	public void setNewFormat(String format) {
		newFormat = format;
	}

	public MultimediaInfo getMMInfo() {
		return info;
	}

	public File getFile() {
		return file;
	}

	public EMAudioFile(String path) throws EncoderException {
		file = new File(path);
		mmObject = new MultimediaObject(file);
		info = mmObject.getInfo();
		metaData = new MetaLink(this);
	}

}
