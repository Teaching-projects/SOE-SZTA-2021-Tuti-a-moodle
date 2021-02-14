public class Entity {
    private double health;
    private double attack;
    private double defense;

    public Entity(double health, double attack, double defense) {
        this.setHealth(health);
        this.attack = attack;
        this.defense = defense;
    }

    public double getHealth() {
        return health;
    }

    private void setHealth(double health) {
        if (health < 0 || health > 100) {
            throw new IllegalArgumentException("Health is outside the valid range");
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
        var enemyHealth = other.getHealth();
        var enemyDefense = other.getDefense();
        var ownAttack = getAttack();

        var damage = ownAttack - enemyDefense;
        if (damage <= 0) {
            return;
        }
        var newHealth = enemyHealth - damage;
        other.setHealth(newHealth < 0 ? 0 : newHealth);
    }
}
