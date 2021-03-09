import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IOException {
        final Entity one;
        final Entity two;
        switch (args.length) {
            case 0:
                try (Scanner in = new Scanner(System.in)) {
                    one = readEntity(in);
                    System.out.println();
                    two = readEntity(in);
                }
                break;
            case 2: {
                var objectMapper = new ObjectMapper();
                one = objectMapper.readValue(new File(args[0]), Entity.class);
                two = objectMapper.readValue(new File(args[1]), Entity.class);
                break;
            }
            default:
                System.err.println("Invalid number of arguments");
                System.exit(1);
                return;
        }

        Battle.battle(one, two);
    }

    static Entity readEntity(Scanner in) {
        System.out.print("Enter the name of the entity: ");
        String name = in.nextLine();
        System.out.print("Enter the lore of " + name + ": ");
        String lore = in.nextLine();
        return new Entity(
            readDouble(in, "Enter the health of " + name + ": "),
            readDouble(in, "Enter the attack of " + name + ": "),
            readDouble(in, "Enter the defense of " + name + ": "),
            readDouble(in, "Enter the cooldown of " + name + ": "),
            name,
            lore
        );
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
