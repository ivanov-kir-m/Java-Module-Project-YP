package Calculator;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    private int people_num = 0;
    final private HashMap<String,Product> products = new HashMap<>();

    private static class ToFewPeople extends Exception {}

    private void get_people_num(){
        final Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("На скольких человек необходимо разделить счёт?");
            try{
                people_num = scanner.nextInt();
                if (people_num < 1) throw new ToFewPeople();
            }
            catch (InputMismatchException|ToFewPeople e){
                System.out.println("Допускается ввод только целых чисел >= 1.");
                scanner.next();
                continue;
            }
            break;
        }
    }

    private void get_products(){
        final Scanner scanner = new Scanner(System.in);
        String product_name;
        double product_price;
        while(true){
            System.out.println(
                    "Добавьте новый товар (например: Груша 10.2) или напишите 'Завершить':"
            );
            product_name = scanner.next().trim();
            if (product_name.equalsIgnoreCase("завершить")){
                scanner.nextLine();
                break;
            }
            try {
                product_price = scanner.nextDouble();
            }
            catch (InputMismatchException e){
                System.out.println("Цена должна быть дробным числом через . или целым числом");
                scanner.nextLine();
                continue;
            }

            // Проверяем и добавляем продукт в список
            if (!products.containsKey(product_name)){
                products.put(product_name, new Product(product_name, product_price));
            }
            else{
                products.get(product_name).prices.add(product_price);
            }
        }
    }

    private void print_products(){
        System.out.println("Добавленные товары:");
        double price_sum = 0;
        for(HashMap.Entry<String, Product> entry : products.entrySet()) {
            Product product = entry.getValue();
            System.out.println(product.name);
            for (double price : product.prices) price_sum += price;
        }
        // TODO: Formater
        System.out.printf("Каждый человек должен запатить по %.2f рублю%n", price_sum/people_num);
    }


    public void run() {
        get_people_num();
        get_products();
        print_products();
    }

}
