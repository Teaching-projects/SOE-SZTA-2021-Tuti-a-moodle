public class Entity {
    private double health;
    private final double attack;
    private final double defense;

    public Entity(double health, double attack, double defense) {
        if (health < 0) {
            throw new IllegalArgumentException("Health must not be negative");
        }

        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public double getHealth() {
        return health;
    }

    private void setHealth(double health) {
        this.health = Math.max(0.0, health);
    }

    public boolean isAlive() {
        return getHealth() > 0;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public void attack(Entity other) {
        double damage = getAttack() - other.getDefense();

        // if damage is a negative number, then the code below would cause the
        // other entity to be healed after an attack
        if (damage <= 0) {
            return;
        }

        other.setHealth(other.getHealth() - damage);
    }
}
