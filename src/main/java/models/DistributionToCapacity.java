package models;

import java.util.List;
import java.util.stream.Collectors;

public class DistributionToCapacity {

    public List<Capacity> copy(List<DistributionCenter> distributionCenter) {
        return distributionCenter.stream()
                                 .map(d -> new Capacity(d.getCenter(), d.getCapacity()))
                                 .collect(Collectors.toList());
    }

    public int getMaxCapacityLast(int quanty, List<Capacity>  capacity, String center) {
        int original = capacity.stream()
                .filter(c -> c.getCenter().equals(center))
                .reduce((first, second) -> second)
                .get()
                .getValue();

        return quanty > original ? original : quanty;
    }

    public int getMaxQuantity(Center center, Product product) {
        return center.getQuantity() <= product.getQuantity() ? center.getQuantity() : product.getQuantity();
    }
}
