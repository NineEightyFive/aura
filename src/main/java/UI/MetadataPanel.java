package UI;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
/**
 *
 * @author Dan Bennett-Jeffreys
 */
@SuppressWarnings("unused")
public final class MetadataPanel extends JPanel{
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
        EchoMain.UI.setSingleMetadata(key, value);
        System.out.println("metadata changed: key=\""+key+"\" value=\"" + value+"\"");
    }
    
    public MetadataPanel(HashSet<String> keyset){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.mandatoryKeyset = keyset;
        keys = new HashSet<>();
        keyPanels = new HashMap<>();
        inputValues = new HashMap<>();
        outputValues = new HashMap<>();
        setKeyset(new HashSet<>());
    }
    
    
        public static void doesThisExist(){
        System.out.println("The MetadataPanel class exists");
    }
    
    private class MDSingleKeyPanel extends JPanel{
        private static final String[] defaultChoices = {"<keep>", "<clear>"};
        private final JLabel label;
        private final JComboBox<String> valueBox;
        public MDSingleKeyPanel(String key){
            //this.setLayout(new FlowLayout(FlowLayout.TRAILING));
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            label = new JLabel(key);
            label.setMinimumSize(new Dimension(80,20));
            valueBox = new JComboBox<>(defaultChoices);
            valueBox.setEditable(true);
            valueBox.addActionListener(new MetadataComboBoxListener(key));
            valueBox.setMaximumSize(new Dimension(160,20));
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
                if(inputValues.size() == 1){
                    valueBox.setSelectedIndex(2);
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
            @SuppressWarnings("unchecked")
            JComboBox<String> valueBox = (JComboBox<String>)e.getSource();
            String value = (String)valueBox.getSelectedItem();
            metadataChangedByUI(key, value);
        }
    }
}
