package EchoMain;

import java.util.Map;

import ws.schild.jave.info.MultimediaInfo;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.FieldKey;

@SuppressWarnings("unused")
public class MetaLink {
	
	//private MultimediaInfo reference;
	private EMAudioFile reference;
	
	public String getMeta(String type) {
try {
		return AudioFileIO.read(reference.getFile()).getTag().getFirst(FieldKey.valueOf(type));
} catch(Exception e) {
	return null;
}
	}

	public void applyMeta(String type, String value) {
		try {
			AudioFileIO.read(reference.getFile()).getTag().setField(FieldKey.valueOf(type),type,value);
	} catch(Exception e) {
	}
		}

	public MetaLink(EMAudioFile afile) {
		reference = afile;
	}
	
}
