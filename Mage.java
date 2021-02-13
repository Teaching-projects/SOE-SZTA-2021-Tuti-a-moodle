public class Mage extends Spec {

    private double mana;

    public Mage(double health, double attack, double defense, double mana) {
        super(health, attack, defense);
        this.mana = mana;
    }

    public double getMana() {
        return mana;
    }

}
