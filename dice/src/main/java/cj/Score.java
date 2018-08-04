package cj;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class Score implements Comparable<Score> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int value;

    private int index;

    @Transient
    private int fixedValue;

    public Score() {
    }

    public Score(String name) {
        this.name = name;
    }

    public Score(String name, int value) {
        this(name);
        this.fixedValue = value;
    }

    public String getName() {
        return name;
    }

    public int getFixedValue() {
        return fixedValue;
    }

    public abstract int evaluate(List<Die> dice);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(Score score) {
        return Integer.compare(getIndex(), score.getIndex());
    }
}