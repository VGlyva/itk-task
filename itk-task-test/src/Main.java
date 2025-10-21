import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        Map<String, Double> amountByProduct = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingDouble(Order::getCost)
                ));

        List<Map.Entry<String, Double>> productsSortedByPrice = amountByProduct.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .toList();

        productsSortedByPrice.forEach(entry ->
                System.out.println("Product: " + entry.getKey() + ", Total Cost: " + entry.getValue())
        );
    }
}