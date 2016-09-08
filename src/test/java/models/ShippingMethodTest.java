package models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShippingMethodTest {

    @Test
    public void testNewShippingMethod() {
        assertThat(ShippingMethod.DHL, is(ShippingMethod.DHL));
        assertThat(ShippingMethod.FEDEX, is(ShippingMethod.FEDEX));
        assertThat(ShippingMethod.UPS, is(ShippingMethod.UPS));
    }
}
