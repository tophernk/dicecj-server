package cj;

import javax.persistence.Entity;

@Entity
public class SixFaceScore extends FaceValueOfAKindScore {

    public SixFaceScore() {
        super("Sixes");
    }

    @Override
    public int getFaceValue() {
        return 6;
    }
}
