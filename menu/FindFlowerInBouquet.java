package menu;

import service.FlowerShop;
public class FindFlowerInBouquet implements MenuCommand {
    private FlowerShop flowerShop;

    public FindFlowerInBouquet(FlowerShop flowerShop) { this.flowerShop = flowerShop; }

    @Override
    public void execute() { flowerShop.findFlowerInBouquet(); }

    @Override
    public String getName() { return "Знайти квітку"; }

    @Override
    public String getDescription() { return "Знайти квітку за діапазоном довжини стебла"; }
}