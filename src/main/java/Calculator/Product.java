package Calculator;

import java.util.ArrayList;
import java.util.List;

public class Product {
    public List<Double> prices = new ArrayList<>();
    public String name;

    Product(String name, double price){
        this.name = name;
        this.prices.add(price);
    }
}
