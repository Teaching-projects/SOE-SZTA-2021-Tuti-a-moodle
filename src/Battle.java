import java.io.PrintStream;

public class Battle {
    private final PrintStream printer;

    public Battle(PrintStream printer) {
        this.printer = printer;
    }

    public void battle(Entity player1, Entity player2) {

        printSeparator('=', 20);
        printEntity(player1);
        printEntity(player2);

        printSeparator('=', 20);

        if (preTest(player1, player2)) {
            return;
        }

        printMessage("The Battle begins.");
        printMessage(player1.getName() + " and " + player2.getName() + " clash!");

        player1.setActiveCooldown(0);
        player2.setActiveCooldown(0);

        do {
            double minCooldown = Math.min(player1.getActiveCooldown(), player2.getActiveCooldown());
            player1.setActiveCooldown(player1.getActiveCooldown() - minCooldown);
            player2.setActiveCooldown(player2.getActiveCooldown() - minCooldown);
            if (player1.getActiveCooldown() == 0) {
                player1.attack(player2);
            }
            if (player2.getActiveCooldown() == 0) {
                player2.attack(player1);
            }
        } while(player1.isAlive() && player2.isAlive());

        printSeparator('=', 20);
        printMessage("The Battle is over.");

        if (!player1.isAlive() && !player2.isAlive()) {
            printMessage("Both heroes are dead!");
        } else {
            String result = player1.isAlive() ? player1.getName() : player2.getName();
            printMessage(result + " won!");
        }
    }

    private boolean preTest(Entity player1, Entity player2) {
        if (player1.getAttack() <= player2.getDefense() && player2.getAttack() <= player1.getDefense()) {
            printMessage("There wouldn\'t be damage in combat!");
            return true;
        } else if (player2.getAttack() <= player1.getDefense()) {
            printMessage("Unbalanced combat!");
            printMessage(player1.getName() + " won!");
            return true;
        } else if (player1.getAttack() <= player2.getDefense()) {
            printMessage("Unbalanced combat!");
            printMessage(player2.getName() + " won!");
            return true;
        }
        return false;
    }

    private void printEntity(Entity entity) {
        printer.println(entity.getName() + " - HP: " + entity.getHealth() + ", DMG: " + entity.getAttack()
                + ", DEF: " + entity.getDefense());
    }

    private void printEntityHP(Entity entity) {
        printer.println(entity.getName() + " - HP: " + entity.getHealth());
    }

    private void printSeparator(char character, int length) {
        for (int i = 0; i < length; i++) {
            printer.print(character);
        }
        printer.println();
    }

    private void printMessage(String message) {
        printer.println(message);
    }

    private void healthCheck(Entity player1, Entity player2) {
        printEntityHP(player1);
        printEntityHP(player2);
    }
}
