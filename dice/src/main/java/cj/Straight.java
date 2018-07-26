package cj;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Comparator;
import java.util.List;

@Entity
public class Straight extends Score {

    @Transient
    private int requiredLength;

    public Straight(int length, int value, String name) {
        super(name, value);
        this.requiredLength = length;
    }

    @Override
    public int evaluate(List<Die> dice) {
        dice.sort(Comparator.comparingInt(Die::getValue));
        return determineStraightLength(dice) >= requiredLength ? getFixedValue() : 0;
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
