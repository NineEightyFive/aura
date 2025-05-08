package EchoMain;

import UI.EchoFrame;
import UI.MetadataPanel;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.FieldKey;
import java.util.Arrays;

import java.util.Arrays;

import javax.swing.JFrame;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;

@SuppressWarnings("unused")
public class Main {
	
	/**
	 * Initalization Function for EchoMaster, runs and sets the window up
	 */
	public static void init() {
		// Run initalization of program, start UI, etc.
		// This will run when the app is started.
                EchoFrame ef = new EchoFrame();
                ef.setSize(512, 512);
                ef.setTitle("Echo Master");
                ef.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ef.setVisible(true);
                System.out.println(ef);
	}

	public static void main(String[] args) {
		System.out.println("Attempting to initialize EchoMaster...");
                MetadataPanel.doesThisExist();//Somehow it didn't exist until I did this
			//System.out.println(Arrays.toString(FieldKey.values()));
		init();
	}
}
