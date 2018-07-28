package cj;

import java.util.List;

public interface Promptable extends InputCommand {
    public String prompt(Player player, List<Die> dice) throws InputException;
}
