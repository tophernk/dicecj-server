package cj.dice.entity.score;

import javax.persistence.Entity;

@Entity
public class ThreeFaceScore extends FaceValueOfAKindScore {

    public ThreeFaceScore() {
        super("Threes");
    }

    @Override
    public int getFaceValue() {
        return 3;
    }
}
