package cj.dice.command;

import cj.dice.InputException;
import org.junit.Test;

public abstract class CommandTest extends MockitoTest {
    @Test
    public abstract void execute() throws InputException;

    @Test
    public abstract void isTrigger();

    @Test
    public abstract void retrieveInstructions();
}
