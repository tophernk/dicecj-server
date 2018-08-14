package cj.dice.entity.score;

import cj.dice.DiceUtil;
import cj.dice.entity.Die;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class ThreeOfAKind extends OfAKindScore {

    public ThreeOfAKind() {
        setName("Three of a Kind");
    }

    @Override
    public int calculateValue(List<Die> dice) {
        return DiceUtil.sum(dice);
    }

    @Override
    public int getCount() {
        return 3;
    }

}
