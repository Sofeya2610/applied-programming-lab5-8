package flower;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bouquet implements Serializable {

    private List<Flower> flowers;
    private List<Accessory> accessories;
    private String name;

    public Bouquet(String name) {
        this.name = name;
        this.flowers = new ArrayList<>();
        this.accessories = new ArrayList<>();
    }

    public void addFlower(Flower flower) {
        this.flowers.add(flower);
    }

    // (Метод для додавання аксесуару)
    public void addAccessory(Accessory accessory) {
        this.accessories.add(accessory);
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (Flower f : flowers) {
            total += f.getPrice();
        }
        for (Accessory a : accessories) {
            total += a.getPrice();
        }
        return total;
    }

    public String getName() {
        return name;
    }
}
