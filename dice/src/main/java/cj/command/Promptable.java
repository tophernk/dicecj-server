package cj.command;

import cj.Die;
import cj.InputException;
import cj.entity.Scoreboard;

import java.util.List;

public interface Promptable extends InputCommand {
    public String prompt(Scoreboard scoreboard, List<Die> dice) throws InputException;
}
