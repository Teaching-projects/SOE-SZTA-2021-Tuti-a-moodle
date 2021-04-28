package tutiamoodle;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;

public class MapCellDeserializer extends JsonDeserializer<MapCell> {


    @Override
    public MapCell deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        var object = p.getCodec().<ObjectNode>readTree(p);
        var typeField = object.get("type");
        var wallsField = object.get("walls");
        var walls = new ArrayList<MapCell.Wall>();
        var type = MapCell.Type.NONE;

        if (wallsField != null) {
            for (var wallName : wallsField) {
                walls.add(MapCell.Wall.valueOf(wallName.asText().toUpperCase()));
            }
        }
        if (typeField != null) {
            type = MapCell.Type.valueOf(typeField.asText().toUpperCase());
        }
        return new MapCell(type, walls);
    }
}
