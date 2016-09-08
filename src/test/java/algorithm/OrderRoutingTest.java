package algorithm;

import models.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class OrderRoutingTest {

    DistributionToCapacity distributionToCapacity = new DistributionToCapacity();

    @Test
    public void shouldOrderRoutingCaseStandard() {
        OrderRouting order = new OrderRouting();
        List<Center> centers = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        centers.add(new Center("Brazil", "Keyboard", 2));
        centers.add(new Center("France", "Mouse", 2));
        products.add(new Product("Keyboard", 2));
        Request request = new Request(centers, ShippingMethod.DHL, PriorizationStrategy.None, products);

        Response response = order.solver(request, distributionToCapacity);
        Center centersOut = response.getCentersOut().get(0);
        assertThat(centersOut.getWareHouse(), is("Brazil"));
        assertThat(centersOut.getProduct(), is("Keyboard"));
        assertThat(centersOut.getQuantity(), is(2));
    }

    @Test
    public void shouldOrderRoutingCaseShippingMethod() {
        OrderRouting order = new OrderRouting();
        List<Center> centers = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        centers.add(new Center("Brazil", "Mouse", 2));
        centers.add(new Center("South Africa", "Mouse", 2));
        products.add(new Product("Mouse", 1));
        Request request = new Request(centers, ShippingMethod.UPS, PriorizationStrategy.None, products);

        Response response = order.solver(request, distributionToCapacity);
        Center centersOut = response.getCentersOut().get(0);
        assertThat(centersOut.getWareHouse(), is("South Africa"));
        assertThat(centersOut.getProduct(), is("Mouse"));
        assertThat(centersOut.getQuantity(), is(1));
    }

    @Test
    public void shouldOrderRoutingCaseCapacity() {
        OrderRouting order = new OrderRouting();
        List<Center> centers = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Center centerOut;

        centers.add(new Center("Canada", "Mouse", 4));
        centers.add(new Center("Canada", "Keyboard", 3));
        centers.add(new Center("France", "Keyboard", 2));
        products.add(new Product("Mouse", 4));
        products.add(new Product("Keyboard", 3));
        Request request = new Request(centers, ShippingMethod.FEDEX, PriorizationStrategy.None, products);

        Response response = order.solver(request, distributionToCapacity);
        Iterator<Center> centerIterator = response.getCentersOut().iterator();

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("Canada"));
        assertThat(centerOut.getProduct(), is("Mouse"));
        assertThat(centerOut.getQuantity(), is(4));

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("Canada"));
        assertThat(centerOut.getProduct(), is("Keyboard"));
        assertThat(centerOut.getQuantity(), is(1));

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("France"));
        assertThat(centerOut.getProduct(), is("Keyboard"));
        assertThat(centerOut.getQuantity(), is(2));

    }


    @Test
    public void shouldOrderRoutingCasePrioritizeByLargestInventory() {
        OrderRouting order = new OrderRouting();
        List<Center> centers = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Center centerOut;

        centers.add(new Center("China", "Mouse", 4));
        centers.add(new Center("Brazil", "Mouse", 3));
        centers.add(new Center("Brazil", "Keyboard", 3));
        centers.add(new Center("France", "Mouse", 2));
        centers.add(new Center("France", "Keyboard", 2));
        products.add(new Product("Mouse", 1));
        products.add(new Product("Keyboard", 1));
        Request request = new Request(centers, ShippingMethod.DHL, PriorizationStrategy.LargestInventory, products);

        Response response = order.solver(request, distributionToCapacity);
        Iterator<Center> centerIterator = response.getCentersOut().iterator();

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("Brazil"));
        assertThat(centerOut.getProduct(), is("Mouse"));
        assertThat(centerOut.getQuantity(), is(1));

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("Brazil"));
        assertThat(centerOut.getProduct(), is("Keyboard"));
        assertThat(centerOut.getQuantity(), is(1));

    }

    @Test
    public void shouldOrderRoutingCasePrioritizeByShortestInventory() {
        OrderRouting order = new OrderRouting();
        List<Center> centers = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Center centerOut;

        centers.add(new Center("China", "Mouse", 4));
        centers.add(new Center("Brazil", "Mouse", 3));
        centers.add(new Center("Brazil", "Keyboard", 3));
        centers.add(new Center("France", "Keyboard", 2));
        products.add(new Product("Mouse", 1));
        products.add(new Product("Keyboard", 1));
        Request request = new Request(centers, ShippingMethod.DHL, PriorizationStrategy.ShortestInventory, products);

        Response response = order.solver(request, distributionToCapacity);
        Iterator<Center> centerIterator = response.getCentersOut().iterator();

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("France"));
        assertThat(centerOut.getProduct(), is("Keyboard"));
        assertThat(centerOut.getQuantity(), is(1));

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("China"));
        assertThat(centerOut.getProduct(), is("Mouse"));
        assertThat(centerOut.getQuantity(), is(1));

    }

    @Test
    public void shouldOrderRoutingCasePrioritizeByLargestCapacity() {
        OrderRouting order = new OrderRouting();
        List<Center> centers = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Center centerOut;

        centers.add(new Center("China", "Mouse", 4));
        centers.add(new Center("Brazil", "Mouse", 3));
        centers.add(new Center("Brazil", "Keyboard", 3));
        centers.add(new Center("France", "Mouse", 2));
        products.add(new Product("Mouse", 1));
        products.add(new Product("Keyboard", 1));
        Request request = new Request(centers, ShippingMethod.DHL, PriorizationStrategy.LargestCapacity, products);

        Response response = order.solver(request, distributionToCapacity);
        Iterator<Center> centerIterator = response.getCentersOut().iterator();

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("China"));
        assertThat(centerOut.getProduct(), is("Mouse"));
        assertThat(centerOut.getQuantity(), is(1));

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("Brazil"));
        assertThat(centerOut.getProduct(), is("Keyboard"));
        assertThat(centerOut.getQuantity(), is(1));

    }

    @Test
    public void shouldOrderRoutingCaseManyProducts() {
        OrderRouting order = new OrderRouting();
        List<Center> centers = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Center centerOut;

        centers.add(new Center("Canada", "Mouse", 2));
        centers.add(new Center("Brazil", "Mouse", 2));
        centers.add(new Center("Brazil", "Keyboard", 3));
        centers.add(new Center("France", "Keyboard", 2));
        centers.add(new Center("France", "Monitor", 2));
        centers.add(new Center("South Africa", "Monitor", 4));
        centers.add(new Center("South Africa", "Camera", 1));
        centers.add(new Center("South Africa", "Mouse", 2));
        products.add(new Product("Mouse", 6));
        products.add(new Product("Keyboard", 3));
        products.add(new Product("Monitor", 3));
        products.add(new Product("Camera", 1));
        Request request = new Request(centers, ShippingMethod.FEDEX, PriorizationStrategy.None, products);


        Response response = order.solver(request, distributionToCapacity);
        Iterator<Center> centerIterator = response.getCentersOut().iterator();

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("Canada"));
        assertThat(centerOut.getProduct(), is("Mouse"));
        assertThat(centerOut.getQuantity(), is(2));

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("Brazil"));
        assertThat(centerOut.getProduct(), is("Mouse"));
        assertThat(centerOut.getQuantity(), is(2));

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("Brazil"));
        assertThat(centerOut.getProduct(), is("Keyboard"));
        assertThat(centerOut.getQuantity(), is(3));

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("France"));
        assertThat(centerOut.getProduct(), is("Monitor"));
        assertThat(centerOut.getQuantity(), is(2));

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("South Africa"));
        assertThat(centerOut.getProduct(), is("Monitor"));
        assertThat(centerOut.getQuantity(), is(1));

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("South Africa"));
        assertThat(centerOut.getProduct(), is("Camera"));
        assertThat(centerOut.getQuantity(), is(1));

        centerOut = centerIterator.next();
        assertThat(centerOut.getWareHouse(), is("South Africa"));
        assertThat(centerOut.getProduct(), is("Mouse"));
        assertThat(centerOut.getQuantity(), is(2));

    }

    @Test
    public void shouldOrderRoutingCaseInvalid() {
        OrderRouting order = new OrderRouting();
        List<Center> centers = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        centers.add(new Center("China", "Mouse", 4));
        centers.add(new Center("Brazil", "Mouse", 3));
        products.add(new Product("Mouse", 5));
        Request request = new Request(centers, ShippingMethod.FEDEX, PriorizationStrategy.None, products);
        Response response = order.solver(request, distributionToCapacity);
        assertThat(response, is(nullValue()));
    }
}
