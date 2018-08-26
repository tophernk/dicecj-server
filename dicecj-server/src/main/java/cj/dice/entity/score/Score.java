package cj.dice.entity.score;

import cj.dice.entity.Die;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public abstract class Score implements Comparable<Score> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int value;

    private int index;

    public Score() {
    }

    public Score(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public abstract int evaluate(List<Die> dice);

    @Override
    public int compareTo(Score score) {
        return this.equals(score) ? 0 : Integer.compare(getIndex(), score.getIndex());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value &&
                index == score.index &&
                Objects.equals(name, score.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, index);
    }
}