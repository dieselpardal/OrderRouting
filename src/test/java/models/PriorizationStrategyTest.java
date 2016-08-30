package models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorizationStrategyTest {

    @Test
    public void testNewPriorizationStrategy() {
        PriorizationStrategy priorizationStrategy = new PriorizationStrategy();

        assertThat(priorizationStrategy.getNone(), is("None"));
    }
}
