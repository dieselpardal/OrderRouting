package models;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TabelaDistributionTest {

    @Test
    public void shouldExistFiveShippings() {
        TableOfDistribution tableOfDistribution = new TableOfDistribution();
        List<DistributionCenter> centers = tableOfDistribution.table();
        assertThat(centers.size(), is(5));
    }

    @Test
    public void shouldExistAmountShippingMethods() {
        TableOfDistribution tableOfDistribution = new TableOfDistribution();
        Iterator<DistributionCenter> distributionCenters = tableOfDistribution.table().iterator();
        assertThat(distributionCenters.next().getShippingMethod().size(), is(2));
        assertThat(distributionCenters.next().getShippingMethod().size(), is(3));
        assertThat(distributionCenters.next().getShippingMethod().size(), is(2));
        assertThat(distributionCenters.next().getShippingMethod().size(), is(1));
        assertThat(distributionCenters.next().getShippingMethod().size(), is(1));
    }

    @Test
    public void shouldExistTableOfDistribution() {
        TableOfDistribution tableOfDistribution = new TableOfDistribution();

        DistributionCenter centers;
        Iterator<DistributionCenter> distributionCenters = tableOfDistribution.table().iterator();
        Iterator<ShippingMethod> shippingMethods;

        centers = distributionCenters.next();
        assertThat(centers.getCenter(), is("Brazil"));
        shippingMethods = centers.getShippingMethod().iterator();
        assertThat(shippingMethods.next(), is(ShippingMethod.DHL));
        assertThat(shippingMethods.next(), is(ShippingMethod.FEDEX));
        assertThat(centers.getCapacity(), is(15));

        centers = distributionCenters.next();
        assertThat(centers.getCenter(), is("France"));
        shippingMethods = centers.getShippingMethod().iterator();
        assertThat(shippingMethods.next(), is(ShippingMethod.DHL));
        assertThat(shippingMethods.next(), is(ShippingMethod.FEDEX));
        assertThat(shippingMethods.next(), is(ShippingMethod.UPS));
        assertThat(centers.getCapacity(), is(10));

        centers = distributionCenters.next();
        assertThat(centers.getCenter(), is("South Africa"));
        shippingMethods = centers.getShippingMethod().iterator();
        assertThat(shippingMethods.next(), is(ShippingMethod.FEDEX));
        assertThat(shippingMethods.next(), is(ShippingMethod.UPS));
        assertThat(centers.getCapacity(), is(10));

        centers = distributionCenters.next();
        assertThat(centers.getCenter(), is("China"));
        shippingMethods = centers.getShippingMethod().iterator();
        assertThat(shippingMethods.next(), is(ShippingMethod.DHL));
        assertThat(centers.getCapacity(), is(20));

        centers = distributionCenters.next();
        assertThat(centers.getCenter(), is("Canada"));
        shippingMethods = centers.getShippingMethod().iterator();
        assertThat(shippingMethods.next(), is(ShippingMethod.FEDEX));
        assertThat(centers.getCapacity(), is(5));
    }

}
