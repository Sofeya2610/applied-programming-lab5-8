package flower;

import flower.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BouquetTest {

    private Bouquet bouquet;
    private Flower freshRose;
    private Flower oldTulip;
    private Accessory ribbon;

    @BeforeEach
    void setUp() {
        bouquet = new Bouquet("Test Bouquet");

        // Свіжа квітка (сьогодні)
        freshRose = new Rose("Rose 1", 100.0, 50.0, "Red", LocalDate.now(), true, "Cup");
        // Стара квітка (5 днів тому)
        oldTulip = new Tulip("Tulip 1", 50.0, 30.0, "Yellow", LocalDate.now().minusDays(5), "Simple", false);

        ribbon = new Accessory("Ribbon", 20.0);

        bouquet.addFlower(freshRose);
        bouquet.addFlower(oldTulip);
        bouquet.addAccessory(ribbon);
    }

    @Test
    @DisplayName("Розрахунок повної вартості букету (квіти + аксесуари)")
    void testCalculateTotalPrice() {
        // 100 + 50 + 20 = 170
        assertEquals(170.0, bouquet.calculateTotalPrice(), 0.01);
    }

    @Test
    @DisplayName("Сортування квітів за свіжістю (від нових до старих)")
    void testSortFlowersByFreshness() {
        bouquet.sortFlowersByFreshness();
        List<Flower> sorted = bouquet.getFlowers();

        // Першою має бути свіжа троянда, другим - старий тюльпан
        assertEquals(freshRose, sorted.get(0));
        assertEquals(oldTulip, sorted.get(1));
    }

    @Test
    @DisplayName("Пошук квітки за діапазоном довжини стебла")
    void testFindFlowersByStemLength() {
        // Шукаємо від 40 до 60 см (має знайти тільки троянду 50см)
        List<Flower> result = bouquet.findFlowersByStemLenght(40, 60);

        assertEquals(1, result.size());
        assertEquals(freshRose, result.get(0));
    }

    @Test
    @DisplayName("Пошук квітки: нічого не знайдено")
    void testFindFlowersByStemLengthEmpty() {
        // Пошук від 10 до 20 см
        List<Flower> result = bouquet.findFlowersByStemLenght(10, 20);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Отримання назви та списків")
    void testGetters() {
        assertEquals("Test Bouquet", bouquet.getName());
        assertEquals(2, bouquet.getFlowers().size());
        assertEquals(1, bouquet.getAccessories().size());
        assertTrue(bouquet.toString().contains("Test Bouquet"));
    }
}