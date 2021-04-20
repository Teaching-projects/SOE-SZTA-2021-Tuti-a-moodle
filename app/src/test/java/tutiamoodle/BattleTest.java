package tutiamoodle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {
    private PrintStream printer;

    @BeforeEach
    public void printerSetup(){
        printer = new PrintStream(new OutputStream() {
                @Override
                public void	write(int b) {
                    // noop
                }
            });
    }

    @Test
    @DisplayName("Test for battle function")
    public void testForBattle1() {
        // Arrange
        Entity entity1 = new Entity(100, 50, 35, 2, "Bob", "Bob story");
        Entity entity2 = new Entity(90, 40, 30, 2, "Mike", "Mike story");

        Battle battle = new Battle(printer);

        // //Act
        battle.battle(entity1, entity2);

        // Assert
        assertTrue(entity1.getHealth() > entity2.getHealth(), "Wrong health");
    }

    @ParameterizedTest
    @ValueSource(doubles = {90, 50, 70, 80, 40, 45.7, 27.9})
    @DisplayName("Test for battle function 2")
    public void testForBattle2(double health) {
        // Arrange
        Entity entity1 = new Entity(100, 50, 35, 2, "Bob", "Bob story");
        Entity entity2 = new Entity(health, 40, 30, 2, "Mike", "Mike story");

        Battle battle = new Battle(printer);

        //Act
        battle.battle(entity1, entity2);

        // Assert
        assertTrue(entity1.isAlive(), "Bob needs to be alive!");
    }

    @ParameterizedTest
    @ValueSource(doubles = {17, 30, 50, 40, 15, 45.7, 27.9})
    @DisplayName("Test for battle function 3")
    public void testForBattle3(double attack) {
        // Arrange
        Entity entity1 = new Entity(100, 50, 35, 2, "Bob", "Bob story");
        Entity entity2 = new Entity(90, attack, 30, 2, "Mike", "Mike story");

        Battle battle = new Battle(printer);

        //Act
        battle.battle(entity1, entity2);

        // Assert
        assertTrue(entity1.getHealth() > entity2.getHealth(), "Wrong health");
    }

    @ParameterizedTest
    @ValueSource(doubles = {17, 30, 20, 30, 15, 15.7, 27.9})
    @DisplayName("Test for battle function 4")
    public void testForBattle4(double defense) {
        // Arrange
        Entity entity1 = new Entity(100, 50, 35, 2, "Bob", "Bob story");
        Entity entity2 = new Entity(90, 40, defense, 2, "Mike", "Mike story");

        Battle battle = new Battle(printer);

        //Act
        battle.battle(entity1, entity2);

        // Assert
        assertTrue(entity1.isAlive(), "Bob needs to be alive!");
    }

    @ParameterizedTest
    @ValueSource(doubles = {17, 30, 20, 30, 15, 15.7, 27.9})
    @DisplayName("Test for battle function 5 - Test the winner")
    public void testForBattle5(double defense) {
        // Arrange
        Entity entity1 = new Entity(100, 50, 35, 2, "Bob", "Bob story");
        Entity entity2 = new Entity(90, 40, defense, 2, "Mike", "Mike story");
        
        Battle battle = new Battle(printer);

        //Act
        var battleResult = battle.battle(entity1, entity2);

        //Assert
        assertEquals("Bob", battleResult.getWinner().getName(), "Bob needs to win!");
    }
 }
