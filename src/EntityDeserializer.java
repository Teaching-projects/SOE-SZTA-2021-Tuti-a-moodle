import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;


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
        
        return isHero(object) ?  buildHero(object) : buildEntity(object);
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
    
    private Hero buildHero(ObjectNode object){

        int xpPerLevel = object.get("xp_per_level").asInt();
        int dmgIncreacePerLevel = object.get("dmg_increase_per_level").asInt();
        int hpIncreasePerLevel = object.get("hp_increase_per_level").asInt();
        double cooldownMultiplierPerLevel = object.get("cooldown_multiplier_per_level").asDouble();

        return new Hero(
            object.get("health").asDouble(),
            object.get("attack").asDouble(),
            object.get("defense").asDouble(),
            object.get("cooldown").asDouble(),
            object.get("name").asText(),
            object.get("lore").asText(),
            xpPerLevel,
            dmgIncreacePerLevel,
            hpIncreasePerLevel,
            cooldownMultiplierPerLevel
            );
    }

    private Entity buildEntity(ObjectNode object){

        return new Entity(
            object.get("health").asDouble(),
            object.get("attack").asDouble(),
            object.get("defense").asDouble(),
            object.get("cooldown").asDouble(),
            object.get("name").asText(),
            object.get("lore").asText());
    }
}
