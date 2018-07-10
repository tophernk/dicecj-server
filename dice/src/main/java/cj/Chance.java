package cj;

import java.util.List;

public class Chance extends Score {

    public Chance() {
        super("Chance");
    }

    @Override
    public int evaluate(List<Die> dice) {
        return Util.sum(dice);
    }

}
