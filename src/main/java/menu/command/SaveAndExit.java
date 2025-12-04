package menu.command;

import service.FlowerShop;

public class SaveAndExit implements MenuCommand {
    private FlowerShop flowerShop;

    public SaveAndExit(FlowerShop flowerShop) { this.flowerShop = flowerShop; }

    @Override
    public void execute() { flowerShop.saveCatalogToFile(); }

    @Override
    public String getName() { return "Зберегти"; }

    @Override
    public String getDescription() { return "Зберегти каталог і вийти"; }
}