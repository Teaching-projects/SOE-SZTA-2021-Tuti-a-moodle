import java.util.Random;

public class Battle {
    private final Entity player1;
    private final String player1Name;
    private final Entity player2;
    private final String player2Name;

    public Battle(String player1Name, Entity player1, String player2Name, Entity player2) {
        this.player1Name = player1Name;
        this.player1 = player1;
        this.player2Name = player2Name;
        this.player2 = player2;
    }

    public void battle() {
        int i = 1;

        printLineOf('=', 20);
        printEntity(player1Name, player1);
        printEntity(player2Name, player2);

        printLineOf('=', 20);

        if (preTest()) {
            return;
        }

        printMessage("The Battle begins.");

        while (player1.isAlive() && player2.isAlive()) {

            printLineOf('*', 20);
            printMessage(i + ". round:");

            printMessage(player1Name + " and " + player2Name + " clash!");
            player1.attack(player2);
            healthCheck();
            player2.attack(player1);

            i++;
            printLineOf('*', 20);
            healthCheck();
        }

        printLineOf('=', 20);
        printMessage("The Battle is over.");

        String result = player1.isAlive() ? player1Name : player2Name;
        printMessage(result + " won!");
    }

    private boolean preTest() {
        if (player1.getAttack() <= player2.getDefense() && player2.getAttack() <= player1.getDefense()) {
            printMessage("Unbalanced combat!");
            printMessage("Draw!");
            return true;
        }
        if (player2.getAttack() <= player1.getDefense()) {
            printMessage("Unbalanced combat!");
            printMessage(player1Name + " won!");
            return true;
        }
        if (player1.getAttack() <= player2.getDefense()) {
            printMessage("Unbalanced combat!");
            printMessage(player2Name + " won!");
            return true;
        }
        return false;
    }

    private void printEntity(String name, Entity entity) {
        System.out.println(name + " - HP: " + entity.getHealth() + ", DMG: " + entity.getAttack() + ", DEF: "
                + entity.getDefense());
    }

    private void printEntityHP(String name, Entity entity) {
        System.out.println(name + " - HP: " + entity.getHealth());
    }

    private void printLineOf(char character, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(character);
        }
        System.out.println();
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private void healthCheck() {
        printEntityHP(player1Name, player1);
        printEntityHP(player2Name, player2);
    }
}
