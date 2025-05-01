//java swing filechooser

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
	

private static final String[] allowedTypes = {"wav","flac","mp3","m4a","mp4","mkv","aiff","aac","ogg","wma"};

private static boolean isAudioFile(File file) {
	
	try {
	String ext = Files.probeContentType(Paths.get(file.getPath()));
		for(int i=0; i<allowedTypes.length;i++) {
			//System.out.println(ext+" audio/"+allowedTypes[i]);
			if (ext.toLowerCase().equals("audio/"+allowedTypes[i])) return true;
		}
		return false; // Not an audio file
} catch(IOException e) {
	}

	return false;
}

	public static void openFileBrowser() {
		
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		 int returnVal = fc.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                //System.out.println("Opening: " + file.getName());


				if(isAudioFile(file)) {
					System.out.println("Passes the test!");
				UI.doUpload(file);

				} else {
					UI.sendNotification("err", "User Provided an invalid file type!");
				}

            } else {
                System.out.println("Open command cancelled by user.");
            }

	}
	
	public AudioFile[] convertFolderToArray(String path) {
		return null;
	}

public static void main(String[] args) {
		System.out.println("I will open the file chooser");
            openFileBrowser();   
	}

}
