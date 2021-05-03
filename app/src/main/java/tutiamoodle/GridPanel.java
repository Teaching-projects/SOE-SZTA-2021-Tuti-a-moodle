package tutiamoodle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GridPanel {
    private static final int N = 5;
    private final List<RoomPanel> list = new ArrayList<>();

    public void addRoomPanel(RoomPanel rp) {
        list.add(rp);
    }

    public RoomPanel getRoomPanel(int r, int c) {
        int index = r * N + c;
        return list.get(index);
    }

    public JPanel createGridPanel() {
        JPanel p = new JPanel(new GridLayout(N, N));
        for (RoomPanel rp: list) {
            p.add(rp);
        }
        return p;
    }
}
