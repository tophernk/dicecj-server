package cj;

import java.util.List;

public abstract class Score {

    public abstract int evaluate(List<Die> dice);
    public abstract String getName();
}
