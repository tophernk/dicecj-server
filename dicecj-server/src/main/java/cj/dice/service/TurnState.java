package cj.dice.service;

import cj.dice.Die;
import cj.dice.entity.Scoreboard;

import java.util.List;

public class TurnState {
    private final Scoreboard scoreboard;
    private final List<Die> dice;
    private int numberOfRolls;

    public TurnState(Scoreboard scoreboard, List<Die> dice, int numberOfRolls) {
        this.scoreboard = scoreboard;
        this.dice = dice;
        this.numberOfRolls = numberOfRolls;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public List<Die> getDice() {
        return dice;
    }

    public int getNumberOfRolls() {
        return numberOfRolls;
    }

    public void setNumberOfRolls(int numberOfRolls) {
        this.numberOfRolls = numberOfRolls;
    }
}
