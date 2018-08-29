package cj.dice.command;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public abstract class MockitoTest {
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
}
