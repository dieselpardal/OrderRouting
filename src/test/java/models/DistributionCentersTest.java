package models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DistributionCentersTest {

    @Test
    public void testNewDistributionCentger() {
        List<DistributionCenter> distributionCenters = new ArrayList<>();
        distributionCenters.add(new DistributionCenter("Brazil",new ArrayList<>(Arrays.asList(ShippingMethod.DHL, ShippingMethod.FEDEX)), 15));

        assertThat(distributionCenters.get(0).getCenter(), is("Brazil"));
        assertThat(distributionCenters.get(0).getShippingMethod().get(0), is(ShippingMethod.DHL));
        assertThat(distributionCenters.get(0).getShippingMethod().get(1), is(ShippingMethod.FEDEX));
    }

}


