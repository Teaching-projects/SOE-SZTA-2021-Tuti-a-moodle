package tutiamoodle;

import java.io.PrintStream;

public class Battle {
    private final PrintStream printer;

    public Battle(PrintStream printer) {
        this.printer = printer;
    }

    public BattleResult battle(Entity player1, Entity player2) {

        printSeparator();
        printEntity(player1);
        printEntity(player2);

        printSeparator();

        var preTestResult = preTest(player1, player2);
        if (preTestResult != null) {
            return preTestResult;
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
        } while (player1.isAlive() && player2.isAlive());

        printSeparator();
        printMessage("The Battle is over.");

        if (!player1.isAlive() && !player2.isAlive()) {
            printMessage("Both heroes are dead!");
            return new BattleResult(false, null);
        }

        Entity winner = player1.isAlive() ? player1 : player2;
        printMessage(winner.getName() + " won!");
        return new BattleResult(false, winner);
    }

    private BattleResult preTest(Entity player1, Entity player2) {
        if (player1.getAttack() <= player2.getDefense() && player2.getAttack() <= player1.getDefense()) {
            return new BattleResult(true, null);
        } else if (player2.getAttack() <= player1.getDefense()) {
            return new BattleResult(true, player1);
        } else if (player1.getAttack() <= player2.getDefense()) {
            return new BattleResult(true, player2);
        }
        return null;
    }

    private void printEntity(Entity entity) {
        printer.println(entity.getName() + " - HP: " + entity.getHealth() + ", DMG: " + entity.getAttack()
            + ", DEF: " + entity.getDefense());
    }

    private void printSeparator() {
        printer.println("=".repeat(20));
    }

    private void printMessage(String message) {
        printer.println(message);
    }

    public static class BattleResult {
        private final boolean unbalanced;
        private final Entity winner;

        public BattleResult(boolean unbalanced, Entity winner) {
            this.unbalanced = unbalanced;
            this.winner = winner;
        }

        public boolean isBalanced() {
            return !unbalanced;
        }

        public Entity getWinner() {
            return winner;
        }
    }
}
