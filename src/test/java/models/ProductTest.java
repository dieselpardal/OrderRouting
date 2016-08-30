package models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProductTest {

    @Test
    public void testNewProduct() {
        Product products = new Product("Tablet", 2);
        assertThat(products.getName(), is("Tablet"));
        assertThat(products.getQuantity(), is(2));
    }
}
