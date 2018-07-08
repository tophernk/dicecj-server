package cj;

import java.util.List;

public abstract class FaceValueOfAKindScore extends Score {

    @Override
    public int evaluate(List<Die> dice) {
        if(dice.stream().filter(d -> d.getValue() == getFaceValue()).count() > 2) {
            return Util.sumValue(dice, getFaceValue());
        }
        return 0;
    }

    public abstract int getFaceValue();
}
