package cj.dice.entity.score;

import cj.dice.TestEntityFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChanceTest {

    @Test
    public void evaluate() {
        Chance chance = new Chance();
        Assert.assertEquals(15, chance.evaluate(TestEntityFactory.createDice(1, 2, 3, 4, 5)));
    }
}