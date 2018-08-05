package cj.dice;

import java.util.List;

public class DiceUtil {

    public static int sum(List<Die> dice) {
        return sumValue(dice, null);
    }

    public static int sumValue(List<Die> dice, Integer value) {
        int sum = 0;
        for(Die die : dice) {
            if(value == null || die.getValue() == value)
            sum += die.getValue();
        }
        return sum;
    }
}
