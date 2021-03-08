import java.util.EnumSet;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonPOJOBuilder
public class EntityBuilder {
    private static final String HEALTH_FIELD = "health_points";
    private static final String ATTACK_FIELD = "damage";
    private static final String COOLDOWN_FIELD = "attack_cooldown";
    private static final String NAME_FIELD = "name";
    private static final String LORE_FIELD = "lore";

    private double health;
    private double attack;
    private double defense;
    private double cooldown;
    private String name;
    private String lore;
    private EnumSet<Required> requiredFields = EnumSet.allOf(Required.class);

    private enum Required {
        HEALTH(HEALTH_FIELD),
        ATTACK(ATTACK_FIELD),
        COOLDOWN(COOLDOWN_FIELD),
        NAME(NAME_FIELD),
        LORE(LORE_FIELD);

        private final String name;

        Required(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @JsonProperty(HEALTH_FIELD)
    EntityBuilder withHealth(double health) {
        requiredFields.remove(Required.HEALTH);
        this.health = health;
        return this;
    }

    @JsonProperty(ATTACK_FIELD)
    EntityBuilder withAttack(double attack) {
        requiredFields.remove(Required.ATTACK);
        this.attack = attack;
        return this;
    }

    EntityBuilder withDefense(double defense) {
        this.defense = defense;
        return this;
    }

    @JsonProperty(COOLDOWN_FIELD)
    EntityBuilder withCooldown(double cooldown) {
        requiredFields.remove(Required.COOLDOWN);
        this.cooldown = cooldown;
        return this;
    }

    EntityBuilder withName(String name) {
        Objects.requireNonNull(name, "'" + NAME_FIELD + "' must not be null");
        requiredFields.remove(Required.NAME);
        this.name = name;
        return this;
    }

    EntityBuilder withLore(String lore) {
        Objects.requireNonNull(lore, "'" + LORE_FIELD + "' must not be null");
        requiredFields.remove(Required.LORE);
        this.lore = lore;
        return this;
    }

    public Entity build() {
        for (var requiredField : requiredFields) {
            throw new IllegalStateException("'" + requiredField.getName() + "' is a required field");
        }

        return new Entity(health, attack, defense, cooldown, name, lore);
    }
}
