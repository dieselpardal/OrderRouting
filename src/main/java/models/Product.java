package models;

public class Product {

    private final String name;
    private int quantity;

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public void decrementQuantityBy(int quantity) {
        this.quantity -= quantity;
    }
}
