package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Game;

public abstract class InputCommand {

    private String trigger;

    public abstract String execute(String userInput, Game game) throws InputException;

    public boolean isTurnEndCommand() {
        return false;
    }

    public final boolean isTrigger(String userInput) {
        String[] split = splitInputByWhiteSpaces(userInput);
        return split.length > 0 ? split[0].equals(trigger) : false;
    }

    protected String[] splitInputByWhiteSpaces(String userInput) {
        return userInput.split("\\s");
    }

    public abstract String retrieveInstructions();

    public boolean isRoll() {
        return false;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }
}
