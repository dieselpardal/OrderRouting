package models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorizationStrategyTest {

    @Test
    public void testAllPriorizationStrategy() {

        assertThat(PriorizationStrategy.None, is(PriorizationStrategy.None));
        assertThat(PriorizationStrategy.LargestCapacity, is(PriorizationStrategy.LargestCapacity));
        assertThat(PriorizationStrategy.LargestInventory, is(PriorizationStrategy.LargestInventory));
        assertThat(PriorizationStrategy.ShortestInventory, is(PriorizationStrategy.ShortestInventory));
    }
}
