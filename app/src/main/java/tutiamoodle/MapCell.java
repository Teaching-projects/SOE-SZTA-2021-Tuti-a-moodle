package tutiamoodle;

import java.util.ArrayList;
import java.util.EnumSet;

public class MapCell {

    private Type type;
    private final EnumSet<MapCell.Wall> walls = EnumSet.noneOf(MapCell.Wall.class);

    public MapCell(Type type, ArrayList<Wall> walls) {
        this.type = type;
        this.walls.addAll(walls);
    }

    public enum Type {
        NONE,
        MONSTER,
        POTION,
        HERO

    }

    public Type getType() {
        return type;
    }

    public EnumSet<Wall> getWalls() {
        return walls;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Wall {
        W,
        S,
        E,
        N
    }
}
