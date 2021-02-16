public class Entity {
    private double health;
    private final double attack;
    private final double defense;

    public Entity(double health, double attack, double defense) {
        this.setHealth(health);
        this.attack = attack;
        this.defense = defense;
    }

    public double getHealth() {
        return health;
    }

    private void setHealth(double health) {
        if (health < 0) {
            throw new IllegalArgumentException("Health must not be negative");
        }

        this.health = health;
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
        if (damage <= 0) {
            return;
        }

        double newHealth = other.getHealth() - damage;
        other.setHealth(newHealth < 0 ? 0 : newHealth);
    }
}
