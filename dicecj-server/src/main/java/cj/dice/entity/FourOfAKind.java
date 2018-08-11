package cj.dice.entity;

import cj.dice.DiceUtil;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class FourOfAKind extends OfAKindScore {

    public FourOfAKind() {
        setName("Four of a Kind");
    }

    @Override
    public int calculateValue(List<Die> dice) {
        return DiceUtil.sum(dice);
    }

    @Override
    public int getCount() {
        return 4;
    }

}
