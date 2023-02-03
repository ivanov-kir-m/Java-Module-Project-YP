package Calculator;

import java.util.ArrayList;
import java.util.List;

public class Product {
    /**
     * Добавленные цены на товар
     */
    public final List<Double> prices = new ArrayList<>();
    /**
     * Название продукта
     */
    public final String name;

    Product(String name, double price){
        this.name = name;
        this.prices.add(price);
    }
}
