package menu.command;

import service.FlowerShop;
public class SortInBouquet implements MenuCommand {
    private FlowerShop flowerShop;

    public SortInBouquet(FlowerShop flowerShop) { this.flowerShop = flowerShop; }

    @Override
    public void execute() { flowerShop.sortBouquet(); }

    @Override
    public String getName() { return "Відсортувати"; }

    @Override
    public String getDescription() { return "Сортувати квіти за свіжістю"; }
}
