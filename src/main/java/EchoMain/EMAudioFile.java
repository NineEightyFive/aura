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
	 * @param key The file metadata object to pull (samplingrate, decoder, bitrate, channels)
	 * @return The value of the provided key, if any
	 */
	public String getSingleAudioMetadata(String key) {
		if (key.equals("samplingrate")) return String.valueOf(info.getAudio().getSamplingRate());
		else if (key.equals("decoder")) return info.getAudio().getDecoder();
		else if (key.equals("bitrate")) return String.valueOf(info.getAudio().getBitRate());
		else if (key.equals("channels")) return String.valueOf(info.getAudio().getChannels());
		return null; // Would return data
	};
	
	/**
	 * 
	 * @param key The file metadata object to pull (samplingrate, decoder, bitrate, channels)
	 * @param value The new value to change the data to
	 */
	public void setSingleAudioMetadata(String key, String value) {
		if (key.equals("samplingrate")) info.getAudio().setSamplingRate(0);
		else if (key.equals("decoder")) info.getAudio().setDecoder(value);
		else if (key.equals("bitrate")) info.getAudio().setBitRate(0);
		else if (key.equals("channels")) info.getAudio().setChannels(0);

	};
	
	public MetaLink getMetaLink() {
		return metaData;
	}

	/**
	 * Returns the path of the file
	 * @author Owain L.
	 * @return A string with the full file path
	 */
	public String getPath() {
		return file.getPath();
	}

	
/**
 * 
 * @return The new file format to convert the file to, default is MP3
 */
	public String getNewFormat() {
		return newFormat;
	}
	/**
	 * 
	 * @param format The new file format to convert the file to
	 */
	public void setNewFormat(String format) {
		newFormat = format;
	}

	public MultimediaInfo getMMInfo() {
		return info;
	}

	public File getFile() {
		return file;
	}

	/**
	 * Returns the name of the file
	 * @author Owain L.
	 * @return The name of the file, including its appropriate format
	 */
	public String getFileName() {
		return file.getName();
	}
	/**
	 * General constructor for a new AudioFile, added to the masterlist in the UI class
	 * @param path The string path of the file
	 * @throws EncoderException
	 */
	public EMAudioFile(String path) throws EncoderException {
		file = new File(path);
		mmObject = new MultimediaObject(file);
		info = mmObject.getInfo();
		metaData = new MetaLink(this);
	}

}
