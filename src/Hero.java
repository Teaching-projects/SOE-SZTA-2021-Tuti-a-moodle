public class Hero extends Entity {

    private int current_level;
    private int current_xp;
    private int xp_per_level;
    private int dmg_increase_per_level;
    private int hp_increase_per_level;
    private float cooldown_multiplier_per_level;
     private double activeCooldown = 0;

    public Hero(
        double health,
        double attack,
        double defense,
        double cooldown,
        String name,
        String lore,
        int current_level,
        int current_xp,
        int xp_per_level,
        int dmg_increase_per_level,
        int hp_increase_per_level,
        double originalHealth,
        float cooldown_multiplier_per_level) {

        super(health, attack, defense, cooldown, cooldown, name, lore, originalHealth);
        
        this.current_level=current_level;
        this.xp_per_level=xp_per_level;
        this.dmg_increase_per_level=dmg_increase_per_level;
        this.hp_increase_per_level=hp_increase_per_level;
        this.cooldown_multiplier_per_level=cooldown_multiplier_per_level;
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

    public void setCurrent_xp(int current_xp){
        this.current_xp=current_xp;
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

    public void attack(Entity other) {
        double damage = getAttack() - other.getDefense();

        // avoid healing the other entity
        if (damage > 0) {
            other.setHealth(other.getHealth() - damage);
        }
        
        setActiveCooldown(getCooldown());
    }
    public void lvlup(Hero h){
        h.setCurrent_level(current_level+1);
        h.setAttack(getAttack()+getDmg_increase_per_level());
        h.setHealth(h.getOriginalHealth()+h.getHp_increase_per_level());
        h.setCurrent_xp(0);
        h.setCooldown(getCooldown()+getCooldown_multiplier_per_level());;
    }
}
