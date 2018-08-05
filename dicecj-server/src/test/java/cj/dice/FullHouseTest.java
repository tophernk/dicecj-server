package cj.dice;

import cj.dice.entity.FullHouse;
import org.junit.Assert;
import org.junit.Test;

public class FullHouseTest {

    @Test
    public void evaluate() {
        FullHouse fullHouse = new FullHouse();
        Assert.assertEquals(0, fullHouse.evaluate(TestUtil.createDice(4, 5, 4, 5, 1)));
        Assert.assertEquals(0, fullHouse.evaluate(TestUtil.createDice(4, 4, 4, 5, 1)));
        Assert.assertEquals(0, fullHouse.evaluate(TestUtil.createDice(4, 4, 4, 4, 1)));
        Assert.assertEquals(25, fullHouse.evaluate(TestUtil.createDice(4, 5, 4, 5, 5)));
        Assert.assertEquals(25, fullHouse.evaluate(TestUtil.createDice(5, 5, 5, 5, 5)));
    }
}