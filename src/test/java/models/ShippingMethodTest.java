package models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShippingMethodTest {

    @Test
    public void testNewShippingMethod() {
        ShippingMethod shippingMethod = new ShippingMethod();
        assertThat(shippingMethod.getDHL(), is("DHL"));
        assertThat(shippingMethod.getFedex(), is("Fedex"));
        assertThat(shippingMethod.getUPS(), is("UPS"));
    }
}
