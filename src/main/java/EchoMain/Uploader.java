//java swing filechooser
package EchoMain;

import UI.MetadataPanel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

import java.util.Arrays;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Uploader {
	String type;
	

public static final String[] allowedTypes = {"wav","flac","mp3","m4a","mp4","mkv","aiff","aac","ogg","wma","mpeg","x-flac"};

public static boolean isValidFormat(String ext) {
	for(int i=0; i<allowedTypes.length;i++) {
		//System.out.println(ext+" audio/"+allowedTypes[i]);
		if (ext.equals(allowedTypes[i])) return true;
	}
	return false; // Not an audio file
}

public static boolean isAudioFile(File file) {
	
	try {
	String ext = Files.probeContentType(Paths.get(file.getPath()));
	if(ext==null) return false;
		for(int i=0; i<allowedTypes.length;i++) {
			//System.out.println(ext+" audio/"+allowedTypes[i]);
			if (ext.toLowerCase().equals("audio/"+allowedTypes[i])) return true;
		}
		return false; // Not an audio file
} catch(IOException e) {
	}

	return false;
}

/**
 * 
 * @param mode
 * @throws IOException
 */
	public static void openFileBrowser(String mode) throws IOException {
		if(mode.equals("FILE")) {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		 int returnVal = fc.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                //System.out.println("Opening: " + file.getName());


				if(isAudioFile(file)) {
					UI.doUpload(file);

				} else {
					UI.sendNotification("err", "User Provided an invalid file type!");
				}

            } else {
                System.out.println("Open command cancelled by user.");
            }
		} else if(mode.equals("FOLDER")) {


				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				 int returnVal = fc.showOpenDialog(null);
		
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						
						File file = fc.getSelectedFile();

							File[] dirListing = file.listFiles();
							if(dirListing.length == 0) System.out.println("Empty Directory!");
							for(int i=0; i<dirListing.length; i++) {
								if(dirListing[i].isFile() && isAudioFile(dirListing[i])) UI.doUpload(dirListing[i]);
							};
						//}
		
					} else {
						System.out.println("Open command cancelled by user."); 
					} 
				}
	}
	
	public EMAudioFile[] convertFolderToArray(String path) {
		return null;
	}

public static void main(String[] args) throws Exception {
		System.out.println("I will open the file chooser");
            openFileBrowser("FOLDER");   
	}

}
