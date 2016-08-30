package models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CenterTest {

    @Test
    public void testNewCenter() {
        Center center = new Center("Brazil", "Tablet", 2);
        assertThat(center.getCenter(), is("Brazil"));
        assertThat(center.getProduct(), is("Tablet"));
        assertThat(center.getQuantity(), is(2));
    }

    @Test
    public void testOtherNewCenter() {
        Center center = new Center("Brazil", "Tablet", 2);
        Center otherCenter = new Center("Chile", center.getProduct(), center.getQuantity());
        assertThat(otherCenter.getCenter(), is("Chile"));
        assertThat(otherCenter.getProduct(), is("Tablet"));
        assertThat(otherCenter.getQuantity(), is(2));
    }
}
