package cj;

import java.util.Comparator;
import java.util.List;

public class Straight extends Score {

    private int requiredLength;
    private int value;
    private String name;

    public Straight(int length, int value, String name) {
        this.requiredLength = length;
        this.value = value;
        this.name = name;
    }

    @Override
    public int evaluate(List<Die> dice) {
        dice.sort(Comparator.comparingInt(Die::getValue));
        return determineStraightLength(dice) >= requiredLength ? value : 0;
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

    @Override
    public String getName() {
        return name;
    }

    private boolean isNextValueStraightValue(List<Die> dice, int i) {
        return Math.abs((dice.get(i).getValue() - dice.get(i + 1).getValue())) == 1;
    }
}
