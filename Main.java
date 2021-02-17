import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double goodHealth = readDouble("Please give the health of the good entity: ");
        double goodAttack = readDouble("Please give the attack of the good entity: ");
        double goodDefense = readDouble("Please give the defense of the good entity: ");

        double evilHealth = readDouble("Please give the health of the good entity: ");
        double evilAttack = readDouble("Please give the attack of the good entity: ");
        double evilDefense = readDouble("Please give the defense of the good entity: ");

        Entity good = new Entity(goodHealth, goodAttack, goodDefense);
        Entity evil = new Entity(evilHealth, evilAttack, evilDefense);

      
        battle(good, evil);
    }

    private static void battle(Entity good, Entity evil) {
        Random rand = new Random();
        int i = 1;

        printEntity("good", good);
        printEntity("evil", evil);

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

    static void printEntity(String name, Entity entity) {
        System.out.println(name + " - HP: " + entity.getHealth() + ", DMG: " + entity.getAttack() + ", DEF: "
                + entity.getDefense());
    }

    static double readDouble(String msg) {
        Scanner in = new Scanner(System.in);
        boolean b = true;
        String valueString;
        double value = 0;
        do {
            System.out.print(msg);
            valueString = in.nextLine();
            try {
                value = Double.parseDouble(valueString);
                b = false;
            } catch (Exception ex) {
                System.err.println("Please enter number or use \".\" instead of \",\"!");
            }
        } while (b);
        return value;
    }
}
