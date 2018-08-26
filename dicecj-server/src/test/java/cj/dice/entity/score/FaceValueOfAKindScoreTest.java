package cj.dice.entity.score;

import cj.dice.TestDiceUtil;
import org.junit.Assert;
import org.junit.Test;

public class FaceValueOfAKindScoreTest {

    @Test
    public void evaluateOnes() {
        OneFaceScore score = new OneFaceScore();
        testFaceValue(score, 1, 2);
    }

    @Test
    public void evaluateTwos() {
        TwoFaceScore score = new TwoFaceScore();
        testFaceValue(score, 2, 6);
    }

    @Test
    public void evaluateThrees() {
        ThreeFaceScore score = new ThreeFaceScore();
        testFaceValue(score, 3, 2);
    }

    @Test
    public void evaluateFours() {
        FourFaceScore score = new FourFaceScore();
        testFaceValue(score, 4, 2);
    }

    @Test
    public void evaluateFives() {
        FiveFaceScore score = new FiveFaceScore();
        testFaceValue(score, 5, 1);
    }

    @Test
    public void evaluateSixes() {
        SixFaceScore score = new SixFaceScore();
        testFaceValue(score, 6, 5);
    }

    private void testFaceValue(Score score, final int scoreValue, int nonScoreValue) {
        Assert.assertEquals(scoreValue * 1, score.evaluate(TestDiceUtil.createDice(scoreValue, nonScoreValue, nonScoreValue, nonScoreValue, nonScoreValue)));
        Assert.assertEquals(scoreValue * 3, score.evaluate(TestDiceUtil.createDice(nonScoreValue, scoreValue, scoreValue, nonScoreValue, scoreValue)));
        Assert.assertEquals(scoreValue * 0, score.evaluate(TestDiceUtil.createDice(nonScoreValue, nonScoreValue, nonScoreValue, nonScoreValue, nonScoreValue)));
    }
}