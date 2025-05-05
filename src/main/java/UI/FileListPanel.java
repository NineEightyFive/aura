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
            int[] indices = fileList.getSelectedIndices();
            for(int i: indices){
                //EMAudioFile file = EchoMain.UI.files.get(i);
                //file.
            }
            
            
            /*List<String> selections = fileList.getSelectedValuesList();
            ArrayList<String> list = new ArrayList<>();
            //list.*/
        }
        
    }
    
    public class FileListDataListener implements ListDataListener{

        @Override
        public void intervalAdded(ListDataEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void intervalRemoved(ListDataEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void contentsChanged(ListDataEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
    }
}
