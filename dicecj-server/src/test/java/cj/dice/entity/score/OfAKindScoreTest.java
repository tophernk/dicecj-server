package cj.dice.entity.score;

import cj.dice.TestDiceUtil;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class OfAKindScoreTest {

    @Test
    public void evaluateThreeOfAKind() {
        ThreeOfAKind threeOfAKind = new ThreeOfAKind();

        // all different
        Assert.assertEquals(0, threeOfAKind.evaluate(TestDiceUtil.createDice(2, 3, 4, 5, 6)));
        Assert.assertEquals(0, threeOfAKind.evaluate(TestDiceUtil.createDice(2, 2, 4, 5, 6)));

        // two matching
        Assert.assertEquals(0, threeOfAKind.evaluate(TestDiceUtil.createDice(2, 2, 1, 5, 6)));
        Assert.assertEquals(0, threeOfAKind.evaluate(TestDiceUtil.createDice(1, 2, 2, 5, 6)));
        Assert.assertEquals(0, threeOfAKind.evaluate(TestDiceUtil.createDice(1, 4, 3, 2, 2)));

        // three matching
        Assert.assertEquals(17, threeOfAKind.evaluate(TestDiceUtil.createDice(2, 2, 2, 5, 6)));
        Assert.assertEquals(17, threeOfAKind.evaluate(TestDiceUtil.createDice(5, 2, 2, 2, 6)));
        Assert.assertEquals(17, threeOfAKind.evaluate(TestDiceUtil.createDice(6, 5, 2, 2, 2)));

        // four matching
        Assert.assertEquals(25, threeOfAKind.evaluate(TestDiceUtil.createDice(6, 6, 6, 6, 1)));
        Assert.assertEquals(25, threeOfAKind.evaluate(TestDiceUtil.createDice(1, 6, 6, 6, 6)));

        // five matching
        Assert.assertEquals(30, threeOfAKind.evaluate(TestDiceUtil.createDice(6, 6, 6, 6, 6)));
    }

    @Test
    public void evaluateFourOfAKind() {
        FourOfAKind fourOfAKind = new FourOfAKind();

        // all different
        Assert.assertEquals(0, fourOfAKind.evaluate(TestDiceUtil.createDice(2, 3, 4, 5, 6)));
        Assert.assertEquals(0, fourOfAKind.evaluate(TestDiceUtil.createDice(2, 2, 4, 5, 6)));

        // two matching
        Assert.assertEquals(0, fourOfAKind.evaluate(TestDiceUtil.createDice(2, 2, 1, 5, 6)));
        Assert.assertEquals(0, fourOfAKind.evaluate(TestDiceUtil.createDice(1, 2, 2, 5, 6)));
        Assert.assertEquals(0, fourOfAKind.evaluate(TestDiceUtil.createDice(1, 4, 3, 2, 2)));

        // three matching
        Assert.assertEquals(0, fourOfAKind.evaluate(TestDiceUtil.createDice(2, 2, 2, 5, 6)));
        Assert.assertEquals(0, fourOfAKind.evaluate(TestDiceUtil.createDice(5, 2, 2, 2, 6)));
        Assert.assertEquals(0, fourOfAKind.evaluate(TestDiceUtil.createDice(6, 5, 2, 2, 2)));

        // four matching
        Assert.assertEquals(25, fourOfAKind.evaluate(TestDiceUtil.createDice(6, 6, 6, 6, 1)));
        Assert.assertEquals(25, fourOfAKind.evaluate(TestDiceUtil.createDice(1, 6, 6, 6, 6)));

        // five matching
        Assert.assertEquals(30, fourOfAKind.evaluate(TestDiceUtil.createDice(6, 6, 6, 6, 6)));
    }

    @Test
    public void evaluateYahtzee() {
        Yahtzee yahtzee = new Yahtzee();

        // all different
        Assert.assertEquals(0, yahtzee.evaluate(TestDiceUtil.createDice(2, 3, 4, 5, 6)));
        Assert.assertEquals(0, yahtzee.evaluate(TestDiceUtil.createDice(2, 2, 4, 5, 6)));

        // two matching
        Assert.assertEquals(0, yahtzee.evaluate(TestDiceUtil.createDice(2, 2, 1, 5, 6)));
        Assert.assertEquals(0, yahtzee.evaluate(TestDiceUtil.createDice(1, 2, 2, 5, 6)));
        Assert.assertEquals(0, yahtzee.evaluate(TestDiceUtil.createDice(1, 4, 3, 2, 2)));

        // three matching
        Assert.assertEquals(0, yahtzee.evaluate(TestDiceUtil.createDice(2, 2, 2, 5, 6)));
        Assert.assertEquals(0, yahtzee.evaluate(TestDiceUtil.createDice(5, 2, 2, 2, 6)));
        Assert.assertEquals(0, yahtzee.evaluate(TestDiceUtil.createDice(6, 5, 2, 2, 2)));

        // four matching
        Assert.assertEquals(0, yahtzee.evaluate(TestDiceUtil.createDice(6, 6, 6, 6, 1)));
        Assert.assertEquals(0, yahtzee.evaluate(TestDiceUtil.createDice(1, 6, 6, 6, 6)));

        // five matching
        Assert.assertEquals(50, yahtzee.evaluate(TestDiceUtil.createDice(6, 6, 6, 6, 6)));
    }
}