package EchoMain;

import java.util.Map;



@SuppressWarnings("unused")
public class MetaLink {
	
	private AudioFile reference;
	
	public String getMeta(String type) {
		Map<String,String> allMeta = reference.getMMInfo().getMetadata();
		if(!allMeta.containsKey(type)) return allMeta.get(type);
		return null;
	}

	public void applyMeta(String type, String value) {
		Map<String,String> allMeta = reference.getMMInfo().getMetadata();
		allMeta.put(type, value);
	}
	
}
