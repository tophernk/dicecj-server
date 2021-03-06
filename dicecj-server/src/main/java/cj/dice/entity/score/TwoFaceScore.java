package cj.dice.entity.score;

import javax.persistence.Entity;

@Entity
public class TwoFaceScore extends FaceValueOfAKindScore {

    public TwoFaceScore() {
        super("Twos");
    }

    @Override
    public int getFaceValue() {
        return 2;
    }
}
