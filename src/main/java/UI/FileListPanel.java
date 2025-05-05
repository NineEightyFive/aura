/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import EchoMain.EMAudioFile;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Dan Bennett-Jeffreys
 */
public class FileListPanel extends JPanel{
    private JScrollPane listPane;
    private JList<String> fileList;
    private DefaultListModel<String> fileListModel;
    public FileListPanel(){
        fileListModel = new DefaultListModel<>();
        fileList = new JList<>(fileListModel);
        fileList.addListSelectionListener(new FileListSelectionListener());
        listPane = new JScrollPane(fileList);
        listPane.setPreferredSize(new Dimension(240,180));
        this.add(listPane);
    }
    
    public void addToList(String filename){
        fileListModel.addElement(filename);
    }
    
    public void clearList(){
        fileListModel.removeAllElements();
    }
    
    public int[] getSelectedIndices(){
        return fileList.getSelectedIndices();
    }
    
    public void removeAt(int i){
        fileListModel.removeElementAt(i);
    }
    
    //public HashSet<String>
    
    public class FileListSelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            EchoMain.UI.refreshSelection();
        }
        
    }
    
}
