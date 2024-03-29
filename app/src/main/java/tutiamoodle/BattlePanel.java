package tutiamoodle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import javax.swing.*;

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
            BufferedImage myPicture = ImageIO.read(readVersusImage());
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
        if (battleResult.isBalanced()) {
            var winner = battleResult.getWinner();
            if (one.getEntity().equals(winner)) {
                popupOutcome(winner.getName() + " won!");
                one.refreshTextArea();
                two.clearPanel();
                return;
            }

            if (two.getEntity().equals(winner)) {
                popupOutcome(winner.getName() + " won!");
                two.refreshTextArea();
                one.clearPanel();
                return;
            }

            popupOutcome("Both heroes are dead!");
            one.clearPanel();
            two.clearPanel();
            return;
        }

        var winner = battleResult.getWinner();
        if (winner == null) {
            printer.println("There wouldn't be any damage in the combat!");
            popupOutcome("There wouldn't be any damage in the combat!");
            return;
        }

        printer.println("Unbalanced combat! " + winner.getName() + " won!");
        popupOutcome("Unbalanced combat! " + winner.getName() + " won!");
        if (one.getEntity().equals(winner)) {
            one.refreshTextArea();
            two.clearPanel();
        } else {
            two.refreshTextArea();
            one.clearPanel();
        }
    }

    private InputStream readVersusImage() {
        return getClass().getClassLoader().getResourceAsStream("img/vs.png");
    }

    private void popupOutcome(String message) {
        JOptionPane.showMessageDialog(null, message, "Outcome of the fight", JOptionPane.PLAIN_MESSAGE);
    }
}
