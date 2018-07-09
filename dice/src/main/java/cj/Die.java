package cj;

import java.util.Random;

public class Die {
    private static final Random NUMBER_GENERATOR = new Random();
    private int value;
    private boolean locked;

    public void roll() {
        if(isLocked()) {
            return;
        }
        value = NUMBER_GENERATOR.nextInt(6) + 1;
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

    public void lock() {
        this.locked = true;
    }

    public void unlock() {
        this.locked = false;
    }
}
