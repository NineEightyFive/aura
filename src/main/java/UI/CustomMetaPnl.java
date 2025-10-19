package UI;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.FieldKey;

public class CustomMetaPnl extends JFrame{
    private JComboBox<FieldKey> dataOptions;
    private JLabel fileSelected;
    private JTextField dataValue;
    private JButton applyButton;
    private JButton cancelButton;
    private JPanel metaPanel;
    private final Map<FieldKey, String> pendingChanges = new HashMap<>();



    public CustomMetaPnl() {
        setTitle("Custom Data");
        setSize(400, 300);
        setContentPane(metaPanel);
        dataOptions.setModel(new DefaultComboBoxModel<>(FieldKey.values()));
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Action Listeners
        applyButton.addActionListener(e -> {

            for(Map.Entry<FieldKey, String> entry : pendingChanges.entrySet()){
                EchoMain.UI.setSingleMetadata(entry.getKey().name(),entry.getValue());
            }

        });



        cancelButton.addActionListener(e -> { // Cancel button closes the window
            java.awt.Window window = SwingUtilities.getWindowAncestor(cancelButton);
            if (window != null) { window.dispose(); }
        });

        dataValue.addFocusListener(new FocusAdapter() {

            private FieldKey currentKey;
            private String lastValue = "";

            public void focusLost(FocusEvent e) {
                currentKey = (FieldKey) dataOptions.getSelectedItem();
                String pending = pendingChanges.getOrDefault(currentKey, "");
                String newValue = dataValue.getText().trim();
                if (currentKey != null && !newValue.equals(lastValue)) {
                    pendingChanges.put(currentKey, newValue);
                    lastValue = newValue;
                }
            }
        });

        dataOptions.addActionListener(e -> {
            if(dataOptions.hasFocus()) {
                FieldKey selectedKey = (FieldKey) dataOptions.getSelectedItem();
                String pending = pendingChanges.getOrDefault(selectedKey, "");
                dataValue.setText(pending);
            }});


        setVisible(true);
    }
}


