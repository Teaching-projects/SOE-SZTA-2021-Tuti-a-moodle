import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;

import javax.imageio.ImageIO;

public class BattlePanel extends JPanel implements ActionListener {

    private final JButton button;
    private final JTextArea textArea;
    private final EntityPanel one;
    private final EntityPanel two;

    public BattlePanel(EntityPanel one, EntityPanel two) {
        super(new BorderLayout());
        this.one = one;
        this.two = two;
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);

        try {
            BufferedImage myPicture = ImageIO.read(new File("./img/vs.png"));
            Image scaledImage = myPicture.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            add(new JLabel(new ImageIcon(scaledImage)), BorderLayout.PAGE_START);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Oops, something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        button = new JButton("Fight!");
        button.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != button) {
            return;
        }

        textArea.setText("");
        if (one.getEntity() == null || two.getEntity() == null) {
            return;
        }

        var printer = new PrintStream(new TextAreaOutputStream(textArea));
        var battleResult = new Battle(printer).battle(one.getEntity(), two.getEntity());
        if (!battleResult.isUnbalanced()) {
            one.refreshTextArea();
            two.refreshTextArea();
            return;
        }

        var winner = battleResult.getWinner();
        if (winner == null) {
            printer.println("There wouldn't be any damage in the combat!");
            return;
        }

        printer.println("Unbalanced combat! " + winner.getName() + " won!");
        one.refreshTextArea();
        two.refreshTextArea();
    }
}
