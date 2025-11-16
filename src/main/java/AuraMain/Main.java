package AuraMain;

import UI.AuraFrame;
import UI.MetadataPanel;

import javax.swing.JFrame;

@SuppressWarnings("unused")
public class Main {
    private static AuraFrame ef;
    /**
     * Initialization Function for EchoMaster, runs and sets the window up
     */
    public static void init() {
        // Run initialization of program, start UI, etc.
        ef = new AuraFrame();
        ef.setSize(550, 400);
        ef.setTitle("AURA");
        ef.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ef.setVisible(true);
        UI.setMainFrame(ef);
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