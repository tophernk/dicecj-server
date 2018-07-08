package cj;

import java.util.List;

public abstract class OfAKindScore extends Score {

    @Override
    public int evaluate(List<Die> dice) {
        for (Die die : dice) {
            if(count(dice, die.getValue()) >= getCount()) {
                return Util.sum(dice);
            }
        }
        return 0;
    }

    private long count(List<Die> dice, int value) {
        return dice.stream().filter(d -> d.getValue() == value).count();
    }

    public abstract int getCount();
}
