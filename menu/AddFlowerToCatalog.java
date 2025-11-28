package menu;

import service.FlowerShop;

public class AddFlowerToCatalog implements MenuCommand {
    private FlowerShop flowerShop;

    public AddFlowerToCatalog(FlowerShop flowerShop) { this.flowerShop = flowerShop; }

    @Override
    public void execute() { flowerShop.addFlowerToCatalog(); }

    @Override
    public String getName() { return "Додати"; }

    @Override
    public String getDescription() { return "Додати нову квітку до каталогу"; }
}