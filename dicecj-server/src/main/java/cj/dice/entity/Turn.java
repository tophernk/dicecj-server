package cj.dice.entity;

import cj.dice.entity.Die;
import cj.dice.entity.Scoreboard;

import javax.persistence.*;
import java.util.List;

@Entity
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SCOREBOARD_ID")
    private Scoreboard scoreboard;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TURN_DIE", joinColumns = @JoinColumn(name = "TURN_ID"),
            inverseJoinColumns = @JoinColumn(name = "DIE_ID"))
    private List<Die> dice;

    @Column(name = "NUMBER_OF_ROLLS")
    private int numberOfRolls;

    public Turn() {
    }

    public Turn(Scoreboard scoreboard, List<Die> dice, int numberOfRolls) {
        this.scoreboard = scoreboard;
        this.dice = dice;
        this.numberOfRolls = numberOfRolls;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public List<Die> getDice() {
        return dice;
    }

    public void setDice(List<Die> dice) {
        this.dice = dice;
    }

    public int getNumberOfRolls() {
        return numberOfRolls;
    }

    public void setNumberOfRolls(int numberOfRolls) {
        this.numberOfRolls = numberOfRolls;
    }
}
