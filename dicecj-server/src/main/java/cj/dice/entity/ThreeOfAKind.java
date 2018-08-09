package cj.dice.entity;

import cj.dice.DiceUtil;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class ThreeOfAKind extends OfAKindScore {

    @Override
    public int calculateValue(List<Die> dice) {
        return DiceUtil.sum(dice);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public String getName() {
        return "Three of a kind";
    }
}
