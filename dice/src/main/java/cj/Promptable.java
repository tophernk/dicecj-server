package cj;

import java.util.List;

public interface Promptable extends InputCommand {
    public String prompt(Scoreboard scoreboard, List<Die> dice) throws InputException;
}
