package service;

import menu.*;
import menu.command.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("Start FlowerShop program");

        try {
            FlowerShop flowerShop = new FlowerShop(); // виконавець функцій

            ConsoleMenu menu = new ConsoleMenu(flowerShop); // головне меню

            // Створення меню
            menu.addMenuItem("1", new ViewCatalog(flowerShop));
            menu.addMenuItem("2", new AddFlowerToCatalog(flowerShop));
            menu.addMenuItem("3", new CreateBouquet(flowerShop));
            menu.addMenuItem("4", new ViewAllBouquets(flowerShop));
            menu.addMenuItem("5", new EditBouquet(flowerShop));
            menu.addMenuItem("6", new SortInBouquet(flowerShop));
            menu.addMenuItem("7", new FindFlowerInBouquet(flowerShop));
            menu.addMenuItem("8", new DeleteFlowerFromCatalog(flowerShop));
            menu.addMenuItem("0", new SaveAndExit(flowerShop));

            menu.run();
            logger.info("FlowerShop program finished 1");
        } catch (Exception e) {
            logger.error("Fatal error in FlowerShop program: {}", e.getMessage(), e);
        }

        logger.debug("Main method finished, exiting application");
    }
}
