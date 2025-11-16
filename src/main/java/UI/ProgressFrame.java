package UI;

import javax.swing.*;
import java.awt.*;

public class ProgressFrame extends JDialog{
    private final JProgressBar progress;
    private final JButton cancel;
    private final JLabel status;

    public ProgressFrame(JFrame parent) {
        super(parent,"Converting Files...", true);
        setLayout(new BorderLayout(10,10));
        setSize(400,150);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(96, 30, 193));
        setForeground(Color.white);

        status = new JLabel("Converting Files", SwingConstants.CENTER);
        progress = new JProgressBar(0, 100);
        cancel = new JButton("Cancel");

        JPanel center = new JPanel(new BorderLayout(5,5));
        center.add(status, BorderLayout.NORTH);
        center.add(progress,BorderLayout.CENTER);
        center.add(cancel,BorderLayout.SOUTH);

        add(center, BorderLayout.CENTER);
    }

    public void updateProgress(int p) {
        SwingUtilities.invokeLater(() -> progress.setValue(p));
    }

    public void updateStatus(String t) {
        SwingUtilities.invokeLater(() -> status.setText(t));
    }

    public JButton getCancel() {
        return cancel;
    }

    public JProgressBar getProgressBar() {
        return progress;
    }

    public void closeDialog() {
        SwingUtilities.invokeLater(this::dispose);
    }

}
