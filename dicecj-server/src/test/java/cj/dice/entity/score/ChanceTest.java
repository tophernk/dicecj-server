package cj.dice.entity.score;

import cj.dice.TestDiceUtil;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChanceTest {

    @Test
    public void evaluate() {
        Chance chance = new Chance();
        Assert.assertEquals(15, chance.evaluate(TestDiceUtil.createDice(1, 2, 3, 4, 5)));
    }
}