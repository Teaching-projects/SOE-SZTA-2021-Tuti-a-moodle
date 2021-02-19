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

        Battle bt = new Battle("Good", good, "Evil", evil);
        bt.battle();
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
