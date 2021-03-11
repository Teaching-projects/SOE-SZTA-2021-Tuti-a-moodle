import java.util.Scanner;

public class EntityReader implements AutoCloseable {
    private final Scanner scanner;

    public EntityReader() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void close() {
        scanner.close();
    }

    public Entity readEntity() {
        System.out.print("Enter the name of the entity: ");
        String name = scanner.nextLine();
        System.out.print("Enter the lore of " + name + ": ");
        String lore = scanner.nextLine();
        var entity = new Entity(
            readDouble("Enter the health of " + name + ": "),
            readDouble("Enter the attack of " + name + ": "),
            readDouble("Enter the defense of " + name + ": "),
            readDouble("Enter the cooldown of " + name + ": "),
            name,
            lore
        );
        System.out.println();
        return entity;
    }

    private double readDouble(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine();
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException ex) {
                System.out.println(value + " is not a number. Try again.");
            }
        }
    }
}
