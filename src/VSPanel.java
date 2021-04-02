import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;

import javax.imageio.ImageIO;

public class VSPanel extends JPanel implements ActionListener {

    JButton button;
    JTextArea textArea;
    EntityPanel one;
    EntityPanel two;

    public VSPanel(EntityPanel one, EntityPanel two) {
        super(new BorderLayout());
        this.one = one;
        this.two = two;
        textArea = new JTextArea();
        try {
            BufferedImage myPicture = ImageIO.read(new File("./img/vs.png"));
            Image scaledImage = myPicture.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            add(new JLabel(new ImageIcon(scaledImage)), BorderLayout.PAGE_START);
        } catch (Exception e) {
            // TODO: handle exception
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
        if (e.getSource() == button) {
            var printer = new PrintStream(new TextAreaOutputStream(textArea));
            var battleResult = new Battle(printer).battle(one.getEntity(), two.getEntity());
            if (!battleResult.isUnbalanced()) {
                return;
            }

            var winner = battleResult.getWinner();
            if (winner == null) {
                printer.println("There wouldn't be any damage in the combat!");
                return;
            }

            printer.println("Unbalanced combat! " + winner.getName() + " won!");
        }
    }
}
