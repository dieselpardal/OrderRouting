package algorithm;

import models.Center;
import models.PriorizationStrategy;
import models.Product;
import models.ShippingMethod;

import java.util.List;

public class Request {
    private final List<Center> centers;
    private final ShippingMethod shippingMethod;
    private final PriorizationStrategy priorizationStrategy;
    private final List<Product> products;

    public List<Center> getCenters() {
        return centers;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public PriorizationStrategy getPriorizationStrategy() {
        return priorizationStrategy;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Request(List<Center> centers, ShippingMethod shippingMethod, PriorizationStrategy priorizationStrategy, List<Product> products) {
        this.centers = centers;
        this.shippingMethod = shippingMethod;
        this.priorizationStrategy = priorizationStrategy;
        this.products = products;
    }
}
