package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Game;
import org.junit.Test;
import org.mockito.Mock;

public abstract class CommandTest extends MockitoTest {
    @Mock
    protected Game game;

    @Test
    public abstract void execute() throws InputException;

    @Test
    public abstract void isTrigger();

    @Test
    public abstract void retrieveInstructions();
}
