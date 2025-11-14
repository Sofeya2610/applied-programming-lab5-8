package menu;

import service.FlowerShop;

public class DeleteFlowerFromCatalog implements MenuCommand {
    private FlowerShop flowerShop;

    public DeleteFlowerFromCatalog(FlowerShop flowerShop) { this.flowerShop = flowerShop; }

    @Override
    public void execute() { flowerShop.deleteFlowerFromCatalog(); }

    @Override
    public String getName() { return "Видалити"; }

    @Override
    public String getDescription() { return "Видалити квітку з каталогу"; }
}