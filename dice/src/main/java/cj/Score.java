package cj;

import java.util.List;

public abstract class Score {

    private String name;
    private int fixedValue;

    public Score(String name) {
        this.name = name;
    }

    public Score(String name, int value) {
        this.name = name;
        this.fixedValue = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFixedValue() {
        return fixedValue;
    }

    public abstract int evaluate(List<Die> dice);
}
