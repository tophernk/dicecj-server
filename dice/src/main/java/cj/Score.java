package cj;

import java.util.List;

public abstract class Score {

    private String name;
    private int value;

    public Score(String name) {
        this.name = name;
    }

    public Score(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public abstract int evaluate(List<Die> dice);
}
