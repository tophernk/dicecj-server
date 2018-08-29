package cj.dice.entity.score;

import cj.dice.TestEntityFactory;
import org.junit.Assert;
import org.junit.Test;

public class FullHouseTest {

    @Test
    public void evaluate() {
        FullHouse fullHouse = new FullHouse();
        Assert.assertEquals(0, fullHouse.evaluate(TestEntityFactory.createDice(4, 5, 4, 5, 1)));
        Assert.assertEquals(0, fullHouse.evaluate(TestEntityFactory.createDice(4, 4, 4, 5, 1)));
        Assert.assertEquals(0, fullHouse.evaluate(TestEntityFactory.createDice(4, 4, 4, 4, 1)));
        Assert.assertEquals(25, fullHouse.evaluate(TestEntityFactory.createDice(4, 5, 4, 5, 5)));
        Assert.assertEquals(25, fullHouse.evaluate(TestEntityFactory.createDice(5, 5, 5, 5, 5)));
    }
}