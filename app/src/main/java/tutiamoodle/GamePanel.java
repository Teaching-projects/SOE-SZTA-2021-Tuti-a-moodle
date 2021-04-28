package tutiamoodle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GamePanel extends JPanel implements ActionListener {
    private final Hero player;
    private final Entity entity;
    private final MapCell[] map;
    private final JTextArea textArea;
    private final JLabel hpValueLabel;
    private final JLabel lvlValueLabel;
    private final JLabel atkValueLabel;
    private final JLabel xpValueLabel;
    private final JButton choice1;
    private final JButton choice2;
    private final JButton choice3;
    private final JButton choice4;

    public GamePanel(Hero player, Entity entity, MapCell[] map) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));

        this.player = player;
        this.entity = entity;
        this.map = map;

        JPanel playerPanel = new JPanel();
        playerPanel.setBackground(Color.DARK_GRAY);
        playerPanel.setLayout(new FlowLayout());
        JLabel lvlLabel = new JLabel("LVL:");
        lvlLabel.setForeground(Color.WHITE);
        Font boldFont = new Font("Courier New", Font.BOLD, 30);
        lvlLabel.setFont(boldFont);
        playerPanel.add(lvlLabel);
        lvlValueLabel = new JLabel(String.valueOf(player.getCurrentLevel()));
        lvlValueLabel.setForeground(Color.WHITE);
        Font normalFont = new Font("Courier New", Font.PLAIN, 30);
        lvlValueLabel.setFont(normalFont);
        playerPanel.add(lvlValueLabel);
        JLabel hpLabel = new JLabel("HP:");
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setFont(boldFont);
        playerPanel.add(hpLabel);
        hpValueLabel = new JLabel(String.valueOf(player.getHealth()));
        hpValueLabel.setForeground(Color.WHITE);
        hpValueLabel.setFont(normalFont);
        playerPanel.add(hpValueLabel);
        JLabel atkLabel = new JLabel("ATK:");
        atkLabel.setForeground(Color.WHITE);
        atkLabel.setFont(boldFont);
        playerPanel.add(atkLabel);
        atkValueLabel = new JLabel(String.valueOf(player.getAttack()));
        atkValueLabel.setForeground(Color.WHITE);
        atkValueLabel.setFont(normalFont);
        playerPanel.add(atkValueLabel);
        JLabel xpLabel = new JLabel("XP:");
        xpLabel.setForeground(Color.WHITE);
        xpLabel.setFont(boldFont);
        playerPanel.add(xpLabel);
        xpValueLabel = new JLabel(String.valueOf(player.getCurrentXp()));
        xpValueLabel.setForeground(Color.WHITE);
        xpValueLabel.setFont(normalFont);
        playerPanel.add(xpValueLabel);
        add(playerPanel, BorderLayout.PAGE_START);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        JLabel mapLabel = new JLabel("MAP");
        mainPanel.add(mapLabel);
        textArea = new JTextArea(10, 30);
        textArea.append("Your adventure has started!");
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane);
        add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        choice1 = new JButton("North");
        choice1.addActionListener(this);
        buttonPanel.add(choice1);
        choice2 = new JButton("South");
        choice2.addActionListener(this);
        buttonPanel.add(choice2);
        choice3 = new JButton("East");
        choice3.addActionListener(this);
        buttonPanel.add(choice3);
        choice4 = new JButton("West");
        choice4.addActionListener(this);
        buttonPanel.add(choice4);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == choice1) {
            // FIXME
        }
        if (e.getSource() == choice2) {
            // FIXME
        }
        if (e.getSource() == choice3) {
            // FIXME
        }
        if (e.getSource() == choice4) {
            // FIXME
        }
    }
}
