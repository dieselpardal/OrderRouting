package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TableOfDistribution {

    public List<DistributionCenter> table() {
        List<DistributionCenter> distributionCenters = new ArrayList<>();
        distributionCenters.add(new DistributionCenter("Brazil", new ArrayList<>(Arrays.asList(ShippingMethod.DHL, ShippingMethod.FEDEX)), 15));
        distributionCenters.add(new DistributionCenter("France", new ArrayList<>(Arrays.asList(ShippingMethod.DHL, ShippingMethod.FEDEX, ShippingMethod.UPS)), 10));
        distributionCenters.add(new DistributionCenter("South Africa", new ArrayList<>(Arrays.asList(ShippingMethod.FEDEX, ShippingMethod.UPS)), 10));
        distributionCenters.add(new DistributionCenter("China", new ArrayList<>(Collections.singletonList(ShippingMethod.DHL)), 20));
        distributionCenters.add(new DistributionCenter("Canada", new ArrayList<>(Collections.singletonList(ShippingMethod.FEDEX)), 5));

        return distributionCenters;
    }
}
