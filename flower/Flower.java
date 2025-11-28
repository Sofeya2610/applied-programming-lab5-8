package flower;
import java.io.Serializable;
import java.time.LocalDate;

public abstract class Flower implements Serializable {
    private String name;
    private double price;
    private double stemLength;
    private String color;
    private LocalDate freshnessDate;

    public Flower(String name, double price, double stemLength, String color, LocalDate freshnessDate) {
        this.name = name;
        this.price = price;
        this.stemLength = stemLength;
        this.color = color;
        this.freshnessDate = freshnessDate;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getStemLength() {
        return stemLength;
    }

    public String getColor() {
        return color;
    }

    public LocalDate getFreshnessDate() {
        return freshnessDate;
    }
}
