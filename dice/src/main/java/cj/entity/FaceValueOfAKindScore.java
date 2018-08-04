package cj.entity;

import cj.DiceUtil;
import cj.Die;

import javax.persistence.Entity;
import java.util.List;

@Entity
public abstract class FaceValueOfAKindScore extends Score {

    public FaceValueOfAKindScore() {
    }

    public FaceValueOfAKindScore(String name) {
        super(name);
    }

    @Override
    public int evaluate(List<Die> dice) {
        return DiceUtil.sumValue(dice, getFaceValue());
    }

    public abstract int getFaceValue();
}
