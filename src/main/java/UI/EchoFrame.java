/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;


/**
 *
 * @author Dan Bennett-Jeffreys
 */
@SuppressWarnings("unused")
public class EchoFrame extends JFrame{
    public MetadataPanel mdPanel;
    
    public EchoFrame(){
        String[] metadataKeys = {
            "title",
            "artist",
            "album_artist",
            "composer",
            "album",
            "track_number",
            "disc_number",

        };
        
        HashSet<String> mandatoryKeys = new HashSet<>();
        mandatoryKeys.addAll(Arrays.asList(metadataKeys));
        
        
        this.setLayout(new BorderLayout());
        PrimaryButtonPanel buttonPanel = new PrimaryButtonPanel();
        mdPanel = new MetadataPanel(mandatoryKeys);
        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.add(mdPanel, BorderLayout.CENTER);
        
    }
}
