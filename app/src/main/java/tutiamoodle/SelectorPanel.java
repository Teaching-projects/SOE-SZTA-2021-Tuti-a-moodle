package tutiamoodle;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SelectorPanel extends JPanel implements ActionListener {
    private final JFileChooser jfc;
    private final JButton openButton;
    private final JTextField textField;
    private File file;

    public SelectorPanel(String label) {
        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Open File");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "json");
        jfc.addChoosableFileFilter(filter);

        textField = new JTextField();
        textField.setColumns(50);
        textField.setEditable(false);

        openButton = new JButton("Choose " + label);
        openButton.addActionListener(this);

        add(new JLabel(label + ":"));
        add(textField);
        add(openButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                file = new File(jfc.getSelectedFile().getAbsolutePath());
                textField.setText(jfc.getSelectedFile().getName());
            } else {
                textField.setText("Open command cancelled by user.");
                file = null;
            }
        }
    }

    public File getFile() {
        return file;
    }
}
