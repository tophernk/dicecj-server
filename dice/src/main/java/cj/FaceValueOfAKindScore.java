package cj;

import java.util.List;

public class FaceValueOfAKindScore extends Score {

    private int faceValue;
    private String name;

    public FaceValueOfAKindScore(int faceValue, String name) {
        this.faceValue = faceValue;
        this.name = name;
    }

    @Override
    public int evaluate(List<Die> dice) {
        if(dice.stream().filter(d -> d.getValue() == faceValue).count() > 2) {
            return Util.sumValue(dice, faceValue);
        }
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

}
