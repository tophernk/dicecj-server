package cj;

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
