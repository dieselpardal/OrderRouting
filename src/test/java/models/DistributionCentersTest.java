package models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DistributionCentersTest {

    @Test
    public void testNewDistributionCentger() {
        ShippingMethod sm = new ShippingMethod();
        List<String> shipping = new ArrayList<>();
        List<DistributionCenters> distributionCenters = new ArrayList<>();
        List<Integer> capacity = new ArrayList<>();
        shipping.add(sm.getDHL());
        shipping.add(sm.getFedex());
        capacity.add(15);
        distributionCenters.add(new DistributionCenters("Brazil", shipping, capacity));

        assertThat(distributionCenters.get(0).getName(), is("Brazil"));
        assertThat(distributionCenters.get(0).getShippingMethod().get(0), is("DHL"));
        assertThat(distributionCenters.get(0).getShippingMethod().get(1), is("Fedex"));
        assertThat(distributionCenters.get(0).getCapacityLast(), is(15));
    }

}


