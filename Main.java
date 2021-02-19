import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double goodHealth = readDouble(in, "Please give the health of the good entity: ");
        double goodAttack = readDouble(in, "Please give the attack of the good entity: ");
        double goodDefense = readDouble(in, "Please give the defense of the good entity: ");

        double evilHealth = readDouble(in, "Please give the health of the good entity: ");
        double evilAttack = readDouble(in, "Please give the attack of the good entity: ");
        double evilDefense = readDouble(in, "Please give the defense of the good entity: ");

        Entity good = new Entity(goodHealth, goodAttack, goodDefense);
        Entity evil = new Entity(evilHealth, evilAttack, evilDefense);

        Battle bt = new Battle("Good", good, "Evil", evil);
        bt.battle();

        in.close();
    }

    static double readDouble(Scanner scanner, String msg) {
        boolean b = true;
        String valueString;
        double value = 0;
        do {
            System.out.print(msg);
            valueString = scanner.nextLine();
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
