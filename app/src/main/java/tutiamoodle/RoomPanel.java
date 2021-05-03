package tutiamoodle;

import java.awt.*;
import javax.swing.*;

public class RoomPanel extends JPanel {
    private final MapCell mapCell;
    private final Entity entity;
    private JLabel label;
    private int[] borderWall = {0, 0, 0, 0};

    public RoomPanel(MapCell mapCell, Entity entity, JLabel image) {
        this.mapCell = mapCell;
        this.entity = entity;
        this.label = image;
        label.setVisible(false);

        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);

        int[] border = walls();
        setBorder(BorderFactory.createMatteBorder(border[0], border[1], border[2], border[3], Color.BLACK));
    }
    
    private int[] walls() {
        for (MapCell.Wall s: mapCell.getWalls()) {
            switch (s) {
                case N: {
                    borderWall[0] += 2;
                    break;
                }
                case W: {
                    borderWall[1] += 2;
                    break;
                }
                case S: {
                    borderWall[2] += 2;
                    break;
                }
                case E: {
                    borderWall[3] += 2;
                    break;
                }
                default:
                    return borderWall;
            }
        }
        return borderWall;
    }

    public int getBorderNorth() {
        return borderWall[0];
    }

    public int getBorderWest() {
        return borderWall[1];
    }

    public int getBorderSouth() {
        return borderWall[2];
    }

    public int getBorderEast() {
        return borderWall[3];
    }

    public Entity getEntity() {
        return entity;
    }

    public void setImage(Boolean b) {
        label.setVisible(b);
        label.revalidate();
        label.repaint();
    }
}
