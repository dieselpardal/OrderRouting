package models;

import java.util.List;

public class Request {
    private final List<Center> centers;
    private final String shippingMethod;
    private final String priorizationStrategy;
    private final List<Product> products;

    public List<Center> getCenters() {
        return centers;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public String getPriorizationStrategy() {
        return priorizationStrategy;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Request(List<Center> centers, String shippingMethod, String priorizationStrategy, List<Product> products) {
        this.centers = centers;
        this.shippingMethod = shippingMethod;
        this.priorizationStrategy = priorizationStrategy;
        this.products = products;
    }
}
