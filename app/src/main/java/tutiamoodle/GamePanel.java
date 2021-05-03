package tutiamoodle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
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
    private final GridPanel gridPanel;
    private ImageIcon imageIcon;
    private int row;
    private int col;

    public GamePanel(Hero player, Entity entity, MapCell[] map) {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));

        this.player = player;
        this.entity = entity;
        this.map = map;

        laodImage();

        gridPanel = new GridPanel();
        col = 2;
        row = 2;

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
        mainPanel.add(map());
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

        gridPanel.getRoomPanel(row, col).setImage(true);
    }

    private JPanel map() {
        for (MapCell mc: map) {
            if (mc.getType() == MapCell.Type.MONSTER) {
                try {
                    Entity e = (Entity) entity.clone();
                    gridPanel.addRoomPanel(new RoomPanel(mc, e, new JLabel(imageIcon)));
                } catch (CloneNotSupportedException cloneNotSupportedException) {
                    cloneNotSupportedException.printStackTrace();
                }
            } else {
                gridPanel.addRoomPanel(new RoomPanel(mc, null, new JLabel(imageIcon)));
            }
        }
        return gridPanel.createGridPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == choice1) {
            moveUP(row, col);
        }
        if (e.getSource() == choice2) {
            moveDown(row, col);
        }
        if (e.getSource() == choice3) {
            moveRight(row, col);
        }
        if (e.getSource() == choice4) {
            moveLeft(row, col);
        }
    }

    private void moveRight(int row, int col) {
        RoomPanel rp = gridPanel.getRoomPanel(row, col);
        if (rp.getBorderEast() == 0) {
            col++;
            rp.setImage(false);
            gridPanel.getRoomPanel(row, col).setImage(true);
        }
        revalidate();
        repaint();
    }

    private void moveDown(int row, int col) {
        RoomPanel rp = gridPanel.getRoomPanel(row, col);
        if (rp.getBorderSouth() == 0) {
            row++;
            rp.setImage(false);
            gridPanel.getRoomPanel(row, col).setImage(true);
        }
        revalidate();
        repaint();
    }

    private void moveLeft(int row, int col) {
        RoomPanel rp = gridPanel.getRoomPanel(row, col);
        if (rp.getBorderWest() == 0) {
            col--;
            rp.setImage(false);
            gridPanel.getRoomPanel(row, col).setImage(true);
        }
        revalidate();
        repaint();
    }

    private void moveUP(int row, int col) {
        if (gridPanel.getRoomPanel(row, col).getBorderNorth() == 0) {
            row--;
            gridPanel.getRoomPanel(row, col).setImage(false);
            gridPanel.getRoomPanel(row, col).setImage(true);
        }
        revalidate();
        repaint();
    }

    public void laodImage() {
        try {
            BufferedImage myPicture = ImageIO.read(readImage());
            Image scaledImage = myPicture.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Oops, something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private InputStream readImage() {
        return getClass().getClassLoader().getResourceAsStream("img/vs.png");
    }

}
