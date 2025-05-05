/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Dan Bennett-Jeffreys
 */
public class FileListPanel extends JPanel{
    private JList<String> fileList;
    private DefaultListModel<String> fileListModel;
    public FileListPanel(){
        fileListModel = new DefaultListModel<>();
        fileListModel.addElement("abc");
        fileListModel.addElement("def");
        fileListModel.addElement("ghijkl");
        fileListModel.addElement("mnopqrstuvwx");
        fileList = new JList<>(fileListModel);
        fileList.addListSelectionListener(new FileListSelectionListener());
        this.add(fileList);
    }
    
    public class FileListSelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
