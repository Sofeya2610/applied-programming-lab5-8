package menu;

import org.junit.jupiter.api.Test;
import service.FlowerShop;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleMenuTest {

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @Test
    void testRunExitImmediately() {
        provideInput("0\n");

        FlowerShop shop = new FlowerShop();
        ConsoleMenu menu = new ConsoleMenu("Головне меню", "Тестове меню", shop);
        menu.run();

        assertTrue(true);
    }

    @Test
    void testRunHelpThenExit() {
        provideInput("help\n0\n");

        FlowerShop shop = new FlowerShop();
        ConsoleMenu menu = new ConsoleMenu("Головне меню", "Тестове меню", shop);
        menu.run();

        assertTrue(true);
    }

    @Test
    void testRunInvalidCommandThenExit() {
        provideInput("99\n0\n");

        FlowerShop shop = new FlowerShop();
        ConsoleMenu menu = new ConsoleMenu("Головне меню", "Тестове меню", shop);
        menu.run();

        assertTrue(true);
    }
}
