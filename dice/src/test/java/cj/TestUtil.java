package cj;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {
    public static List<Die> createDice(int v1, int v2, int v3, int v4, int v5) {
        Die d1 = new Die();
        d1.setValue(v1);
        Die d2 = new Die();
        d2.setValue(v2);
        Die d3 = new Die();
        d3.setValue(v3);
        Die d4 = new Die();
        d4.setValue(v4);
        Die d5 = new Die();
        d5.setValue(v5);

        List<Die> dice = new ArrayList<>();
        dice.add(d1);
        dice.add(d2);
        dice.add(d3);
        dice.add(d4);
        dice.add(d5);
        return dice;
    }
}
