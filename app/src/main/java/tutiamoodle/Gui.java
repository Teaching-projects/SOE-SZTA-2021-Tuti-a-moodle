package tutiamoodle;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import javax.swing.*;

class Gui extends JFrame {

    public Gui(ObjectMapper objectMapper) {
        setLayout(new BorderLayout());

        final var one = new EntityPanel(objectMapper);
        final var two = new EntityPanel(objectMapper);
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
