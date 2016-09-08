package models;

import algorithm.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ResponseTest {
    private List<Center> centers = new ArrayList<>();
    @Test
    public void testRequestToResponse() {
        centers.add(new Center("Brazil", "Keyboard", 2));
        Response response = new Response(centers);

        assertThat(response.getCentersOut().get(0).getWareHouse(), is("Brazil"));
        assertThat(response.getCentersOut().get(0).getProduct(), is("Keyboard"));
        assertThat(response.getCentersOut().get(0).getQuantity(), is(2));

    }
}
