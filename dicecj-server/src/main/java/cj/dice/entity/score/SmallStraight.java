package cj.dice.entity.score;

import javax.persistence.Entity;

@Entity
public class SmallStraight extends Straight {

    public SmallStraight() {
        setName("Small Straight");
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
