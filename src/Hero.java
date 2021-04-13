public class Hero extends Entity {

    private int currentLevel = 1;
    private double currentXp = 0;
    private final double xpPerLevel;
    private final int dmgIncreasePerLevel;
    private final int hpIncreasePerLevel;
    private final double cooldownMultiplierPerLevel;
    private final double originalHealth = getHealth();

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


    public int gethpIncreasePerLevel() {
        return hpIncreasePerLevel;
    }

    public int getDmgIncreasePerLevel() {
        return dmgIncreasePerLevel;
    }

    public double getXpPerLevel() {
        return xpPerLevel;
    }

    public double getCurrentXp(){
        return currentXp;
    }

    protected void setCurrent_xp(double currentXp){
        this.currentXp=currentXp;
    }

    public int getCurrent_level() {
        return currentLevel;
    }

    protected void setCurrent_level(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    
    @Override
    public double attack(Entity other) {
        var damageDealt = super.attack(other);

        setCurrent_xp(getCurrentXp()+damageDealt);
        
        if(getCurrentXp()>=getXpPerLevel()){
            lvlup();
        }
        
        return damageDealt;
    }

    protected double getOriginalHealth(){
        return originalHealth;
    }

    protected void lvlup(){
        setCurrent_level(getCurrent_level()+1);
        setAttack(getAttack()+getDmgIncreasePerLevel());
        setHealth(getOriginalHealth()+gethpIncreasePerLevel());
        setCurrent_xp(getCurrentXp()-getXpPerLevel());
        setCooldown(getCooldown()+getcooldownMultiplierPerLevel());
    }

}
