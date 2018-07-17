package cj;

import java.util.List;

public class FaceValueOfAKindScore extends Score {

    private int faceValue;

    public FaceValueOfAKindScore(int faceValue, String name) {
        super(name);
        this.faceValue = faceValue;
    }

    @Override
    public int evaluate(List<Die> dice) {
        return DiceUtil.sumValue(dice, faceValue);
    }

    public int getFaceValue() {
        return faceValue;
    }
}
