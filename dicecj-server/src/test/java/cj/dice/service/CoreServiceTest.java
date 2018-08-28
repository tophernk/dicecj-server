package cj.dice.service;

import cj.dice.command.*;
import cj.dice.entity.Game;
import cj.dice.entity.Player;
import cj.dice.entity.Scoreboard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.enterprise.inject.Instance;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.AllOf.allOf;

public class CoreServiceTest extends MockitoTest {

    @InjectMocks
    private CoreService serviceUnderTest;

    @Mock
    private Instance<InputCommand> inputCommands;

    @Mock
    private GameDao gameDao;

    @Mock
    private ScoreboardSerivce scoreboardSerivce;

    @Mock
    private Player player;
    private Supplier<Stream<InputCommand>> streamSupplier;

    @Before
    public void mockCommands() {
        InputCommand[] inputCommandArray = new InputCommand[] { new NewGameCommand(), new RollDiceCommand() };
        streamSupplier = () -> Stream.of(inputCommandArray);
        Mockito.when(inputCommands.stream()).thenReturn(streamSupplier.get());
    }

    @Test
    public void initNewGame() {
        serviceUnderTest.initNewGame(player);
        Mockito.verify(gameDao).createGame(Mockito.any(Game.class));
    }

    @Test
    public void initNewGameWhenScoreboardIsComplete() {
        Scoreboard completedScoreboard = new Scoreboard();
        completedScoreboard.setOpenScores(new ArrayList<>());
        Scoreboard newScoreboard = new Scoreboard();
        Game game = new Game();
        game.setScoreboard(completedScoreboard);

        Mockito.when(gameDao.findGameByPlayer(player)).thenReturn(game);
        Mockito.when(scoreboardSerivce.buildScoreboard(player)).thenReturn(newScoreboard);

        serviceUnderTest.initNewGame(player);

        Assert.assertEquals(newScoreboard, game.getScoreboard());
        Assert.assertEquals(0, game.getCurrentNumberOfRolls());
    }

    @Test
    public void retrieveCommandOverview() {
        NewGameCommand newGameCommand = new NewGameCommand();
        RollDiceCommand rollDiceCommand = new RollDiceCommand();
        SelectDiceCommand selectDiceCommand = new SelectDiceCommand();

        String commandOverview = serviceUnderTest.retrieveCommandOverview();
        Assert.assertThat(commandOverview, allOf(
                containsString(newGameCommand.retrieveInstructions()),
                containsString(rollDiceCommand.retrieveInstructions()),
                not(containsString(selectDiceCommand.retrieveInstructions()))));
    }

    @Test
    public void executeRollCommand() {
        Game game = Mockito.mock(Game.class);
        Mockito.when(game.getScoreboard()).thenReturn(Mockito.mock(Scoreboard.class));
        Mockito.when(game.getCurrentNumberOfRolls()).thenReturn(0);
        InputCommand command = Mockito.mock(InputCommand.class);
        Mockito.when(command.isRoll()).thenReturn(true);
        serviceUnderTest.executeCommand(command, "input", game);
        Mockito.verify(game).setCurrentNumberOfRolls(1);
    }

    @Test
    public void executeTurnEndCommand() {
        Game game = Mockito.mock(Game.class);
        Mockito.when(game.getScoreboard()).thenReturn(Mockito.mock(Scoreboard.class));
        InputCommand command = Mockito.mock(InputCommand.class);
        Mockito.when(command.isTurnEndCommand()).thenReturn(true);
        serviceUnderTest.executeCommand(command, "input", game);
        Mockito.verify(game).setCurrentNumberOfRolls(0);
    }

    @Test
    public void getFirstExecutableCommand() {
        Optional<InputCommand> command = serviceUnderTest.getFirstExecutableCommand("noTrigger");
        Assert.assertFalse(command.isPresent());
        Stream<InputCommand> inputCommandStream = streamSupplier.get();
        Mockito.when(inputCommands.stream()).thenReturn(inputCommandStream);
        command = serviceUnderTest.getFirstExecutableCommand("r");
        Assert.assertTrue(command.isPresent());
    }

}