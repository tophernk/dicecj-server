package cj.dice.command;

import org.junit.Test;

public abstract class CommandTest extends MockitoTest {
    @Test
    public abstract void isTrigger();

    @Test
    public abstract void retrieveInstructions();
}
