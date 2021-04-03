import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = EntityBuilder.class)
public class Entity {
    private double health;
    private final double originalHealth;
    private double attack;
    private final double defense;
    private final double cooldown;
    private final String name;
    private final String lore;
    private double activeCooldown = 0;

    public Entity(
        double health,
        double originalHealth,
        double attack,
        double defense,
        double cooldown,
        String name,
        String lore, double originalHealth2
    ) {
        if (health < 0) {
            throw new IllegalArgumentException("Health must not be negative");
        }

        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.cooldown = cooldown;
        this.name = name;
        this.lore = lore;
        this.originalHealth=originalHealth;
    }

    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
    }

    public double getCooldown() {
        return cooldown;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = Math.max(0.0, health);
    }

    public boolean isAlive() {
        return getHealth() > 0;
    }

    public double getAttack() {
        return attack;
    }
    public void setAttack(double attack){
            this.attack=attack;
    }

    public double getDefense() {
        return defense;
    }

    public void attack(Entity other) {
        double damage = getAttack() - other.getDefense();

        // avoid healing the other entity
        if (damage > 0) {
            other.setHealth(other.getHealth() - damage);
        }

        setActiveCooldown(getCooldown());
    }

    public void setActiveCooldown(double activeCooldown) {
        this.activeCooldown = activeCooldown;
    }

    public double getActiveCooldown() {
        return activeCooldown;
    }

    public double getOriginalHealth(){
        return originalHealth;
    }
    public void setCooldown(double cooldown){
        this.cooldown=cooldown;
    }
}
