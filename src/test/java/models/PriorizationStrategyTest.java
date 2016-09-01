package models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorizationStrategyTest {

    @Test
    public void testAllPriorizationStrategy() {
        PriorizationStrategy priorizationStrategy = new PriorizationStrategy();
        assertThat(priorizationStrategy.getNone(), is("None"));
        assertThat(priorizationStrategy.getLargestAvailability(), is("LargestAvailability"));
        assertThat(priorizationStrategy.getShortestAvailability(), is("ShortestAvailability"));
        assertThat(priorizationStrategy.getLargestCapacity(), is("LargestCapacity"));
    }
}
