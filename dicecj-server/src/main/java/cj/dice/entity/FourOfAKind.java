package cj.dice.entity;

import cj.dice.DiceUtil;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class FourOfAKind extends OfAKindScore {

    @Override
    public int calculateValue(List<Die> dice) {
        return DiceUtil.sum(dice);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public String getName() {
        return "Four of a kind";
    }
}
