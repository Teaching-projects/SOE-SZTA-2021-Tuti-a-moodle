import javax.swing.*;
import java.awt.*;
import com.fasterxml.jackson.databind.ObjectMapper;

class Gui extends JFrame {
    private final EntityPanel one;
    private final EntityPanel two;

    public Gui(ObjectMapper objectMapper) {
        setLayout(new BorderLayout());

        one = new EntityPanel(objectMapper);
        two = new EntityPanel(objectMapper);
        Container contentPane = getContentPane();
        contentPane.add(one, BorderLayout.LINE_START);
        contentPane.add(new BattlePanel(one, two), BorderLayout.CENTER);
        contentPane.add(two, BorderLayout.LINE_END);

        setTitle("Fapados RPG");
        setSize(750, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
