
public class Hero extends Entity {

    private int currentLevel;
    private double currentXp;
    private int xpPerLevel;
    private int dmgIncreasePerLevel;
    private int hpIncreasePerLevel;
    private float cooldownMultiplierPerLevel;
    private double originalHealth;
    private double lvlTreshHold;
    private double activeCooldown = 0;

    public Hero(
        double health,
        double attack,
        double defense,
        double cooldown,
        String name,
        String lore,
        int currentLevel,
        double currentXp,
        int xpPerLevel,
        int dmgIncreasePerLevel,
        int hpIncreasePerLevel,
        double originalHealth,
        double lvlTreshHold,
        float cooldownMultiplierPerLevel) {

        super(health, attack, defense, cooldown, name, lore);
        
        this.currentXp=currentXp;
        this.currentLevel=currentLevel;
        this.xpPerLevel=xpPerLevel;
        this.dmgIncreasePerLevel=dmgIncreasePerLevel;
        this.hpIncreasePerLevel=hpIncreasePerLevel;
        this.cooldownMultiplierPerLevel=cooldownMultiplierPerLevel;
        this.originalHealth=originalHealth;
        this.lvlTreshHold=lvlTreshHold;
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

    public double getCurrentXp(){
        return currentXp;
    }

    public void setCurrent_xp(double currentXp){
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

    public double getLvlTreshHold(){
        return lvlTreshHold;
    }

    public void setLvlTreshHold(double lvlTreshHold) {
        this.lvlTreshHold=lvlTreshHold;
    }
    
@Override
    public void attack(Entity other) {
        super.attack(other);
        setCurrent_xp(getCurrentXp()+getAttack());
        if(getCurrentXp()>=getLvlTreshHold()){
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
        setLvlTreshHold(getLvlTreshHold()*1.2);
    }
}
