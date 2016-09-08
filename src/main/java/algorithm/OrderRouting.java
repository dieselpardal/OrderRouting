package algorithm;

import models.*;

import java.util.ArrayList;
import java.util.List;

public class OrderRouting {

    private List<DistributionCenter> distributionCenters;

    public OrderRouting() {
        TableOfDistribution tableOfDistribution = new TableOfDistribution();
        distributionCenters = tableOfDistribution.table();
    }

    private boolean hasShippingOrLimitCapacityMethod(Request request, DistributionCenter distributionCenters) {
        return distributionCenters.getShippingMethod()
                .stream()
                .anyMatch(shippingMethod -> shippingMethod == request.getShippingMethod());
    }

    private boolean hasLimitCapacity(Request request) {
        return request.getProducts()
                .stream()
                .allMatch(p -> p.getQuantity() == 0);
    }

    private boolean findProductFromCenter(Center center, Product product) {
        return center.getProduct().equals(product.getName());
    }

    private boolean hasHigh(int amount) {
        return amount > 0;
    }

    public Response solver(Request request, DistributionToCapacity distributionToCapacity) {
        List<Center> centersCheckOut = new ArrayList<>();
        PriorizationStrategyProcess priorizationStrategyProcess = new PriorizationStrategyProcess();
        List<Center> newCenters = priorizationStrategyProcess.newCentersPriorization(request, distributionCenters);
        List<Capacity> capacity = distributionToCapacity.copy(distributionCenters);
        for (Center center : newCenters) {
          for (DistributionCenter d : distributionCenters) {
            if (center.getWareHouse().equals(d.getCenter()) && hasShippingOrLimitCapacityMethod(request, d)) {
              for (Product product : request.getProducts()) {
                if (findProductFromCenter(center, product)) {
                  int amount = distributionToCapacity.getMaxQuantity(center, product);
                  if (hasHigh(amount)) {
                    amount = distributionToCapacity.getMaxCapacityLast(amount, capacity, d.getCenter());
                    product.decrementQuantityBy(amount);
                    capacity.add(new Capacity(d.getCenter(), d.getCapacity() - amount));
                    centersCheckOut.add(new Center(center.getWareHouse(), product.getName(), amount));
                  }
                }
              }
            }
          }
        }
        return hasLimitCapacity(request) ? new Response(centersCheckOut) : null;
    }

}
