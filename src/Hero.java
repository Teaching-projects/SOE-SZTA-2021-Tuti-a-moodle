public class Hero extends Entity {

    private int currentLevel;
    private int currentXp;
    private int xpPerLevel;
    private int dmgIncreasePerLevel;
    private int hpIncreasePerLevel;
    private float cooldownMultiplierPerLevel;
    private double originalHealth;
    private double activeCooldown = 0;

    public Hero(
        double health,
        double attack,
        double defense,
        double cooldown,
        String name,
        String lore,
        int currentLevel,
        int currentXp,
        int xpPerLevel,
        int dmgIncreasePerLevel,
        int hpIncreasePerLevel,
        double originalHealth,
        float cooldownMultiplierPerLevel) {

        super(health, attack, defense, cooldown, name, lore);
        
        this.currentXp=currentXp;
        this.currentLevel=currentLevel;
        this.xpPerLevel=xpPerLevel;
        this.dmgIncreasePerLevel=dmgIncreasePerLevel;
        this.hpIncreasePerLevel=hpIncreasePerLevel;
        this.cooldownMultiplierPerLevel=cooldownMultiplierPerLevel;
        this.originalHealth=originalHealth;
    }

    public float getcooldownMultiplierPerLevel() {
        return cooldownMultiplierPerLevel;
    }

    public void setcooldownMultiplierPerLevel(float cooldownMultiplierPerLevel) {
        this.cooldownMultiplierPerLevel = cooldownMultiplierPerLevel;
    }

    public int gethpIncreasePerLevel() {
        return hpIncreasePerLevel;
    }

    public void sethpIncreasePerLevel(int hpIncreasePerLevel) {
        this.hpIncreasePerLevel = hpIncreasePerLevel;
    }

    public int getDmgIncreasePerLevel() {
        return dmgIncreasePerLevel;
    }

    public void setDmgIncreasePerLevel(int dmgIncreasePerLevel) {
        this.dmgIncreasePerLevel = dmgIncreasePerLevel;
    }

    public int getXpPerLevel() {
        return xpPerLevel;
    }

    public int getCurrentXp(){
        return currentXp;
    }

    public void setCurrent_xp(int currentXp){
        this.currentXp=currentXp;
    }

    public void setXp_per_level(int xpPerLevel) {
        this.xpPerLevel = xpPerLevel;
    }

    public int getCurrent_level() {
        return currentLevel;
    }

    public void setCurrent_level(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    
@Override
    public void attack(Entity other) {
        super.attack(other);
        setCurrent_xp(getCurrentXp()+1);
    }

    public double getOriginalHealth(){
        return originalHealth;
    }

    public void lvlup(Hero h){
        h.setCurrent_level(getCurrent_level()+1);
        h.setAttack(getAttack()+getDmgIncreasePerLevel());
        h.setHealth(h.getOriginalHealth()+h.gethpIncreasePerLevel());
        h.setCurrent_xp(0);
        h.setCooldown(getCooldown()+getcooldownMultiplierPerLevel());;
    }
}
