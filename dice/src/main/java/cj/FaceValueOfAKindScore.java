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
        if(dice.stream().filter(d -> d.getValue() == faceValue).count() > 2) {
            return Util.sumValue(dice, faceValue);
        }
        return 0;
    }

}
