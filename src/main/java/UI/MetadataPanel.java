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
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author Dan Bennett-Jeffreys
 */
@SuppressWarnings("unused")
public final class MetadataPanel extends JPanel{
    /*private String name;
    private JTextField label;
    private JComboBox metadata;*/
    private final HashSet<String> mandatoryKeyset;
    private HashSet<String> keys;
    private HashMap<String,MDSingleKeyPanel> keyPanels;
    private HashMap<String,ArrayList<String>> inputValues;
    private HashMap<String,String> outputValues;

    public HashSet<String> getKeyset(){
        return keys;
    }
    
    public void setKeyset(HashSet<String> newKeys){
        newKeys.addAll(mandatoryKeyset);
        keys.addAll(newKeys);
        for(String singleKey: keys){
            MDSingleKeyPanel panel = keyPanels.get(singleKey);
            if(newKeys.contains(singleKey)){
                if(panel == null){
                    panel = new MDSingleKeyPanel(singleKey);
                    this.add(panel);
                    keyPanels.put(singleKey, panel);
                    //inputValues.put(singleKey, new ArrayList<>());
                    outputValues.put(singleKey, panel.getOutputValue());
                }
            }else{
                if(panel != null){
                    this.remove(panel);
                    keyPanels.remove(singleKey);
                    inputValues.remove(singleKey);
                    outputValues.remove(singleKey);
                }
            }
        }
        this.revalidate();
        this.repaint();
        keys = newKeys;
    }
    
    public HashMap<String,ArrayList<String>> getInputValues(){
        return inputValues;
    }
    
    public void setInputValues(HashMap<String,ArrayList<String>> newInputValues){
        inputValues = newInputValues;
        for (Map.Entry<String,MDSingleKeyPanel> entry : keyPanels.entrySet()){
            entry.getValue().setInputValues(inputValues.get(entry.getKey()));
        }
    }
    
    public HashMap<String,String> getOutputValues(){
        return outputValues;
    }
    
    private void metadataChangedByUI(String key, String value){
        outputValues.put(key, value);
        System.out.println("metadata changed: key=\""+key+"\" value=\"" + value+"\"");
    }
    
    public MetadataPanel(HashSet<String> keyset){
        this.mandatoryKeyset = keyset;
        keys = new HashSet<>();
        keyPanels = new HashMap<>();
        inputValues = new HashMap<>();
        outputValues = new HashMap<>();
        setKeyset(new HashSet());
    }
    
    /*public MetadataPanel(String[] keys, String[] defaults){
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
            // ** Dan see MetaLink.changemeta(key,val) -o
        }
    }*/
    
        public static void doesThisExist(){
        System.out.println("The MetadataPanel class exists");
    }
    
    private class MDSingleKeyPanel extends JPanel{
        private static final String[] defaultChoices = {"<keep>", "<clear>"};
        private final JLabel label;
        private final JComboBox valueBox;
        public MDSingleKeyPanel(String key){
            this.setLayout(new FlowLayout());
            label = new JLabel(key);
            valueBox = new JComboBox(defaultChoices);
            valueBox.setEditable(true);
            valueBox.addActionListener(new MetadataComboBoxListener(key));
            this.add(label);
            this.add(valueBox);
        }
        
        public void setInputValues(ArrayList<String> inputValues){
            valueBox.removeAllItems();
            for(String value: defaultChoices){
                valueBox.addItem(value);
            }
            if(inputValues != null){
                for(String value: inputValues){
                    valueBox.addItem(value);
                }
            }
        }
        
        public String getOutputValue(){
            return (String)valueBox.getSelectedItem();
        }
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
