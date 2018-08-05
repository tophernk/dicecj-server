package cj.dice.entity;

import javax.persistence.Entity;

@Entity
public class FourFaceScore extends FaceValueOfAKindScore {

    public FourFaceScore() {
        super("Fours");
    }

    @Override
    public int getFaceValue() {
        return 4;
    }
}
