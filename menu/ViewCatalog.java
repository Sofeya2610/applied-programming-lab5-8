package menu;

import service.FlowerShop;
public class ViewCatalog implements MenuCommand {
    private FlowerShop flowerShop;

    public ViewCatalog(FlowerShop flowerShop) { this.flowerShop = flowerShop; }

    @Override
    public void execute() { flowerShop.viewCatalog(); }

    @Override
    public String getName() { return "Переглянути"; }

    @Override
    public String getDescription() { return "Переглянути каталог квітів"; }
}