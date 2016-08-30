package models;


import java.util.List;

public class DistributionCenters {

    private final String name;
    private List <String> shippingMethod;
    private List <Integer> capacity;

    public DistributionCenters(String name, List<String> shippingMethod, List <Integer> capacity) {
        this.name = name;
        this.shippingMethod = shippingMethod;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public List<String> getShippingMethod() {
        return shippingMethod;
    }

    public List <Integer> getCapacity() {
        return capacity;
    }
    public int getCapacityLast() {
        return getCapacity()
                .get(getCapacity().size()-1);
    }

}
