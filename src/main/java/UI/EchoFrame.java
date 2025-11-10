/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.HashSet;


/**
 *
 * @author Dan Bennett-Jeffreys
 */
@SuppressWarnings("unused")
public class EchoFrame extends JFrame{
    private PrimaryButtonPanel buttonPanel;
    private MetadataPanel mdPanel;
    private FileListPanel listPanel;
    
    public EchoFrame(){ //Default Audio Tags, for use with JAudioTagger
        String[] metadataKeys = {
            "TITLE",
            "ARTIST",
            "ALBUM_ARTIST",
            "COMPOSER",
            "ALBUM",
            "TRACK",
            "DISC_NO",
        };
        
        // Adds metadataKeys dropdowns
        HashSet<String> mandatoryKeys = new HashSet<>();
        mandatoryKeys.addAll(Arrays.asList(metadataKeys));
        
        
        this.setLayout(new BorderLayout());
        //this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        buttonPanel = new PrimaryButtonPanel();
        mdPanel = new MetadataPanel(mandatoryKeys);
        
        // File List Panel
        listPanel = new FileListPanel();
        AuraMain.UI.echoFrame = this;
        AuraMain.UI.filePanel = listPanel;
        AuraMain.UI.metadataPanel = mdPanel;
        AuraMain.UI.buttonPanel = buttonPanel;
        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.add(mdPanel, BorderLayout.CENTER);
        this.add(listPanel, BorderLayout.LINE_START);
    }
}
