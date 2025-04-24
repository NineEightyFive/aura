import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;

public class AudioFile {
	
	String format;
	MetaLink metaData;
	String newFormat;
	String path;
	
	public String getSingleMetadata(String key) {
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
}
