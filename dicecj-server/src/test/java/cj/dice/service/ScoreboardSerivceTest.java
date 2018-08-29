package cj.dice.service;

import cj.dice.TestEntityFactory;
import cj.dice.command.MockitoTest;
import cj.dice.entity.Player;
import cj.dice.entity.Scoreboard;
import cj.dice.entity.score.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.instanceOf;

public class ScoreboardSerivceTest extends MockitoTest {

    @InjectMocks
    private ScoreboardSerivce serviceUnderTest;

    @Test
    public void addValidScore() {
        FullHouse score = TestEntityFactory.createScore(FullHouse.class, 0, 0);
        List<Score> openScores = TestEntityFactory.createOpenScores(score);
        Scoreboard scoreboard = TestEntityFactory.createScoreboard(openScores, TestEntityFactory.createClosedScores());
        boolean scoreAdded = serviceUnderTest.addScore(scoreboard, score, TestEntityFactory.createDice(1, 1, 1, 2, 2));
        Assert.assertTrue(scoreAdded);
        Assert.assertTrue(openScores.isEmpty());
        Assert.assertEquals(25, scoreboard.getClosedScores().iterator().next().getValue());
    }

    @Test
    public void addInvalidScore() {
        FullHouse score = TestEntityFactory.createScore(FullHouse.class, 0, 0);
        List<Score> openScores = TestEntityFactory.createOpenScores(score);
        Scoreboard scoreboard = TestEntityFactory.createScoreboard(openScores, TestEntityFactory.createClosedScores());
        boolean scoreAdded = serviceUnderTest.addScore(scoreboard, score, TestEntityFactory.createDice(1, 1, 1, 1, 2));
        Assert.assertTrue(scoreAdded);
        Assert.assertTrue(openScores.isEmpty());
        Assert.assertEquals(0, scoreboard.getClosedScores().iterator().next().getValue());
    }

    @Test
    public void getTotal() {
        Scoreboard scoreboard = TestEntityFactory.createScoreboard(
                TestEntityFactory.createOpenScores(),
                TestEntityFactory.createClosedScores(
                        TestEntityFactory.createScore(SixFaceScore.class, 12, 0),
                        TestEntityFactory.createScore(Yahtzee.class, 50, 1)));
        Assert.assertEquals(62, serviceUnderTest.getTotal(scoreboard));
    }

    @Test
    public void printScores() {
        Scoreboard scoreboard = TestEntityFactory.createScoreboard(
                TestEntityFactory.createOpenScores(
                        TestEntityFactory.createScore(FullHouse.class, 0, 0)),
                TestEntityFactory.createClosedScores());
        String scores = serviceUnderTest.printScores(scoreboard, TestEntityFactory.createDice(1, 1, 1, 2, 2));
        Assert.assertThat(scores, containsString("25"));
        scores = serviceUnderTest.printScores(scoreboard, TestEntityFactory.createDice(1, 1, 1, 1, 2));
        Assert.assertThat(scores, containsString("(0)"));
    }

    @Test
    public void buildScoreboard() {
        Player player = Mockito.mock(Player.class);
        Scoreboard scoreboard = serviceUnderTest.buildScoreboard(player);
        Assert.assertThat(scoreboard.getOpenScores(), hasItems(
                instanceOf(OneFaceScore.class),
                instanceOf(TwoFaceScore.class),
                instanceOf(ThreeFaceScore.class),
                instanceOf(FourFaceScore.class),
                instanceOf(FiveFaceScore.class),
                instanceOf(SixFaceScore.class),
                instanceOf(ThreeOfAKind.class),
                instanceOf(FourOfAKind.class),
                instanceOf(FullHouse.class),
                instanceOf(SmallStraight.class),
                instanceOf(Straight.class),
                instanceOf(Yahtzee.class),
                instanceOf(Chance.class)
        ));
        Assert.assertTrue(scoreboard.getClosedScores().isEmpty());
    }

    @Test
    public void calculateBonus() {
        TreeSet<Score> closedScores = TestEntityFactory.createClosedScores(
                TestEntityFactory.createScore(FiveFaceScore.class, 10, 0),
                TestEntityFactory.createScore(Yahtzee.class, 50, 1)
        );
        Scoreboard scoreboard = TestEntityFactory.createScoreboard(null, closedScores);
        Assert.assertEquals(0, serviceUnderTest.calculateBonus(scoreboard));
        SixFaceScore s3 = TestEntityFactory.createScore(SixFaceScore.class, 24, 2);
        closedScores.add(s3);
        Assert.assertEquals(35, serviceUnderTest.calculateBonus(scoreboard));
    }

    @Test
    public void calculateCurrentDiffToRegularBonus() {
        TreeSet<Score> closedScores = TestEntityFactory.createClosedScores(
                TestEntityFactory.createScore(FiveFaceScore.class, 10, 0),
                TestEntityFactory.createScore(Yahtzee.class, 50, 1)
        );
        Scoreboard scoreboard = TestEntityFactory.createScoreboard(null, closedScores);
        Assert.assertEquals(-5, serviceUnderTest.calculateCurrentDiffToRegularBonus(scoreboard));
        SixFaceScore s3 = TestEntityFactory.createScore(SixFaceScore.class, 24, 2);
        closedScores.add(s3);
        Assert.assertEquals(1, serviceUnderTest.calculateCurrentDiffToRegularBonus(scoreboard));
    }

}
