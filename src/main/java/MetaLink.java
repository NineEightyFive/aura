
import java.util.Map;


@SuppressWarnings("unused")
public class MetaLink {
	
	private AudioFile reference;
	
	public String getMeta(String type) {

		Map<String,String> allMeta = reference.getMMInfo().getMetadata(); // ask bro

		return null;
	}
	
	public int applyMeta(String type, int value) {
		return 0;
	}
	
	public String applyMeta(String type, String value) {
		return null;
	}
	
}
