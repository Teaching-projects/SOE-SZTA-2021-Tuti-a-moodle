import javax.swing.*;
import java.awt.*;

class Gui extends JFrame {
    private final EntityPanel one;
    private final EntityPanel two;

    public Gui() {
        setLayout(new BorderLayout());

        one = new EntityPanel();
        two = new EntityPanel();
        Container contentPane = getContentPane();
        contentPane.add(one, BorderLayout.LINE_START);
        contentPane.add(new VSPanel(one, two), BorderLayout.CENTER);
        contentPane.add(two, BorderLayout.LINE_END);

        setTitle("Fapados RPG");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
