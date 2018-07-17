package cj;

import java.util.List;

public class OfAKindScore extends Score {

    private int count;

    public OfAKindScore(int count, String name) {
        super(name);
        this.count = count;
    }

    public OfAKindScore(int count, int value, String name) {
        super(name, value);
        this.count = count;
    }

    @Override
    public int evaluate(List<Die> dice) {
        for (Die die : dice) {
            if(count(dice, die.getValue()) >= count) {
                return getFixedValue() > 0 ? getFixedValue() : DiceUtil.sum(dice);
            }
        }
        return 0;
    }

    private long count(List<Die> dice, int value) {
        return dice.stream().filter(d -> d.getValue() == value).count();
    }
}
