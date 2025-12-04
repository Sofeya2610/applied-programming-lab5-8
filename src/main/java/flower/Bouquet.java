package flower;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Bouquet implements Serializable {

    private String name;
    private List<Flower> flowers = new ArrayList<>();
    private List<Accessory> accessories = new ArrayList<>();
    // конструктор який приймає назву, щоб ініціалізувати новий букет
    public Bouquet(String name) {
        this.name = name;
    }

    public void addFlower(Flower flower) {
        this.flowers.add(flower);
    }

    public void addAccessory(Accessory accessory) {
        this.accessories.add(accessory);
    }

    public double calculateTotalPrice() {
        double price = 0;
        for (Flower f : flowers) {
            price += f.getPrice();
        }
        for (Accessory a : accessories) {
            price += a.getPrice();
        }
        return price;
    }

    public void sortFlowersByFreshness(){
        flowers.sort(Comparator.comparing(Flower::getFreshnessDate).reversed());
    }

    public List<Flower> findFlowersByStemLenght(double min, double max) {
        return flowers.stream().filter(f -> f.getStemLength() >= min && f.getStemLength() <= max)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }

    @Override
    public String toString() {
        return "Букет '" + name + "' (Квітів: " + flowers.size() + ", Аксесуарів: " + accessories.size() +
                ", Ціна: " + calculateTotalPrice() + " грн)";
    }
}