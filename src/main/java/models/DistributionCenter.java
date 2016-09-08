package models;


import java.util.List;

public class DistributionCenter {

    private final String name;
    private List<ShippingMethod> shippingMethod;
    private int capacity;

    public DistributionCenter(String name, List<ShippingMethod> shippingMethod, int capacity) {
        this.name = name;
        this.shippingMethod = shippingMethod;
        this.capacity = capacity;
    }

    public String getCenter() {
        return name;
    }

    public List<ShippingMethod> getShippingMethod() {
        return shippingMethod;
    }

    public int getCapacity() {
        return capacity;
    }

}
