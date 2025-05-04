package EchoMain;

import java.util.Map;
import java.util.HashMap;

import ws.schild.jave.info.MultimediaInfo;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.FieldKey;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("unused")
public class MetaLink {
	
	//private MultimediaInfo reference;
	private EMAudioFile reference;
	private Map<String,String> metaChangesToMake;
	
	public String getMeta(String type) {
try {
		return AudioFileIO.read(reference.getFile()).getTag().getFirst(FieldKey.valueOf(type));
} catch(Exception e) {
	return null;
}
	}

	public void applyMeta(String type, String value) {
		try {



			AudioFileIO.read(reference.getFile()).getTag().setField(FieldKey.valueOf(type),value);
	} catch(Exception e) {
	}
		}

	public String checkForChangedValue(String key) {

		for(Map.Entry<String,String> entry : metaChangesToMake.entrySet()) {
			if(entry.getKey().equals(key)) return entry.getValue();
		}

		return null;

	}

	public MetaLink(EMAudioFile afile) {
		reference = afile;
		metaChangesToMake =  new HashMap<String, String>()
	}

	public static void applyData(File src, File target) {
		try {

			AudioFile sourceFileIO = AudioFileIO.read(src);
			AudioFile targetFileIO = AudioFileIO.read(target);

			Tag sourceTag = sourceFileIO.getTag();
			Tag targetTag = targetFileIO.getTag();
			for (FieldKey fieldKey : FieldKey.values()) {
                if (sourceTag.hasField(fieldKey)) {
                    targetTag.setField(fieldKey, sourceTag.getValue(fieldKey, 0));
                }
            }

			for () {

			}

			AudioFileIO.write(targetFileIO);

	} catch(Exception e) {
	
	}
		}
		
	}
