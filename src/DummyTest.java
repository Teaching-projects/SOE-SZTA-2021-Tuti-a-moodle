import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;



import static org.junit.jupiter.api.Assertions.*;

// FIXME: remove this class
public class DummyTest {

       
    @Test
    @DisplayName("Test in Entity")
    void testInConstructor() {
        // Arrange
        Entity entity;

        //Act
        entity = new Entity(100, 50, 40, 2, "Bob", "Bob story");

        // Assert
        assertEquals(100, entity.getHealth(), "Wrong health");
        assertEquals(50, entity.getAttack(), "Wrong attack");
        assertEquals(40, entity.getDefense(), "Wrong defense");
        assertEquals(2, entity.getCooldown(), "Wrong cooldown");
        assertEquals("Bob", entity.getName(), "Wrong name");
        assertEquals("Bob story", entity.getLore(), "Wrong lore");
    }

    @Test
    @DisplayName("Test if health is 0")
    void testHealthIsZero() {
        // Arrange
        Entity entity;

        //Act
        entity = new Entity(0, 50, 40, 2, "Bob", "Bob story");
        
        // Assert
        assertEquals(0, entity.getHealth(), "Wrong health");
    }


    @Test
    @DisplayName("Test if entity is dead")
    void testHealthIsBigger() {
        // Arrange
        Entity entity;

        //Act
        entity = new Entity(0, 20, 60, 2, "Bob", "Bob story");
        
        // Assert
        assertFalse(entity.isAlive(), "Wrong health");
    }

    @Test
    @DisplayName("Test if entity is alive")
    void testEntityAlive() {
        // Arrange
        Entity entity;

        //Act
        entity = new Entity(5, 50, 40, 2, "Bob", "Bob story");
        
        // Assert
        assertTrue(entity.isAlive(), "Wrong health");
    }

    @Test
    @DisplayName("Test if cooldown bigger than 0")
    void testCooldownBiggerThanZero() {
        // Arrange
        Entity entity;

        //Act
        entity = new Entity(100, 50, 40, 2, "Bob", "Bob story");
        
        // Assert
        assertEquals(entity.getCooldown(), 2, "Wrong cooldown");
    }

   
    @Test
    @DisplayName("Test if attack bigger than 0")
    void testAttackBiggerThanZero() {
        // Arrange
        Entity entity;

        //Act
        entity = new Entity(100, 50, 40, 2, "Bob", "Bob story");
        
        // Assert
        assertEquals(entity.getAttack(), 50, "Wrong attack");
    }

    @Test
    @DisplayName("Test if defense bigger than 0")
    void testDefenseBiggerThanZero() {
        // Arrange
        Entity entity;

        //Act
        entity = new Entity(100, 50, 40, 2, "Bob", "Bob story");
        
        // Assert
        assertEquals(entity.getDefense() >= 0, true, "Wrong defense");
    }

    @Test
    @DisplayName("Test if health is below 0")
    void exceptionTestingHealth() {
        // Arrange
        Executable executable;

        //Act
        executable = () -> new Entity(-1, 50, 40, 2, "Bob", "Bob story");

        // Assert
        assertThrows(IllegalArgumentException.class, executable, "Exception handling failed");
    }
  
    
    @Test
    public void dummyTest() {
        assertTrue(true);
    }
}
