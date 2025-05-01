import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@SuppressWarnings("unused")
public class UI {
	
	static ArrayList<AudioFile> files = new ArrayList<AudioFile>();
	
	public static void sendNotification(String type, String msg) {
		//TODO: Have UI setup for this so it actually goes to a popup...
		if(type.equals("err")) {
			System.out.println("ERROR: "+msg);
		} else if(type.equals("gen")) {
			System.out.println("INFORMATION: "+msg);
		} else if(type.equals("war")) {
			System.out.println("WARNING: "+msg);
		}
	}
	
	public void doConvert(ArrayList<AudioFile> filesToConvert) {
		
	}
	
	public static void doUpload(File f) {

		try {
		files.add(new AudioFile(f.getPath()));
		UI.sendNotification("gen", "File Added To List");
		} catch(Exception e) {
System.out.println("womp womp");
		}
	}
	
}
