package cj.dice.service;

import cj.dice.command.*;
import cj.dice.entity.Game;
import cj.dice.entity.Player;
import cj.dice.entity.Scoreboard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.BeanManager;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.List;

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

    @Before
    public void mockCommands() {
        List<InputCommand> inputCommandList = new ArrayList<>();
        inputCommandList.add(new NewGameCommand());
        inputCommandList.add(new RollDiceCommand());
        Mockito.when(inputCommands.stream()).thenReturn(inputCommandList.stream());
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
    public void retrieveInstructions() {
    }

    @Test
    public void executeCommand() {
    }

    @Test
    public void getFirstExecutableCommand() {
    }

    @Test
    public void buildResult() {
    }

    @Test
    public void createJson() {
    }

    @Test
    public void printDice() {
    }
}