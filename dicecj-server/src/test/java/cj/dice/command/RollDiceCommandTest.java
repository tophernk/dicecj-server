package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Die;
import cj.dice.entity.Game;
import cj.dice.service.CoreService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class RollDiceCommandTest extends CommandTest {

    @InjectMocks
    private RollDiceCommand command;

    @Mock
    private Game game;

    @Mock
    private Die die;

    @Mock
    private CoreService coreService;

    @Override
    public void execute() throws InputException {
        List<Die> dice = new ArrayList<>();
        dice.add(die);
        Mockito.when(game.getDice()).thenReturn(dice);
        command.execute(null, game);
        Mockito.verify(die).roll();
    }

    @Test(expected = InputException.class)
    public void executeWithInputException() throws InputException {
        Mockito.when(game.getCurrentNumberOfRolls()).thenReturn(Integer.MAX_VALUE);
        command.execute(null, game);
    }

    @Override
    public void isTrigger() {
        Assert.assertFalse(command.isTrigger("noTrigger"));
        Assert.assertTrue(command.isTrigger("r"));
    }

    @Override
    public void retrieveInstructions() {
        Assert.assertNotNull(command.retrieveInstructions());
    }

    @Test
    public void isRoll() {
        Assert.assertTrue(command.isRoll());
    }
}