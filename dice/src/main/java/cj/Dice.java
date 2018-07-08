package cj;

import java.util.Random;

public class Dice {
    private static Random NUMBER_GENERATOR = new Random();
    private int value;

    public void roll() {
        value = NUMBER_GENERATOR.nextInt(5) + 1;
    }

    public int getValue() {
        return value;
    }
}
