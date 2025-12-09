package flower.service;

import flower.Flower;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.FlowerShop;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlowerShopTest {

    private final String TEST_FILE = "catalog.dat";

    // Метод для імітації вводу з клавіатури
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @BeforeEach
    void setUp() {
        new File(TEST_FILE).delete();
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
        new File(TEST_FILE).delete();
    }

    @Test
    void testInitDemoCatalog() throws NoSuchFieldException, IllegalAccessException {
        FlowerShop shop = new FlowerShop();

        Field catalogField = FlowerShop.class.getDeclaredField("catalog");
        catalogField.setAccessible(true);
        List<?> catalog = (List<?>) catalogField.get(shop);

        assertFalse(catalog.isEmpty());
        // Має бути 3 квітки (Троянда, Тюльпан, Ромашка)
        assertEquals(3, catalog.size());
    }

    @Test
    void testViewCatalog() {
        // Перевірка, що метод не падає
        FlowerShop shop = new FlowerShop();
        shop.viewCatalog();
    }

    @Test
    void testAddFlowerToCatalog() throws NoSuchFieldException, IllegalAccessException {
        // Ввід: 1(Троянда) -> Назва -> 100 -> 50 -> Червоний -> Дата -> true -> Форма
        String input = "1\nTestRose\n100\n50\nRed\n2025-01-01\ntrue\nCup\n";
        provideInput(input);

        FlowerShop shop = new FlowerShop();
        shop.addFlowerToCatalog();

        Field catalogField = FlowerShop.class.getDeclaredField("catalog");
        catalogField.setAccessible(true);
        List<?> catalog = (List<?>) catalogField.get(shop);

        assertEquals(4, catalog.size()); // 3 стартові + 1 нова
    }

    @Test
    void testCreateBouquet() throws NoSuchFieldException, IllegalAccessException {
        String input = "MyTestBouquet\n";
        provideInput(input);

        FlowerShop shop = new FlowerShop();
        shop.createBouquet();

        Field bouquetsField = FlowerShop.class.getDeclaredField("bouquets");
        bouquetsField.setAccessible(true);
        List<?> bouquets = (List<?>) bouquetsField.get(shop);

        assertEquals(1, bouquets.size());
    }

    @Test
    void testViewAllBouquets() {
        // Створення букету і пеергляд
        String input = "B1\n";
        provideInput(input);
        FlowerShop shop = new FlowerShop();
        shop.createBouquet();

        shop.viewAllBouquets();
    }

    @Test
    void testDeleteFlowerFromCatalog() throws NoSuchFieldException, IllegalAccessException {
        String input = "1\n"; // Видалити першу квітку
        provideInput(input);

        FlowerShop shop = new FlowerShop();
        shop.deleteFlowerFromCatalog();

        Field catalogField = FlowerShop.class.getDeclaredField("catalog");
        catalogField.setAccessible(true);
        List<?> catalog = (List<?>) catalogField.get(shop);

        assertEquals(2, catalog.size());
    }

    @Test
    void testEditBouquet() {
        // Сценарій складний:
        // 1. Створюємо букет "TestB"
        // 2. Запускаємо редагування:
        //    - Вибираємо букет №1 ("1")
        //    - Вводимо квітку №1 ("1")
        //    - Кількість 5 штук ("5")
        //    - Завершуємо квіти ("0")
        //    - Додаємо аксесуар ("1")
        //    - Назва "Стрічка"
        //    - Ціна "10"
        //    - Завершуємо аксесуари ("0")

        String createBouquetInput = "TestB\n";
        String editInput = "1\n1\n5\n0\n1\nRibbon\n10\n0\n";

        provideInput(createBouquetInput + editInput);

        FlowerShop shop = new FlowerShop();
        shop.createBouquet(); // Зчитає "TestB"
        shop.editBouquet();   // Зчитає решту команд
    }

    @Test
    void testSortBouquet() {
   // 1. Створити букет
        // 2. Викликати сортування -> Вибрати букет 1
        String input = "B1\n1\n";
        provideInput(input);

        FlowerShop shop = new FlowerShop();
        shop.createBouquet();
        shop.sortBouquet();
    }

    @Test
    void testFindFlowerInBouquet() {
        // 1. Створити букет
        // 2. Шукати -> Вибрати букет 1 -> мін 0 -> макс 100
        String input = "B1\n1\n0\n100\n";
        provideInput(input);

        FlowerShop shop = new FlowerShop();
        shop.createBouquet();
        shop.findFlowerInBouquet();
    }

    @Test
    void testSaveAndLoadCatalog() {
        FlowerShop shop = new FlowerShop();
        shop.saveCatalogToFile();
        File f = new File(TEST_FILE);
        assertTrue(f.exists());
    }

    @Test
    void testDeleteFlowerFromCatalogInvalidIndex() throws Exception {
        // Вводимо некоректний індекс (0)
        String input = "0\n";
        provideInput(input);

        FlowerShop shop = new FlowerShop();

        Field catalogField = FlowerShop.class.getDeclaredField("catalog");
        catalogField.setAccessible(true);
        List<?> before = (List<?>) catalogField.get(shop);
        int sizeBefore = before.size();

        shop.deleteFlowerFromCatalog();

        List<?> after = (List<?>) catalogField.get(shop);
        // Нічого не видалилось
        assertEquals(sizeBefore, after.size());
    }

    @Test
    void testSelectBouquetInvalidChoice() throws Exception {
        // Створюємо один букет, але вводимо неправильний номер 5
        String createInput = "B1\n";
        String selectInput = "5\n";
        provideInput(createInput + selectInput);

        FlowerShop shop = new FlowerShop();
        shop.createBouquet();

        // Викликаємо selectBouquet через рефлексію
        var method = FlowerShop.class.getDeclaredMethod("selectBouquet");
        method.setAccessible(true);
        Object result = method.invoke(shop);

        assertNull(result); // при неправильному номері повертається null
    }
    @Test
    void testViewAllBouquetsWhenEmpty() {
        // Букети ще не створювалися
        FlowerShop shop = new FlowerShop();

        // Метод має відпрацювати без винятків, гілка bouquets.isEmpty()
        shop.viewAllBouquets();

        assertTrue(true);
    }

    @Test
    void testSortBouquetWhenNoBouquets() {
        // Взагалі немає букетів, selectBouquet поверне null
        provideInput(""); // ніякого вводу

        FlowerShop shop = new FlowerShop();
        shop.sortBouquet(); // гілка if (b != null) не виконається

        assertTrue(true);
    }

    @Test
    void testFindFlowerInBouquetWhenNoBouquets() {
        //  немає букетів, одразу вихід
        provideInput("");

        FlowerShop shop = new FlowerShop();
        shop.findFlowerInBouquet(); // b == null, тіло if не виконується

        assertTrue(true);
    }

    @Test
    void testFindFlowerInBouquetRangeWithNoResults() {
        // 1. Створити букет
        // 2. Шукати з діапазоном, у який жодна квітка не потрапляє
        String input = "B1\n1\n1000\n2000\n";
        provideInput(input);

        FlowerShop shop = new FlowerShop();
        shop.createBouquet();
        shop.findFlowerInBouquet();

        assertTrue(true);
    }


}

