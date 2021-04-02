public class Hero extends Entity {

    private int current_level;
    private int xp_per_level;
    private int dmg_increase_per_level;
    private int hp_increase_per_level;
    private float cooldown_multiplier_per_level;

    public Hero(
        double health,
        double attack,
        double defense,
        double cooldown,
        String name,
        String lore,
        int current_level,
        int xp_per_level,
        int dmg_increase_per_level,
        int hp_increase_per_level,
        float cooldown_multiplier_per_level) {

        super(health, attack, defense, cooldown, name, lore);
        
        this.setCurrent_level(current_level);
        this.setXp_per_level(xp_per_level);
        this.setDmg_increase_per_level(dmg_increase_per_level);
        this.setHp_increase_per_level(hp_increase_per_level);
        this.setCooldown_multiplier_per_level(cooldown_multiplier_per_level);
    }

    public float getCooldown_multiplier_per_level() {
        return cooldown_multiplier_per_level;
    }

    public void setCooldown_multiplier_per_level(float cooldown_multiplier_per_level) {
        this.cooldown_multiplier_per_level = cooldown_multiplier_per_level;
    }

    public int getHp_increase_per_level() {
        return hp_increase_per_level;
    }

    public void setHp_increase_per_level(int hp_increase_per_level) {
        this.hp_increase_per_level = hp_increase_per_level;
    }

    public int getDmg_increase_per_level() {
        return dmg_increase_per_level;
    }

    public void setDmg_increase_per_level(int dmg_increase_per_level) {
        this.dmg_increase_per_level = dmg_increase_per_level;
    }

    public int getXp_per_level() {
        return xp_per_level;
    }

    public void setXp_per_level(int xp_per_level) {
        this.xp_per_level = xp_per_level;
    }

    public int getCurrent_level() {
        return current_level;
    }

    public void setCurrent_level(int current_level) {
        this.current_level = current_level;
    }
    
}
