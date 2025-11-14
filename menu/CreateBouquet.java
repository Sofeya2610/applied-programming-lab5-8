package menu;

import service.FlowerShop;
public class CreateBouquet implements MenuCommand {
    private FlowerShop flowerShop;

    public CreateBouquet(FlowerShop flowerShop) { this.flowerShop = flowerShop; }

    @Override
    public void execute() { flowerShop.createBouquet(); }

    @Override
    public String getName() { return "Створити"; }

    @Override
    public String getDescription() { return "Створити новий букет"; }
}