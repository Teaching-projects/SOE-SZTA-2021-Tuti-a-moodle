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
        Random rand = new Random();
        int i = 1;

        printLineOf('=', 20);
        printEntity(player1Name, player1);
        printEntity(player2Name, player2);

        printLineOf('=', 20);

        printMessage("The Battle begins.");

        while (player1.isAlive() && player2.isAlive()) {

            printLineOf('*', 20);
            printMessage(i + ". round:");

            if (rand.nextBoolean()) {
                printMessage(player1Name + " attacks first.");
                player1.attack(player2);
                if (player2.isAlive()) {
                    player2.attack(player1);
                }
            } else {
                printMessage(player2Name + " attacks first.");
                player2.attack(player1);
                if (player1.isAlive()) {
                    player1.attack(player2);
                }
            }

            i++;
            printLineOf('*', 20);
            printEntityHP(player1Name, player1);
            printEntityHP(player2Name, player2);
        }

        printLineOf('=', 20);
        printMessage("The Battle is over.");

        String result = player1.isAlive() ? player1Name : player2Name;
        printMessage(result + " won!");
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
}
