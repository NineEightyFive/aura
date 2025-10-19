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
     * Initialization Function for EchoMaster, runs and sets the window up
     */
    public static void init() {
        // Run initialization of program, start UI, etc.
        EchoFrame ef = new EchoFrame();
        ef.setSize(576, 512);
        ef.setTitle("Echo Master");
        ef.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ef.setVisible(true);
        System.out.println("EchoFrame initialized: " + ef);
    }

    public static void main(String[] args) {
        System.out.println("Attempting to initialize EchoMaster...");

        try {
            // Run metadata check before UI starts
            MetadataPanel.doesThisExist();

            // Always start Swing UI on the Event Dispatch Thread
            javax.swing.SwingUtilities.invokeLater(() -> {
                try {
                    init();
                } catch (Exception e) {
                    e.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "EchoMaster failed to start:\n" + e.getMessage(),
                        "Startup Error",
                        javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            });

        } catch (Exception e) {
            // Catch any pre-UI errors
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "EchoMaster failed before UI launch:\n" + e.getMessage(),
                "Fatal Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }
}