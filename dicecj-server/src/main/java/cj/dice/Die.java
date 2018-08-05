package cj.dice;

import java.util.Random;

public class Die {

    private static final int RESET_VALUE = Integer.MAX_VALUE;
    private static final int UPPER_BOUND = 6;
    private static final Random NUMBER_GENERATOR = new Random();
    private int value;
    private boolean locked;

    public Die() {
        reset();
    }

    public void roll() {
        if (isRollable()) {
            value = randomDieNumber();
        }
    }

    private boolean isRollable() {
        return !isLocked() || !isValid();
    }

    private int randomDieNumber() {
        return NUMBER_GENERATOR.nextInt(UPPER_BOUND) + 1;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isLocked() {
        return locked;
    }

    public void toggleLock() {
        locked = !locked;
    }

    public void unlock() {
        this.locked = false;
    }

    public boolean isValid() {
        return value > 0 && value <= UPPER_BOUND;
    }

    public void reset() {
        value = RESET_VALUE;
    }

    @Override
    public String toString() {
        String out = value == RESET_VALUE ? "x" : Integer.toString(value);
        return isLocked() ? "[" + out + "]" : out;
    }
}
