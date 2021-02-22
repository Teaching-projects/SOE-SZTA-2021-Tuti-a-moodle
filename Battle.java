public class Battle {
    private final Entity player1;
    private final Entity player2;

    public Battle(Entity player1, Entity player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void battle() {
        int i = 1;

        printSeparator('=', 20);
        printEntity(player1);
        printEntity(player2);

        printSeparator('=', 20);

        if (preTest()) {
            return;
        }

        printMessage("The Battle begins.");
        printMessage(player1.getName() + " and " + player2.getName() + " clash!");

        while (player1.isAlive() && player2.isAlive()) {

            printSeparator('*', 20);
            printMessage(i + ". round:");

            player1.attack(player2);
            healthCheck();

            if (player2.isAlive()) {
                printSeparator('*', 20);
                player2.attack(player1);
                healthCheck();
            }
            i++;
        }

        printSeparator('=', 20);
        printMessage("The Battle is over.");

        String result = player1.isAlive() ? player1.getName() : player2.getName();
        printMessage(result + " won!");
    }

    private boolean preTest() {
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
        System.out.println(entity.getName() + " - HP: " + entity.getHealth() + ", DMG: " + entity.getAttack()
                + ", DEF: " + entity.getDefense());
    }

    private void printEntityHP(Entity entity) {
        System.out.println(entity.getName() + " - HP: " + entity.getHealth());
    }

    private void printSeparator(char character, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(character);
        }
        System.out.println();
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private void healthCheck() {
        printEntityHP(player1);
        printEntityHP(player2);
    }
}
