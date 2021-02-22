import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double goodHealth = readDouble(in, "Enter the health of the good entity: ");
        double goodAttack = readDouble(in, "Enter the attack of the good entity: ");
        double goodDefense = readDouble(in, "Enter the defense of the good entity: ");

        double evilHealth = readDouble(in, "Enter the health of the evil entity: ");
        double evilAttack = readDouble(in, "Enter the attack of the evil entity: ");
        double evilDefense = readDouble(in, "Enter the defense of the evil entity: ");

        Entity good = new Entity(goodHealth, goodAttack, goodDefense, "Tim");
        Entity evil = new Entity(evilHealth, evilAttack, evilDefense, "Honks");

        Battle bt = new Battle(good, evil);
        bt.battle();

        in.close();
    }

    static double readDouble(Scanner scanner, String msg) {
        boolean b = true;
        double value = 0;
        do {
            System.out.print(msg);
            if (scanner.hasNextFloat()) {
                value = scanner.nextFloat();
                b = false;
            } else {
                System.out.println(scanner.next() + " is not an acceptable type. ");
            }
        } while (b);
        return value;
    }
}
