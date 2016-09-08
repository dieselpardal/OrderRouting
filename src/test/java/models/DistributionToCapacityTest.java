package models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DistributionToCapacityTest {

    @Test
    public void shouldCopyToCapacity() {
        ShippingMethod sm = null;
        List<DistributionCenter> distributionCenters;
        distributionCenters = new ArrayList<>();
        distributionCenters.add(new DistributionCenter("Brazil", new ArrayList<>(Arrays.asList(ShippingMethod.DHL, ShippingMethod.FEDEX)), 15));
        distributionCenters.add(new DistributionCenter("France", new ArrayList<>(Arrays.asList(ShippingMethod.DHL, ShippingMethod.FEDEX, ShippingMethod.UPS)), 10));

        DistributionToCapacity distributionToCapacity = new DistributionToCapacity();
        List<Capacity> capacity = distributionToCapacity.copy(distributionCenters);
        assertThat(capacity.get(0).getCenter(), is("Brazil"));
        assertThat(capacity.get(0).getValue(), is(15));
        assertThat(capacity.get(1).getCenter(), is("France"));
        assertThat(capacity.get(1).getValue(), is(10));
    }

}
