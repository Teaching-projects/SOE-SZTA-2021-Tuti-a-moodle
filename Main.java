import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Entity combatant_1 = new Warrior(75, 45, 10);
        Entity combatant_2 = new Warrior(90, 35, 15);

        battle(combatant_1, combatant_2);
    }

    private static void battle(Entity good, Entity evil) {
        Random rand = new Random();
        int i = 1;
        System.out.println(
                "Good - HP: " + good.getHealth() + ", ATK: " + good.getAttack() + ", DEF: " + good.getDefense());
        System.out.println(
                "Evil - HP: " + evil.getHealth() + ", ATK: " + evil.getAttack() + ", DEF: " + evil.getDefense());
        System.out.println("====================");
        System.out.println("The Battle begins.");
        while (good.isAlive() && evil.isAlive()) {
            System.out.println("********************");
            System.out.println(i + ". round:");
            if (rand.nextBoolean()) {
                System.out.println("Good attacks first.");
                good.attack(evil);
                if (evil.isAlive()) {
                    evil.attack(good);
                }
            } else {
                System.out.println("Evil attacks first.");
                evil.attack(good);
                if (good.isAlive()) {
                    good.attack(evil);
                }
            }
            i++;
            System.out.println("********************");
            System.out.println("Good - HP: " + good.getHealth());
            System.out.println("Evil - HP: " + evil.getHealth());
        }
        System.out.println("====================");
        System.out.println("The Battle is over.");
        String result = good.isAlive() ? "The Good won!" : "The Evil won!";
        System.out.println(result);
    }
}
