abstract class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract double calculateDiscount();

    public double getFinalPrice() {
        return price - calculateDiscount();
    }

    public void displayDetails() {
        System.out.printf("Product: %s%nPrice: %.2f%nDiscount: %.2f%nFinal price: %.2f%n%n",
                name, price, calculateDiscount(), getFinalPrice());
    }
}

class ElectronicsProduct extends Product {
    public ElectronicsProduct(String name, double price) {
        super(name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.10;
    }
}

class ClothingProduct extends Product {
    public ClothingProduct(String name, double price) {
        super(name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.20;
    }
}

public class Assignment2 {
    public static void main(String[] args) {
        Product laptop = new ElectronicsProduct("Laptop", 75000.00);
        Product jacket = new ClothingProduct("Winter Jacket", 5000.00);

        laptop.displayDetails();
        jacket.displayDetails();
    }
}
