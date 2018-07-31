package cj;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class FaceValueOfAKindScore extends Score {

    @Transient
    private int faceValue;

    public FaceValueOfAKindScore() {
    }

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
