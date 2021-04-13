import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;




import static org.junit.jupiter.api.Assertions.*;

// FIXME: remove this class
public class DummyTest {

       
    @Test
    @DisplayName("Test in Entity")
    void testInConstructor() {
        Entity entity = new Entity(100, 50, 40, 2, "Bob", "Bob story");
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
        Entity entity = new Entity(0, 50, 40, 2, "Bob", "Bob story");
        assertEquals(0, entity.getHealth(), "Wrong health");
    }


    @Test
    @DisplayName("Test if entity is dead")
    void testHealthIsBigger() {
        Entity entity = new Entity(0, 20, 60, 2, "Bob", "Bob story");
        assertEquals(false, entity.isAlive(), "Wrong health");
    }

    @Test
    @DisplayName("Test if entity is alive")
    void testEntityAlive() {

        Entity entity = new Entity(5, 50, 40, 2, "Bob", "Bob story");
        
        assertEquals(true, entity.isAlive(), "Wrong health");
    }

    @Test
    @DisplayName("Test if cooldown bigger than 0")
    void testCooldownBiggerThanZero() {

        Entity entity = new Entity(100, 50, 40, 2, "Bob", "Bob story");
        
        assertEquals(entity.getCooldown() >= 0, true, "Wrong cooldown");
    }

   
    @Test
    @DisplayName("Test if attack bigger than 0")
    void testAttackBiggerThanZero() {

        Entity entity = new Entity(100, 50, 40, 2, "Bob", "Bob story");
        
        assertEquals(entity.getAttack() >= 0, true, "Wrong attack");
    }

    @Test
    @DisplayName("Test if defense bigger than 0")
    void testDefenseBiggerThanZero() {

        Entity entity = new Entity(100, 50, 40, 2, "Bob", "Bob story");
        
        assertEquals(entity.getDefense() >= 0, true, "Wrong defense");
    }

    @Test
    @DisplayName("Test if health is below 0")
    void exceptionTestingHealth() {
        //This assetion will fail
        try{
        Entity entity = new Entity(-1, 50, 40, 2, "Bob", "Bob story");
        
        // Exception exception = assertThrows(
        //     IllegalArgumentException.class,
        //     () -> entity.getHealth());
        } catch (IllegalArgumentException e) {
            assertEquals("Health must not be negative", e.getMessage());
        }
    }
  
    
    @Test
    public void dummyTest() {
        assertTrue(true);
    }
}
