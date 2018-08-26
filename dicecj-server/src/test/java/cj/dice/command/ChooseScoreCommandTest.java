package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Die;
import cj.dice.entity.Game;
import cj.dice.entity.Scoreboard;
import cj.dice.entity.score.OneFaceScore;
import cj.dice.entity.score.Score;
import cj.dice.service.ScoreboardSerivce;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ChooseScoreCommandTest extends CommandTest {

    @InjectMocks
    private ChooseScoreCommand command;

    @Mock
    private Game game;

    @Mock
    private Scoreboard scoreboard;

    @Mock
    private ScoreboardSerivce scoreboardSerivce;

    private OneFaceScore s1;
    private List<Die> dice;

    private void initExecute() {
        Mockito.when(game.getScoreboard()).thenReturn(scoreboard);

        s1 = new OneFaceScore();

        List<Score> openScores = new ArrayList<>();
        openScores.add(s1);

        Mockito.when(scoreboard.getOpenScores()).thenReturn(openScores);

        dice = new ArrayList<>();
    }

    @Test
    public void execute() throws InputException {
        initExecute();
        command.execute("0", game);
        Mockito.verify(scoreboardSerivce).addScore(scoreboard, s1, dice);
    }

    @Test(expected = InputException.class)
    public void executeWithInputException() throws InputException {
        initExecute();
        command.execute("-1", game);
    }

    @Override
    public void isTrigger() {
        Assert.assertFalse(command.isTrigger("noTrigger"));
        Assert.assertTrue(command.isTrigger("scr"));
    }

    @Test
    public void retrieveInstructions() {
        Assert.assertNotNull(command.retrieveInstructions());
    }
}