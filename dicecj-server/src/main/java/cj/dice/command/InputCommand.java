package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Game;

public abstract class InputCommand {

    private String trigger;

    public InputCommand(String trigger) {
        this.trigger = trigger;
    }

    public abstract String execute(String userInput, Game game) throws InputException;

    public boolean isTurnEndCommand() {
        return false;
    }

    public final boolean isTrigger(String inputTrigger) {
        return inputTrigger != null && inputTrigger.equals(this.trigger);
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
