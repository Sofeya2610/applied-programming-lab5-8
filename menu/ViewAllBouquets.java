package menu;

import service.FlowerShop;
public class ViewAllBouquets implements MenuCommand {
    private FlowerShop flowerShop;

    public ViewAllBouquets(FlowerShop flowerShop) { this.flowerShop = flowerShop; }

    @Override
    public void execute() { flowerShop.viewAllBouquets(); }

    @Override
    public String getName() { return "Переглянути все"; }

    @Override
    public String getDescription() { return "Переглянути всі букети"; }
}