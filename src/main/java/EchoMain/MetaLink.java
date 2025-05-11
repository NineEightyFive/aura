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
            if(metaChangesToMake.containsKey(type)){
                return metaChangesToMake.get(type);
            }
            try {
                return AudioFileIO.read(reference.getFile()).getTag().getFirst(FieldKey.valueOf(type));
            } catch(Exception e) {
                return null;
            }
	}

	public void applyMeta(String type, String value) {
		try {
                        if(value==null || value.equals("<clear>")) value="";
                        else if(value.equals("") || value.equals(" ")|| value.equals("<keep>")) {
                            return;
                        }
			metaChangesToMake.put(type, value);
	} catch(Exception e) { e.printStackTrace();
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
		metaChangesToMake =  new HashMap<String, String>();
	}

	public void applyData(File src, File target) {
		try {
			System.out.println("A");
			AudioFile sourceFileIO = AudioFileIO.read(src);
			AudioFile targetFileIO = AudioFileIO.read(target);

			Tag sourceTag = sourceFileIO.getTag();
			Tag targetTag = targetFileIO.getTag();

			for(Map.Entry<String,String> entry : metaChangesToMake.entrySet()) {

				try {
					targetTag.setField(FieldKey.valueOf(entry.getKey()), entry.getValue());
				} catch (Exception eee) { // If for any reason a value is no compatable, it will skip it upon error
					System.out.println(eee);
				}
				//}
			}

			AudioFileIO.write(targetFileIO);

	} catch(Exception e) {
	e.printStackTrace();
	}
		}
		
	}
