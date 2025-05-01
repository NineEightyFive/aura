import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("unused")
public class MetaLink {
	
	private AudioFile reference;
	
	public static String getMeta(String type) {
		Map<String,String> allMeta = reference.getMMInfo().getMetadata();
		if(!allMeta.containsKey(type)) return allMeta.get(type);
		return null;
	}

	public static void applyMeta(String type, String value) {
		Map<String,String> allMeta = reference.getMMInfo().getMetadata();
		allMeta.put(type, value);
	}
	
}
