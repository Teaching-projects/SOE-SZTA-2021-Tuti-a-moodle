import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class EntityReader implements AutoCloseable {
    private final PrintStream printer;
    private final Scanner scanner;

    public EntityReader() {
        printer = System.console() != null
            ? System.out
            : new PrintStream(new OutputStream() {
                @Override
                public void	write(int b) {
                    // noop
                }
            });
        scanner = new Scanner(System.in);
    }

    @Override
    public void close() {
        scanner.close();
    }

    public Entity readEntity() {
        printer.print("Enter the name of the entity: ");
        String name = scanner.nextLine();
        printer.print("Enter the lore of " + name + ": ");
        String lore = scanner.nextLine();
        var entity = new Entity(
            readDouble("Enter the health of " + name + ": "),
            readDouble("Enter the attack of " + name + ": "),
            readDouble("Enter the defense of " + name + ": "),
            readDouble("Enter the cooldown of " + name + ": "),
            name,
            lore
        );
        printer.println();
        return entity;
    }

    private double readDouble(String message) {
        while (true) {
            printer.print(message);
            String value = scanner.nextLine();
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException ex) {
                printer.println(value + " is not a number. Try again.");
            }
        }
    }
}
