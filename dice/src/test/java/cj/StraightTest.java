package cj;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StraightTest {

    @Test
    public void evaluateBigStraight() {
        final int straightValue = 40;

        Straight straight = new Straight(5, straightValue, "Big Straight");

        Assert.assertEquals(straightValue, straight.evaluate(createDice(1, 2, 3, 4, 5)));
        Assert.assertEquals(straightValue, straight.evaluate(createDice(2, 3, 4, 5, 6)));
        Assert.assertEquals(straightValue, straight.evaluate(createDice(2, 4, 5, 3, 1)));
        Assert.assertEquals(0, straight.evaluate(createDice(4, 5, 4, 5, 1)));
    }

    @Test
    public void evaluateSmallStraight() {
        final int straightValue = 30;

        Straight straight = new Straight(4, straightValue, "Small Straight");

        Assert.assertEquals(straightValue, straight.evaluate(createDice(1, 2, 3, 4, 6)));
        Assert.assertEquals(straightValue, straight.evaluate(createDice(2, 3, 4, 5, 5)));
        Assert.assertEquals(straightValue, straight.evaluate(createDice(2, 4, 5, 3, 2)));
        Assert.assertEquals(0, straight.evaluate(createDice(4, 5, 4, 5, 1)));
    }

    private List<Die> createDice(int v1, int v2, int v3, int v4, int v5) {
        Die d1 = new Die();
        d1.setValue(v1);
        Die d2 = new Die();
        d2.setValue(v2);
        Die d3 = new Die();
        d3.setValue(v3);
        Die d4 = new Die();
        d4.setValue(v4);
        Die d5 = new Die();
        d5.setValue(v5);

        List<Die> dice = new ArrayList<>();
        dice.add(d1);
        dice.add(d2);
        dice.add(d3);
        dice.add(d4);
        dice.add(d5);
        return dice;
    }
}