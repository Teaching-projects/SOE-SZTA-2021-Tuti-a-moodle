public class Hero extends Entity {

    private final double xpPerLevel;
    private final int damageIncreasePerLevel;
    private final int hpIncreasePerLevel;
    private final double cooldownMultiplierPerLevel;
    private final double originalHealth = getHealth();
    private int currentLevel = 1;
    private double currentXp = 0;

    public Hero(
        double health,
        double attack,
        double defense,
        double cooldown,
        String name,
        String lore,
        int xpPerLevel,
        int damageIncreasePerLevel,
        int hpIncreasePerLevel,
        double cooldownMultiplierPerLevel
    ) {

        super(health, attack, defense, cooldown, name, lore);

        this.xpPerLevel = xpPerLevel;
        this.damageIncreasePerLevel = damageIncreasePerLevel;
        this.hpIncreasePerLevel = hpIncreasePerLevel;
        this.cooldownMultiplierPerLevel = cooldownMultiplierPerLevel;
    }

    public double getCooldownMultiplierPerLevel() {
        return cooldownMultiplierPerLevel;
    }

    public int getHpIncreasePerLevel() {
        return hpIncreasePerLevel;
    }

    public int getDamageIncreasePerLevel() {
        return damageIncreasePerLevel;
    }

    public double getXpPerLevel() {
        return xpPerLevel;
    }

    public double getCurrentXp() {
        return currentXp;
    }

    protected void setCurrentXp(double currentXp) {
        this.currentXp = currentXp;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    protected void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    protected double getOriginalHealth() {
        return originalHealth;
    }

    @Override
    public double attack(Entity other) {
        var damageDealt = super.attack(other);

        setCurrentXp(getCurrentXp() + damageDealt);

        while (getCurrentXp() >= getXpPerLevel()) {
            levelUp();
        }

        return damageDealt;
    }

    private void levelUp() {
        setCurrentLevel(getCurrentLevel() + 1);
        setAttack(getAttack() + getDamageIncreasePerLevel());
        setHealth(getOriginalHealth() + getHpIncreasePerLevel());
        setCurrentXp(getCurrentXp() - getXpPerLevel());
        setCooldown(getCooldown() + getCooldownMultiplierPerLevel());
    }

}
