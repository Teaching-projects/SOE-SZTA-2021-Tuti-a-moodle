public class Hero extends Entity {

    private int currentLevel = 1;
    private double currentXp = 0;
    private double xpPerLevel;
    private int dmgIncreasePerLevel;
    private int hpIncreasePerLevel;
    private double cooldownMultiplierPerLevel;
    private double originalHealth = getHealth();

    public Hero(
        double health,
        double attack,
        double defense,
        double cooldown,
        String name,
        String lore,
        int xpPerLevel,
        int dmgIncreasePerLevel,
        int hpIncreasePerLevel,
        double cooldownMultiplierPerLevel
        ) {

        super(health, attack, defense, cooldown, name, lore);
        
        this.xpPerLevel=xpPerLevel;
        this.dmgIncreasePerLevel=dmgIncreasePerLevel;
        this.hpIncreasePerLevel=hpIncreasePerLevel;
        this.cooldownMultiplierPerLevel=cooldownMultiplierPerLevel;
        
    }

    public double getcooldownMultiplierPerLevel() {
        return cooldownMultiplierPerLevel;
    }

    public void setcooldownMultiplierPerLevel(double cooldownMultiplierPerLevel) {
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

    public double getXpPerLevel() {
        return xpPerLevel;
    }

    public double getCurrentXp(){
        return currentXp;
    }

    public void setCurrent_xp(double currentXp){
        this.currentXp=currentXp;
    }

    public void setXp_per_level(double xpPerLevel) {
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
        setCurrent_xp(getCurrentXp()+getAttack());
        if(getCurrentXp()>=getXpPerLevel()){
            lvlup();
        }
    }

    public double getOriginalHealth(){
        return originalHealth;
    }

    public void lvlup(){
        setCurrent_level(getCurrent_level()+1);
        setAttack(getAttack()+getDmgIncreasePerLevel());
        setHealth(getOriginalHealth()+gethpIncreasePerLevel());
        setCurrent_xp(0);
        setCooldown(getCooldown()+getcooldownMultiplierPerLevel());
        setXp_per_level(xpPerLevel*1.2);
    }
}
