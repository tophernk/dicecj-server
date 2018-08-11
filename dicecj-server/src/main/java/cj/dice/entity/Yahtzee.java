package cj.dice.entity;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Yahtzee extends OfAKindScore {

    public Yahtzee() {
        setName("Yahtzee");
    }

    @Override
    public int calculateValue(List<Die> dice) {
        return 50;
    }

    @Override
    public int getCount() {
        return 5;
    }

}
