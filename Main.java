import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Entity combatant_1 = new Warrior(75, 45, 10);
        Entity combatant_2 = new Warrior(90, 35, 15);
        Entity combatant_mage = new Mage(50, 70, 5, 100);

        battle(combatant_1, combatant_mage);
    }

    private static void battle(Entity warrior, Entity mage) {
        Random rand = new Random();
        int i = 1;
        System.out.println("Warrior - HP: " + warrior.getHealth() + ", ATK: " + warrior.getAttack() + ", DEF: "
                + warrior.getDefense());
        System.out.println(
                "Mage - HP: " + mage.getHealth() + ", ATK: " + mage.getAttack() + ", DEF: " + mage.getDefense());
        System.out.println("====================");
        System.out.println("The Battle begins.");
        while (warrior.isAlive() && mage.isAlive()) {
            System.out.println("********************");
            System.out.println(i + ". round:");
            if (rand.nextBoolean()) {
                System.out.println("The Warrior attacks first.");
                warrior.attack(mage);
                if (mage.isAlive()) {
                    mage.attack(warrior);
                }
            } else {
                System.out.println("The Mage attacks first.");
                mage.attack(warrior);
                if (warrior.isAlive()) {
                    warrior.attack(mage);
                }
            }
            i++;
            System.out.println("********************");
            System.out.println("Warrior - HP: " + warrior.getHealth());
            System.out.println("Mage - HP: " + mage.getHealth());
        }
        System.out.println("====================");
        System.out.println("The Battle is over.");
        String result = warrior.isAlive() ? "The Warrior won!" : "The Mage won!";
        System.out.println(result);
    }
}
