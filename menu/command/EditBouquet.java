package menu.command;

import service.FlowerShop;

public class EditBouquet implements MenuCommand {
    private FlowerShop flowerShop;

    public EditBouquet(FlowerShop flowerShop) { this.flowerShop = flowerShop; }

    @Override
    public void execute() { flowerShop.editBouquet(); }

    @Override
    public String getName() { return "Змінити"; }

    @Override
    public String getDescription() { return "Редагувати букет"; }
}