package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Die;
import cj.dice.entity.Game;
import cj.dice.service.CoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class SelectDiceCommandTest {

    @InjectMocks
    private SelectDiceCommand command;

    @Mock
    private CoreService coreServiceMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isTrigger() {
        Assert.assertTrue(command.isTrigger("sel"));
    }

    @Test
    public void execute() throws InputException {
        Game game = new Game();
        Die d1 = new Die();
        List<Die> dice = new ArrayList<>();
        dice.add(d1);
        game.setDice(dice);

        command.execute("0", game);
        Assert.assertFalse(d1.isLocked());
        command.execute("1", game);
        Assert.assertTrue(d1.isLocked());
    }

    @Test(expected = InputException.class)
    public void executeWithInputException() throws InputException {
        command.execute("invalidInput", new Game());
    }

    @Test
    public void retrieveInstructions() {
        Assert.assertNotNull(command.retrieveInstructions());
    }
}