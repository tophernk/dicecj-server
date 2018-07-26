package cj;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class FullHouse extends Score {

    public FullHouse() {
        super("Full House", 25);
    }

    @Override
    public int evaluate(List<Die> dice) {
        Map<Integer, Integer> dieCount = countDiceValues(dice);
        return (dieCount.values().contains(3) && dieCount.values().contains(2))
                || dieCount.values().contains(5) ? getFixedValue() : 0;
    }

    private Map<Integer, Integer> countDiceValues(List<Die> dice) {
        Map<Integer, Integer> dieValueMap = new HashMap<>();
        for (Die d : dice) {
            countDie(dieValueMap, d);
        }
        return dieValueMap;
    }

    private void countDie(Map<Integer, Integer> dieValueMap, Die d) {
        if (dieValueMap.get(d.getValue()) == null) {
            dieValueMap.put(d.getValue(), 1);
        } else {
            dieValueMap.put(d.getValue(), dieValueMap.get(d.getValue()) + 1);
        }
    }

}
