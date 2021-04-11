import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

import javax.swing.text.html.parser.Entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jdk.internal.jimage.ImageReader.Node;

public class EntityDeserializer extends JsonDeserializer<Entity> {

    private static final String[] heroFields = {
        "xp_per_level",
        "dmg_increase_per_level",
        "hp_increase_per_level",
        "cooldown_multiplier_per_level"
    };

    @Override
    public Entity deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {

        var object = p.getCodec().<ObjectNode>readTree(p);
        
        return isHero(object) ? buildHero(object) : buildEntity(object);
    }

    private boolean isHero(ObjectNode object) {
        Integer count = Arrays.stream(heroFields).reduce(0, (c, field)->c + (object.has(field)?1:0),(a,b)->a+b) ;

        if(count == 0){
            return false;
        } else if (count == heroFields.length){
            return true;
        }
        throw new IllegalStateException("Wrong input!");
    }
    
    private Entity buildHero(ObjectNode object){
        buildEntity(object);
        int xpPerLevel = object.get("xp_per_level").asInt();
        int dmgIncreacePerLevel = object.get("dmg_increase_per_level").asInt();
        int hpIncreasePerLevel = object.get("hp_increase_per_level").asInt();
        float cooldownMultiplierPerLevel = object.floatValue();
    }

    private Entity buildEntity(ObjectNode object){
        double health = object.get("health").asDouble();
        double attack = object.get("attack").asDouble();
        double defense = object.get("defense").asDouble();
        double cooldown = object.get("cooldown").asDouble();
        String name= object.get("name").asText();
        String lore= object.get("lore").asText();
    }
}
