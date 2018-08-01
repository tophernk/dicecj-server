package cj;

import javax.persistence.Entity;

@Entity
public class OneFaceScore extends FaceValueOfAKindScore {

    public OneFaceScore() {
        super("Ones");
    }

    @Override
    public int getFaceValue() {
        return 1;
    }
}
