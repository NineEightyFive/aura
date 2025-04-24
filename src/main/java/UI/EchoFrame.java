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
        String[] defaultOptions = {
            "<keep>",
            "<clear>"
        };
        JTextField authorLabel = new JTextField("Author");
        authorLabel.setEditable(false);
        JComboBox authorField = new JComboBox(defaultOptions);
        authorField.setEditable(true);
        this.setLayout(new GridBagLayout());
        this.add(authorLabel);
        this.add(authorField);
    }
}
