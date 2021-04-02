import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EntityPanel extends JPanel implements ActionListener {
    static private final String newline = "\n";
    JButton openButton;
    JTextArea textArea;
    JFileChooser jfc;
    Entity entity;

    public EntityPanel() {
        super(new BorderLayout());

        textArea = new JTextArea(5, 20);
        textArea.setMargin(new Insets(5, 5, 5, 5));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select an entity");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "json");
        jfc.addChoosableFileFilter(filter);

        openButton = new JButton("Choose entity");
        openButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);

        add(buttonPanel, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                try {
                    var objectMapper = new ObjectMapper();
                    entity = objectMapper.readValue(new File(jfc.getSelectedFile().getAbsolutePath()), Entity.class);
                    textArea.setText("");
                    textArea.append(entity + "." + newline);
                } catch (Exception ex) {
                    // TODO: handle exception
                }
            } else {
                textArea.append("Open command cancelled by user." + newline);
            }
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }

    public Entity getEntity() {
        return entity;
    }
}