public class Entity {
    private double health;
    private final double attack;
    private final double defense;
    private final String name;

    public Entity(double health, double attack, double defense, String name) {
        if (health < 0) {
            throw new IllegalArgumentException("Health must not be negative");
        }

        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.name = name;
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

        // avoid healing the other entity
        if (damage > 0) {
            other.setHealth(other.getHealth() - damage);
        }
    }

    public String getName() {
        return name;
    }
}
