package cj.dice.entity;

import cj.dice.DiceUtil;

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
