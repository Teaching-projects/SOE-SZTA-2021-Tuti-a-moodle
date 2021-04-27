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
    private final Container contentPane;
    private final JPanel mainPanel;

    public Adventure(ObjectMapper objectMapper) {
        super("Fapados RPG");
        this.objectMapper = objectMapper;

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxlayout);
        mainPanel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));

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
        startButton.addActionListener(this);
        startButtonPanel.add(startButton);

        mainPanel.add(titleNamePanel);
        mainPanel.add(heroSelectorPanel);
        mainPanel.add(mapSelectorPanel);
        mainPanel.add(enemySelectorPanel);
        mainPanel.add(startButtonPanel);

        contentPane = getContentPane();
        contentPane.add(mainPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            try {
                Entity heroEntity = objectMapper.readValue(heroSelectorPanel.getFile(), Entity.class);
                Entity enemyEntity = objectMapper.readValue(enemySelectorPanel.getFile(), Entity.class);
                contentPane.add(new GamePanel((Hero) heroEntity, enemyEntity));
                mainPanel.setVisible(false);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getStackTrace(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
