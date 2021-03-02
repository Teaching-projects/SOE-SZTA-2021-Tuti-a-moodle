import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonPOJOBuilder
public class EntityBuilder {
    private double health;
    private double attack;
    private double defense;
    private double cooldown;
    private String name;
    private String lore;

    @JsonProperty("health_points")
    EntityBuilder withHealth(double health) {
        this.health = health;
        return this;
    }

    @JsonProperty("damage")
    EntityBuilder withAttack(double attack) {
        this.attack = attack;
        return this;
    }

    EntityBuilder withDefense(double defense) {
        this.defense = defense;
        return this;
    }

    @JsonProperty("attack_cooldown")
    EntityBuilder withCooldown(double cooldown) {
        this.cooldown = cooldown;
        return this;
    }

    EntityBuilder withName(String name) {
        this.name = name;
        return this;
    }

    EntityBuilder withLore(String lore) {
        this.lore = lore;
        return this;
    }

    public Entity build() {
        return new Entity(health, attack, defense, cooldown, name, lore);
    }
}
