/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
/**
 *
 * @author Dan Bennett-Jeffreys
 */
public class MetadataPanel extends JPanel{
    /*private String name;
    private JTextField label;
    private JComboBox metadata;*/
    

    
    private void metadataChangedByUI(String key, String value){
        //TODO: integrate with rest of EchoMaster
        System.out.println("metadata changed: key=\""+key+"\" value=\"" + value+"\"");
    }
    
    public MetadataPanel(String[] keys, String[] defaults){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (String key: keys){
            JPanel subPanel = new JPanel();
            subPanel.setLayout(new FlowLayout());
            JTextField label = new JTextField(key);
            label.setEditable(false);
            JComboBox valueBox = new JComboBox(defaults);
            valueBox.setEditable(true);
            valueBox.addActionListener(new MetadataComboBoxListener(key));
            subPanel.add(label);
            subPanel.add(valueBox);
            this.add(subPanel);
            //TODO: make a (key,valueBox) map so code can change displayed metadata
        }
    }
    
        public static void doesThisExist(){
        System.out.println("The MetadataPanel class exists");
    }
    
    public class MetadataComboBoxListener implements ActionListener {
        private final String key;
        
        public MetadataComboBoxListener(String name){
            key = name;
        }
        
        @Override
        public void actionPerformed(ActionEvent e){
            JComboBox valueBox = (JComboBox)e.getSource();
            String value = (String)valueBox.getSelectedItem();
            metadataChangedByUI(key, value);
        }
    }
}
