package cj.dice.entity.score;

import cj.dice.TestDiceUtil;
import org.junit.Assert;
import org.junit.Test;

public class StraightTest {

    @Test
    public void evaluateBigStraight() {
        final int straightValue = 40;

        Straight straight = new Straight();

        Assert.assertEquals(straightValue, straight.evaluate(TestDiceUtil.createDice(1, 2, 3, 4, 5)));
        Assert.assertEquals(straightValue, straight.evaluate(TestDiceUtil.createDice(2, 3, 4, 5, 6)));
        Assert.assertEquals(straightValue, straight.evaluate(TestDiceUtil.createDice(2, 4, 5, 3, 1)));
        Assert.assertEquals(0, straight.evaluate(TestDiceUtil.createDice(4, 5, 4, 5, 1)));
    }

    @Test
    public void evaluateSmallStraight() {
        final int straightValue = 30;

        Straight straight = new SmallStraight();

        Assert.assertEquals(straightValue, straight.evaluate(TestDiceUtil.createDice(1, 2, 3, 4, 6)));
        Assert.assertEquals(straightValue, straight.evaluate(TestDiceUtil.createDice(2, 3, 4, 5, 5)));
        Assert.assertEquals(straightValue, straight.evaluate(TestDiceUtil.createDice(2, 4, 5, 3, 2)));
        Assert.assertEquals(0, straight.evaluate(TestDiceUtil.createDice(4, 5, 4, 5, 1)));
    }

}