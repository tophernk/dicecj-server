package cj.dice.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class Straight extends Score {

    @Override
    public String getName() {
        return "Straight";
    }

    protected int getRequiredLength() {
        return 5;
    }

    protected int getFixedValue() {
        return 40;
    }

    @Override
    public int evaluate(List<Die> dice) {
        List<Die> diceCopy = new ArrayList<>(dice);
        diceCopy.sort(Comparator.comparingInt(Die::getValue));
        return determineStraightLength(diceCopy) >= getRequiredLength() ? getFixedValue() : 0;
    }

    private int determineStraightLength(List<Die> dice) {
        int straightLength = 1;
        for (int i = 0; i < dice.size() - 1; i++) {
            if (isNextValueStraightValue(dice, i)) {
                straightLength++;
            }
        }
        return straightLength;
    }

    private boolean isNextValueStraightValue(List<Die> dice, int i) {
        return Math.abs((dice.get(i).getValue() - dice.get(i + 1).getValue())) == 1;
    }
}
