package algorithm;

import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderRouting {

    public static final String OK = "Ok";
    public static final String ORDER_CANNOT_BE_FULFILLED = "Order cannot be fulfilled.";
    private List<DistributionCenters> distributionCenters;
    private ShippingMethod sm = new ShippingMethod();

    public OrderRouting() {
        tableOfDistribution();
    }

    public List<DistributionCenters> getCenters() {
        return distributionCenters;
    }

    private void tableOfDistribution() {
        List<String> shipping = new ArrayList<>();
        distributionCenters = new ArrayList<>();
        List<Integer> capacity = new ArrayList<>();
        shipping.add(sm.getDHL());
        shipping.add(sm.getFedex());
        capacity.add(15);
        distributionCenters.add(new DistributionCenters("Brazil", shipping, capacity));
        capacity = new ArrayList<>();
        shipping = new ArrayList<>();
        shipping.add(sm.getDHL());
        shipping.add(sm.getFedex());
        shipping.add(sm.getUPS());
        capacity.add(10);
        distributionCenters.add(new DistributionCenters("France", shipping, capacity));
        shipping = new ArrayList<>();
        capacity = new ArrayList<>();
        shipping.add(sm.getFedex());
        shipping.add(sm.getUPS());
        capacity.add(10);
        distributionCenters.add(new DistributionCenters("South Africa", shipping, capacity));
        shipping = new ArrayList<>();
        capacity = new ArrayList<>();
        shipping.add(sm.getDHL());
        capacity.add(20);
        distributionCenters.add(new DistributionCenters("China", shipping, capacity));
        shipping = new ArrayList<>();
        capacity = new ArrayList<>();
        shipping.add(sm.getFedex());
        capacity.add(5);
        distributionCenters.add(new DistributionCenters("Canada", shipping, capacity));
    }

    private Optional<DistributionCenters> hasDistribution(String center, List<DistributionCenters> distributionCenters) {
        return distributionCenters.stream()
                .filter(p -> p.getName().equals(center))
                .findFirst();
    }

    private boolean hasShippingMethod(String shipping, Optional<DistributionCenters> distributionCenters) {
        return distributionCenters.get()
                .getShippingMethod()
                .stream()
                .filter(p -> p.equals(shipping))
                .findFirst()
                .isPresent();
    }

    private int getMaxQuantity(Center center, Product product) {
        return center.getQuantity() <= product.getQuantity() ? center.getQuantity() : product.getQuantity();
    }

    private int getMaxCapacityLast(int quanty, Optional<DistributionCenters> distributionCentersOpt) {
        return quanty > distributionCentersOpt.get().getCapacityLast() ? distributionCentersOpt.get().getCapacityLast() : quanty;
    }

    private void newCapacityOfDistributionCenter(Optional<DistributionCenters> distributionCentersOpt, Center center, int amount) {
        final int finalAmount = amount;
        this.distributionCenters.stream()
                .map(p -> p.getName().equals(center.getCenter()) ?
                                p.getCapacity().add(distributionCentersOpt.get().getCapacityLast() - finalAmount)
                                : this.distributionCenters
                )
                .collect(Collectors.toList());
    }

    private boolean findProductFromCenter(Center center, Product product) {
        return center.getProduct().equals(product.getName());
    }

    public Response solver(Request request) {
        List<Center> centersCheckOut = new ArrayList<>();
        //String estra = request.getPriorizationStrategy().getName();
        for (Center center : request.getCenters()) {
            for (Product product : request.getProducts()) {
                if (findProductFromCenter(center, product)) {
                    Optional<DistributionCenters> distributionCentersOpt = hasDistribution(center.getCenter(), this.distributionCenters);
                    if (hasShippingMethod(request.getShippingMethod(), distributionCentersOpt)) {
                        int amount = getMaxQuantity(center, product);
                        if (amount > 0) {
                            product.decrementQuantityBy(amount );
                            amount = getMaxCapacityLast(amount, distributionCentersOpt);
                            newCapacityOfDistributionCenter(distributionCentersOpt, center, amount);
                            centersCheckOut.add(new Center(center.getCenter(), center.getProduct(), amount));
                            // System.out.println("TRUE " + center.getCenter() + " e " + center.getProduct() + " e " + quanty);
                        }
                    }
                }
            }
        }

        // if request.getProducts() tem algum maior que 0
        // retorna new Response(invalido)

        return new Response(centersCheckOut);
    }

    public String hasFulFilled(Request request) {
        return request.getCenters().size() == request.getCenters()
                .stream()
                .filter(p -> (distributionCenters.stream()
                        .filter((d) -> d.getName().equals(p.getCenter()))
                        .filter((e) -> e.getShippingMethod().stream()
                                .filter((f) -> f.equals(request.getShippingMethod()))
                                .count() > 0)
                        .findFirst()
                        .isPresent()))
                .count() ? OK : ORDER_CANNOT_BE_FULFILLED;
    }
}
