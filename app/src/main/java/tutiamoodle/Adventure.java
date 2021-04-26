package tutiamoodle;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Adventure extends JFrame implements ActionListener {
    private final ObjectMapper objectMapper;
    private final JButton startButton;
    private final SelectorPanel heroSelectorPanel;
    private final SelectorPanel mapSelectorPanel;
    private final SelectorPanel enemySelectorPanel;

    public Adventure(ObjectMapper objectMapper) {
        super("Fapados RPG");
        this.objectMapper = objectMapper;

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        panel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));

        JPanel titleNamePanel = new JPanel();
        Font titleFont = new Font("Courier New", Font.BOLD, 80);
        JLabel titleNameLabel = new JLabel("Fapados RPG");
        titleNameLabel.setForeground(Color.DARK_GRAY);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);

        heroSelectorPanel = new SelectorPanel("Hero");
        mapSelectorPanel = new SelectorPanel("Map");
        enemySelectorPanel = new SelectorPanel("Enemy");

        JPanel startButtonPanel = new JPanel();
        startButton = new JButton("START");
        startButtonPanel.add(startButton);

        panel.add(titleNamePanel);
        panel.add(heroSelectorPanel);
        panel.add(mapSelectorPanel);
        panel.add(enemySelectorPanel);
        panel.add(startButtonPanel);
        add(panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {

        }
    }
}
