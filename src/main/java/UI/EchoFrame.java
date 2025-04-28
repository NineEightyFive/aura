/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
/**
 *
 * @author Dan Bennett-Jeffreys
 */
public class EchoFrame extends JFrame{
    public EchoFrame(){
        String[] metadataKeys = {
            "author",
            "title",
            "date"
        };
        String[] defaultOptions = {
            "<keep>",
            "<clear>"
        };
        
        this.setLayout(new GridBagLayout());
        MetadataPanel panel = new MetadataPanel((String[])metadataKeys, (String[])defaultOptions);
        this.add(panel);
    }
}
