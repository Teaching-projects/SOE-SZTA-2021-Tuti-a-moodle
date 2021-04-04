import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IOException {
        final Entity one;
        final Entity two;
        switch (args.length) {
            case 0:
                try (var reader = new EntityReader()) {
                    one = reader.readEntity();
                    two = reader.readEntity();
                } catch (NoSuchElementException ex) {
                    bail("The input file is too short");
                    return;
                }
                break;
            case 1: {
                if (args[0].equals("GUI")) {
                    new Gui();
                } else {
                    bail("Invalid argument");
                }
                return;
            }
            case 2: {
                var objectMapper = new ObjectMapper();
                one = objectMapper.readValue(new File(args[0]), Entity.class);
                two = objectMapper.readValue(new File(args[1]), Entity.class);
                break;
            }
            default:
                bail("Invalid number of arguments");
                return;
        }

        var printer = System.out;
        var battleResult = new Battle(printer).battle(one, two);
        if (!battleResult.isUnbalanced()) {
            return;
        }

        var winner = battleResult.getWinner();
        if (winner == null) {
            printer.println("There wouldn't be any damage in the combat!");
            return;
        }

        printer.println("Unbalanced combat! " + winner.getName() + " won!");
    }

    private static void bail(String message) {
        System.err.println(message);
        System.exit(1);
    }
}
