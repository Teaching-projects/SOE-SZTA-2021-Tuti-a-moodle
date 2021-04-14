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

    protected void setCurrentXp(double currentXp){
        this.currentXp=currentXp;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    protected void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    
    @Override
    public double attack(Entity other) {
        var damageDealt = super.attack(other);

        setCurrentXp(getCurrentXp()+damageDealt);
        
        if(getCurrentXp()>=getXpPerLevel()){
            lvlup();
        }
        
        return damageDealt;
    }

    protected double getOriginalHealth(){
        return originalHealth;
    }

    protected void lvlup(){
        setCurrentLevel(getCurrentLevel()+1);
        setAttack(getAttack()+getDmgIncreasePerLevel());
        setHealth(getOriginalHealth()+gethpIncreasePerLevel());
        setCurrentXp(getCurrentXp()-getXpPerLevel());
        setCooldown(getCooldown()+getcooldownMultiplierPerLevel());
    }

}
