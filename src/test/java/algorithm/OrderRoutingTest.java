package algorithm;

import models.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OrderRoutingTest {

    PriorizationStrategy priorizationStrategy = new PriorizationStrategy();
    ShippingMethod shippingMethod = new ShippingMethod();
    @Test
    public void shouldExistFiveShippings() {

        OrderRouting order = new OrderRouting();
        List<DistributionCenters> centers = order.getCenters();
        assertThat(centers.size(), is(5));
    }

    @Test
    public void shouldExistAmountShippingMethods() {
        OrderRouting order = new OrderRouting();
        Iterator<DistributionCenters> distributionCenters = order.getCenters().iterator();
        assertThat(distributionCenters.next().getShippingMethod().size(), is(2));
        assertThat(distributionCenters.next().getShippingMethod().size(), is(3));
        assertThat(distributionCenters.next().getShippingMethod().size(), is(2));
        assertThat(distributionCenters.next().getShippingMethod().size(), is(1));
        assertThat(distributionCenters.next().getShippingMethod().size(), is(1));
    }

    @Test
    public void shouldInitCompleteDistributionCenters() {
        OrderRouting order = new OrderRouting();
        DistributionCenters centers;
        Iterator<DistributionCenters> distributionCenters = order.getCenters().iterator();
        Iterator<String> shippingMethod;

        centers = distributionCenters.next();
        assertThat(centers.getName(), is("Brazil"));
        shippingMethod = centers.getShippingMethod().iterator();
        assertThat(shippingMethod.next(), is("DHL"));
        assertThat(shippingMethod.next(), is("Fedex"));
        assertThat(centers.getCapacityLast(), is(15));

        centers = distributionCenters.next();
        assertThat(centers.getName(), is("France"));
        shippingMethod = centers.getShippingMethod().iterator();
        assertThat(shippingMethod.next(), is("DHL"));
        assertThat(shippingMethod.next(), is("Fedex"));
        assertThat(shippingMethod.next(), is("UPS"));
        assertThat(centers.getCapacityLast(), is(10));

        centers = distributionCenters.next();
        assertThat(centers.getName(), is("South Africa"));
        shippingMethod = centers.getShippingMethod().iterator();
        assertThat(shippingMethod.next(), is("Fedex"));
        assertThat(shippingMethod.next(), is("UPS"));
        assertThat(centers.getCapacityLast(), is(10));

        centers = distributionCenters.next();
        assertThat(centers.getName(), is("China"));
        shippingMethod = centers.getShippingMethod().iterator();
        assertThat(shippingMethod.next(), is("DHL"));
        assertThat(centers.getCapacityLast(), is(20));

        centers = distributionCenters.next();
        assertThat(centers.getName(), is("Canada"));
        shippingMethod = centers.getShippingMethod().iterator();
        assertThat(shippingMethod.next(), is("Fedex"));
        assertThat(centers.getCapacityLast(), is(5));
    }

    @Test
    public void shouldOrderRoutingCaseStandard() {
        OrderRouting order = new OrderRouting();
        List<Center> centers = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        centers.add(new Center("Brazil", "Keyboard", 2));
        centers.add(new Center("France", "Mouse", 2));
        products.add(new Product("Keyboard", 2));
        Request request = new Request(centers, shippingMethod.getDHL(), priorizationStrategy.getNone(), products);

        Response response = order.solver(request);
        Center centersOut = response.getCentersOut().get(0);
        assertThat(centersOut.getCenter(), is("Brazil"));
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
        Request request = new Request(centers, shippingMethod.getUPS(), priorizationStrategy.getNone(), products);

        Response response = order.solver(request);
        Center centersOut = response.getCentersOut().get(0);
        assertThat(centersOut.getCenter(), is("South Africa"));
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
        Request request = new Request(centers, shippingMethod.getFedex(), priorizationStrategy.getNone(), products);

        Response response = order.solver(request);
        Iterator<Center> centerIterator = response.getCentersOut().iterator();

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("Canada"));
        assertThat(centerOut.getProduct(), is("Mouse"));
        assertThat(centerOut.getQuantity(), is(4));

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("Canada"));
        assertThat(centerOut.getProduct(), is("Keyboard"));
        assertThat(centerOut.getQuantity(), is(1));

    }

    @Test
    public void shouldOrderRoutingCasePrioritizeByLargestAvailability() {
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
        Request request = new Request(centers, shippingMethod.getDHL(), priorizationStrategy.getLargestAvailability(), products);

        Response response = order.solver(request);
        Iterator<Center> centerIterator = response.getCentersOut().iterator();

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("China"));
        assertThat(centerOut.getProduct(), is("Mouse"));
        assertThat(centerOut.getQuantity(), is(1));

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("Brazil"));
        assertThat(centerOut.getProduct(), is("Keyboard"));
        assertThat(centerOut.getQuantity(), is(1));

    }

    @Test
    public void shouldOrderRoutingCasePrioritizeByShortestAvailability() {
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
        Request request = new Request(centers, shippingMethod.getDHL(), priorizationStrategy.getShortestAvailability(), products);

        Response response = order.solver(request);
        Iterator<Center> centerIterator = response.getCentersOut().iterator();

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("China"));
        assertThat(centerOut.getProduct(), is("Mouse"));
        assertThat(centerOut.getQuantity(), is(1));

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("Brazil"));
        assertThat(centerOut.getProduct(), is("Keyboard"));
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
        Request request = new Request(centers, shippingMethod.getDHL(), priorizationStrategy.getLargestCapacity(), products);

        Response response = order.solver(request);
        Iterator<Center> centerIterator = response.getCentersOut().iterator();

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("China"));
        assertThat(centerOut.getProduct(), is("Mouse"));
        assertThat(centerOut.getQuantity(), is(1));

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("Brazil"));
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
        Request request = new Request(centers, shippingMethod.getFedex(), priorizationStrategy.getNone(), products);


        Response response = order.solver(request);
        Iterator<Center> centerIterator = response.getCentersOut().iterator();

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("Canada"));
        assertThat(centerOut.getProduct(), is("Mouse"));
        assertThat(centerOut.getQuantity(), is(2));

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("Brazil"));
        assertThat(centerOut.getProduct(), is("Mouse"));
        assertThat(centerOut.getQuantity(), is(2));

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("Brazil"));
        assertThat(centerOut.getProduct(), is("Keyboard"));
        assertThat(centerOut.getQuantity(), is(3));

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("France"));
        assertThat(centerOut.getProduct(), is("Monitor"));
        assertThat(centerOut.getQuantity(), is(2));

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("South Africa"));
        assertThat(centerOut.getProduct(), is("Monitor"));
        assertThat(centerOut.getQuantity(), is(1));

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("South Africa"));
        assertThat(centerOut.getProduct(), is("Camera"));
        assertThat(centerOut.getQuantity(), is(1));

        centerOut = centerIterator.next();
        assertThat(centerOut.getCenter(), is("South Africa"));
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
        Request request = new Request(centers, shippingMethod.getFedex(), priorizationStrategy.getNone(), products);

        assertThat(order.hasFulFilled(request), is(order.ORDER_CANNOT_BE_FULFILLED));
    }
}
