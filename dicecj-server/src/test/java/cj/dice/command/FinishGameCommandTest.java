package cj.dice.command;

import cj.dice.InputException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;

public class FinishGameCommandTest extends CommandTest {

    @InjectMocks
    private FinishGameCommand command;

    @Override
    @Test
    public void execute() throws InputException {
        // nothing to test
    }

    @Override
    @Test
    public void isTrigger() {
        Assert.assertFalse(command.isTrigger("noTrigger"));
        Assert.assertTrue(command.isTrigger("f"));
    }

    @Override
    @Test
    public void retrieveInstructions() {
        Assert.assertNotNull(command.retrieveInstructions());
    }
}