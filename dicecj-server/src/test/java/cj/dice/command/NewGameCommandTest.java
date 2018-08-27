package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Player;
import cj.dice.service.CoreService;
import cj.dice.service.PlayerService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class NewGameCommandTest extends CommandTest {

    @InjectMocks
    private NewGameCommand command;

    @Mock
    private CoreService coreService;

    @Mock
    private PlayerService playerService;

    @Mock
    private Player player;

    @Override
    @Test
    public void execute() throws InputException {
        String playerName = "playerName";
        Mockito.when(playerService.findOrCreatePlayer(playerName)).thenReturn(player);
        command.execute(playerName, game);
        Mockito.verify(coreService).initNewGame(player);
    }

    @Test(expected = InputException.class)
    public void executeWithInputException() throws InputException {
        command.execute(null, game);
    }

    @Override
    @Test
    public void isTrigger() {
        Assert.assertFalse(command.isTrigger("noTrigger"));
        Assert.assertTrue(command.isTrigger("n"));
    }

    @Override
    @Test
    public void retrieveInstructions() {
        Assert.assertNotNull(command.retrieveInstructions());
    }
}