package Calculator;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    /**
     * Кол-во людей за столом
     */
    private int peopleNum = 0;
    /**
     * Список продуктов ключ <b>Название продукта</b>, значение <b>Продукт</b>.
     */
    private final HashMap<String,Product> products = new HashMap<>();

    /**
     * Exeption: Слишком мало людей
     */
    private static class ToFewPeople extends Exception {}

    /**
     * Функция получения кол-ва людей за столом
     */
    private void getPeopleNum(){
        final Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("На скольких человек необходимо разделить счёт?");
            try{
                peopleNum = scanner.nextInt();
                if (peopleNum < 1) throw new ToFewPeople();
            }
            catch (InputMismatchException|ToFewPeople e){
                System.out.println("Допускается ввод только целых чисел >= 1.");
                scanner.nextLine(); // Забираем все из буфера
                continue;
            }
            break;
        }
    }

    /**
     * Функция получения списка продуктов
     */
    private void getProducts(){
        final Scanner scanner = new Scanner(System.in);
        String productName;
        double productPrice;
        while(true){
            System.out.println(
                    "Добавьте новый товар (например: Груша 10.2) или напишите 'Завершить':"
            );
            productName = scanner.next().trim();
            if (productName.equalsIgnoreCase("завершить")){
                scanner.nextLine(); // Забираем все из буфера
                break;
            }
            try {
                productPrice = scanner.nextDouble();
            }
            catch (InputMismatchException e){
                System.out.println("Цена должна быть дробным числом через . или целым числом");
                scanner.nextLine(); // Забираем все из буфера
                continue;
            }

            // Проверяем и добавляем продукт в список, если продукт уже есть сохраняем цену
            if (!products.containsKey(productName)){
                products.put(productName, new Product(productName, productPrice));
            }
            else{
                products.get(productName).prices.add(productPrice);
            }
        }
    }

    /**
     * Функция рассчета стоймости и вывода списка продуктов
     */
    private void printProducts(){
        System.out.println("Добавленные товары:");
        double priceSum = 0;
        for(HashMap.Entry<String, Product> entry : products.entrySet()) {
            Product product = entry.getValue();
            System.out.println(product.name);
            for (double price : product.prices) priceSum += price;
        }
        double priceForEveryPeople = priceSum / peopleNum;
        System.out.printf(
                "Каждый человек должен запатить по %.2f %s",
                priceForEveryPeople,
                Formatter.getRubText(priceForEveryPeople)
        );
    }

    /**
     * Запуск калькулятора товаров
     */
    public void run() {
        getPeopleNum();
        getProducts();
        printProducts();
    }

}
