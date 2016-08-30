package models;

public class Center {

    private final String center;
    private final String product;
    private final int quantity;

    public Center(String center, String product, int quantity) {
        this.center = center;
        this.product = product;
        this.quantity = quantity;
    }

    public String getCenter() {
        return center;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
