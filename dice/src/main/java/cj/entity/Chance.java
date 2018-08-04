package cj.entity;

import cj.DiceUtil;
import cj.Die;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Chance extends Score {

    public Chance() {
        super("Chance");
    }

    @Override
    public int evaluate(List<Die> dice) {
        return DiceUtil.sum(dice);
    }

}
