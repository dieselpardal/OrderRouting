package models;

import algorithm.Request;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RequestTest {
    private List<Center> centers = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    @Test
    public void testNewRequest() {

        centers.add(new Center("Brazil", "Keyboard", 2));
        products.add(new Product("Keyboard", 2));
        Request request = new Request(centers, ShippingMethod.DHL, PriorizationStrategy.None, products);

        assertThat(request.getCenters().get(0).getWareHouse(), is("Brazil"));
        assertThat(request.getCenters().get(0).getProduct(), is("Keyboard"));
        assertThat(request.getCenters().get(0).getQuantity(), is(2));
        assertThat(request.getProducts().get(0).getName(), is("Keyboard"));
        assertThat(request.getProducts().get(0).getQuantity(), is(2));
        assertThat(request.getPriorizationStrategy(), is(PriorizationStrategy.None));
        assertThat(request.getShippingMethod(), is(ShippingMethod.DHL));

    }
}
