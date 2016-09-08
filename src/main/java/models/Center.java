package models;

public class Center {

    private final String wareHouse;
    private final String product;
    private final int quantity;

    public Center(String wareHouse, String product, int quantity) {
        this.wareHouse = wareHouse;
        this.product = product;
        this.quantity = quantity;
    }

    public String getWareHouse() {
        return wareHouse;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
