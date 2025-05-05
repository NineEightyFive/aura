package EchoMain;

import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@SuppressWarnings("unused")
public class UI {
	
	static ArrayList<EMAudioFile> files = new ArrayList<EMAudioFile>();
	
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
	
	public static void doConvert(ArrayList<EMAudioFile> filesToConvert) {
		if(filesToConvert.size()==0) {
			UI.sendNotification("err", "Converter Failed As There Are No Files To Convert"); return; }
		System.out.println("-- STARTING CONVERSION PROCESS --");
		ConvertEngine.convert(filesToConvert);
	}
	
	public static void doUpload(File f) {

		try {
		files.add(new EMAudioFile(f.getPath()));
		UI.sendNotification("gen", "File "+f.getName()+" Added To List");
		} catch(Exception e) {
			System.out.println(e);
System.out.println("womp womp");
		}
	}

	public static void wipeAllFiles() {
			files.clear();
			UI.sendNotification("gen", "The list of all files have been cleared");
	}

	public static ArrayList<EMAudioFile> getFilesEnqueued() {
		return files;
	}

	public static EMAudioFile checkIfIn(String va) {
		for(EMAudioFile f : files) {
			if(f.getFileName().equals(va)) return f;
		}
		return null;
	}

	public static ArrayList<EMAudioFile> getSelectedFilesInList(ArrayList<String> list) {

		ArrayList<EMAudioFile> toReturn = new ArrayList<EMAudioFile>();

		for(String f : list) {
			if(checkIfIn(f)!=null) toReturn.add(checkIfIn(f));
		}

return toReturn;
	}
	
}
