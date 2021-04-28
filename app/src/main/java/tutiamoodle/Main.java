package tutiamoodle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws IOException {
        final Entity one;
        final Entity two;
        final var objectMapper = objectMapper();
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
                if (args[0].equals("gui")) {
                    new Gui(objectMapper);
                } else if (args[0].equals("adventure")) {
                    new Adventure(objectMapper);
                } else {
                    bail("Invalid argument");
                }
                return;
            }
            case 2: {
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
        if (battleResult.isBalanced()) {
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

    private static ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();

        var deserialization = new SimpleModule();
        deserialization.addDeserializer(Entity.class, new EntityDeserializer());
        deserialization.addDeserializer(MapCell.class, new MapCellDeserializer());
        objectMapper.registerModule(deserialization);

        return objectMapper;
    }
}
