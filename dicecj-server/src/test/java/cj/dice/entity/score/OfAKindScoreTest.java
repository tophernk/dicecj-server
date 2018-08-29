package cj.dice.entity.score;

import cj.dice.TestEntityFactory;
import org.junit.Assert;
import org.junit.Test;

public class OfAKindScoreTest {

    @Test
    public void evaluateThreeOfAKind() {
        ThreeOfAKind threeOfAKind = new ThreeOfAKind();

        // all different
        Assert.assertEquals(0, threeOfAKind.evaluate(TestEntityFactory.createDice(2, 3, 4, 5, 6)));

        // two matching
        Assert.assertEquals(0, threeOfAKind.evaluate(TestEntityFactory.createDice(2, 2, 1, 5, 6)));
        Assert.assertEquals(0, threeOfAKind.evaluate(TestEntityFactory.createDice(1, 2, 2, 5, 6)));
        Assert.assertEquals(0, threeOfAKind.evaluate(TestEntityFactory.createDice(1, 4, 3, 2, 2)));

        // three matching
        Assert.assertEquals(17, threeOfAKind.evaluate(TestEntityFactory.createDice(2, 2, 2, 5, 6)));
        Assert.assertEquals(17, threeOfAKind.evaluate(TestEntityFactory.createDice(5, 2, 2, 2, 6)));
        Assert.assertEquals(17, threeOfAKind.evaluate(TestEntityFactory.createDice(6, 5, 2, 2, 2)));

        // four matching
        Assert.assertEquals(25, threeOfAKind.evaluate(TestEntityFactory.createDice(6, 6, 6, 6, 1)));
        Assert.assertEquals(25, threeOfAKind.evaluate(TestEntityFactory.createDice(1, 6, 6, 6, 6)));

        // five matching
        Assert.assertEquals(30, threeOfAKind.evaluate(TestEntityFactory.createDice(6, 6, 6, 6, 6)));
    }

    @Test
    public void evaluateFourOfAKind() {
        FourOfAKind fourOfAKind = new FourOfAKind();

        // all different
        Assert.assertEquals(0, fourOfAKind.evaluate(TestEntityFactory.createDice(2, 3, 4, 5, 6)));

        // two matching
        Assert.assertEquals(0, fourOfAKind.evaluate(TestEntityFactory.createDice(2, 2, 1, 5, 6)));
        Assert.assertEquals(0, fourOfAKind.evaluate(TestEntityFactory.createDice(1, 2, 2, 5, 6)));
        Assert.assertEquals(0, fourOfAKind.evaluate(TestEntityFactory.createDice(1, 4, 3, 2, 2)));

        // three matching
        Assert.assertEquals(0, fourOfAKind.evaluate(TestEntityFactory.createDice(2, 2, 2, 5, 6)));
        Assert.assertEquals(0, fourOfAKind.evaluate(TestEntityFactory.createDice(5, 2, 2, 2, 6)));
        Assert.assertEquals(0, fourOfAKind.evaluate(TestEntityFactory.createDice(6, 5, 2, 2, 2)));

        // four matching
        Assert.assertEquals(25, fourOfAKind.evaluate(TestEntityFactory.createDice(6, 6, 6, 6, 1)));
        Assert.assertEquals(25, fourOfAKind.evaluate(TestEntityFactory.createDice(1, 6, 6, 6, 6)));

        // five matching
        Assert.assertEquals(30, fourOfAKind.evaluate(TestEntityFactory.createDice(6, 6, 6, 6, 6)));
    }

    @Test
    public void evaluateYahtzee() {
        Yahtzee yahtzee = new Yahtzee();

        // all different
        Assert.assertEquals(0, yahtzee.evaluate(TestEntityFactory.createDice(2, 3, 4, 5, 6)));

        // two matching
        Assert.assertEquals(0, yahtzee.evaluate(TestEntityFactory.createDice(2, 2, 1, 5, 6)));
        Assert.assertEquals(0, yahtzee.evaluate(TestEntityFactory.createDice(1, 2, 2, 5, 6)));
        Assert.assertEquals(0, yahtzee.evaluate(TestEntityFactory.createDice(1, 4, 3, 2, 2)));

        // three matching
        Assert.assertEquals(0, yahtzee.evaluate(TestEntityFactory.createDice(2, 2, 2, 5, 6)));
        Assert.assertEquals(0, yahtzee.evaluate(TestEntityFactory.createDice(5, 2, 2, 2, 6)));
        Assert.assertEquals(0, yahtzee.evaluate(TestEntityFactory.createDice(6, 5, 2, 2, 2)));

        // four matching
        Assert.assertEquals(0, yahtzee.evaluate(TestEntityFactory.createDice(6, 6, 6, 6, 1)));
        Assert.assertEquals(0, yahtzee.evaluate(TestEntityFactory.createDice(1, 6, 6, 6, 6)));

        // five matching
        Assert.assertEquals(50, yahtzee.evaluate(TestEntityFactory.createDice(6, 6, 6, 6, 6)));
    }
}