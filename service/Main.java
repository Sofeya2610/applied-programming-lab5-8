import menu.*;
import service.FlowerShop;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        FlowerShop flowerShop = new FlowerShop(); // виконавець функцій

        ConsoleMenu menu = new ConsoleMenu(); //викликач менюшки

        //Створення меню
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
    }
}
