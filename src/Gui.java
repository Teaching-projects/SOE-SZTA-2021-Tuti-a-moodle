import javax.swing.*;
import java.awt.*;

class Gui extends JFrame {
    private EntityPanel one;
    private EntityPanel two;

    public Gui() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        one = new EntityPanel();
        two = new EntityPanel();
        Container contentPane = getContentPane();
        contentPane.add(one, BorderLayout.LINE_START);
        contentPane.add(new VSPanel(one, two), BorderLayout.CENTER);
        contentPane.add(two, BorderLayout.LINE_END);

        setTitle("Fapados RPG");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}