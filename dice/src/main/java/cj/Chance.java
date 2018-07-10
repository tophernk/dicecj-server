package cj;

import java.util.List;

public class Chance extends Score {
    @Override
    public int evaluate(List<Die> dice) {
        return Util.sum(dice);
    }

    @Override
    public String getName() {
        return "Chance";
    }
}
