import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Assignment4 {
        public static void main(String[] args) {
                List<Product> products = Arrays.asList(
                                new Product("Laptop", "Electronics", 75000),
                                new Product("Mouse", "Electronics", 800),
                                new Product("Chair", "Furniture", 2500),
                                new Product("Notebook", "Stationery", 150),
                                new Product("Desk", "Furniture", 5000));

                List<Product> productsAbove1000 = products.stream()
                                .filter(product -> product.price() > 1000)
                                .collect(Collectors.toList());
                double totalPrice = products.stream()
                                .mapToDouble(Product::price)
                                .sum();
                Product highestPricedProduct = products.stream()
                                .max((first, second) -> Double.compare(first.price(), second.price()))
                                .orElseThrow();
                List<String> sortedProductNames = products.stream()
                                .map(Product::name)
                                .sorted()
                                .collect(Collectors.toList());
                Map<String, Long> productsByCategory = products.stream()
                                .collect(Collectors.groupingBy(Product::category, Collectors.counting()));

                System.out.println("Products with price greater than 1000: " + productsAbove1000);
                System.out.println("Total price: " + totalPrice);
                System.out.println("Highest-priced product: " + highestPricedProduct);
                System.out.println("Product names alphabetically: " + sortedProductNames);
                System.out.println("Products in each category: " + productsByCategory);
        }

        record Product(String name, String category, double price) {
        }
}
