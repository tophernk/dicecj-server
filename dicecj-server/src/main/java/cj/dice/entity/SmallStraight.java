package cj.dice.entity;

import javax.persistence.Entity;

@Entity
public class SmallStraight extends Straight {

    @Override
    public String getName() {
        return "Small Straight";
    }

    @Override
    protected int getRequiredLength() {
        return 4;
    }

    @Override
    protected int getFixedValue() {
        return 30;
    }
}
