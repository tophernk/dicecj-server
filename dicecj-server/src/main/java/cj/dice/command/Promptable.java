package cj.dice.command;

import cj.dice.entity.Die;
import cj.dice.InputException;
import cj.dice.entity.Scoreboard;

import java.util.List;

public interface Promptable extends InputCommand {
    public String prompt(Scoreboard scoreboard, List<Die> dice) throws InputException;
}
