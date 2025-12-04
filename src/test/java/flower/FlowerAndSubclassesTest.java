package flower;

import flower.Accessory;
import flower.Chamomile;
import flower.Rose;
import flower.Tulip;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class FlowerAndSubclassesTest {

    @Test
    void testRoseCreation() {
        LocalDate date = LocalDate.now();
        Rose rose = new Rose("R1", 10, 20, "Red", date, true, "Cup");

        assertEquals("R1", rose.getName());
        assertEquals(10, rose.getPrice());
        assertTrue(rose.hasThorns());
        assertEquals("Cup", rose.getBudShape());
    }

    @Test
    void testTulipCreation() {
        Tulip tulip = new Tulip("T1", 5, 10, "Pink", LocalDate.now(), "Sharp", true);

        assertEquals("T1", tulip.getName());
        assertTrue(tulip.isDouble());
        assertEquals("Sharp", tulip.getPetalShape());
    }

    @Test
    void testChamomileCreation() {
        Chamomile cham = new Chamomile("C1", 2, 5, "White", LocalDate.now(), 1.5, 20);

        assertEquals(20, cham.getPetalCount());
        assertEquals(1.5, cham.getCoreSize());
    }

    @Test
    void testAccessoryCreation() {
        Accessory acc = new Accessory("Bow", 15.5);
        assertEquals("Bow", acc.getName());
        assertEquals(15.5, acc.getPrice());
    }
}
