package cj.dice.service;

import cj.dice.Die;
import cj.dice.InputException;
import cj.dice.command.*;
import cj.dice.entity.Player;
import cj.dice.entity.Scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CoreService {

    public static final int NUMBER_OF_DICE = 5;

    private List<InputCommand> inputCommands = new ArrayList<>();

    public Scoreboard play(Player player) {
        List<Die> dice = initDice();
        initCommands();
        Scoreboard scoreboard = ServiceSupport.getScoreboardSerivce().buildScoreboard(player);
        printInstruction();
        startRepl(dice, scoreboard);
        return scoreboard;
    }

    private List<Die> initDice() {
        List<Die> dice = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            dice.add(new Die());
        }
        return dice;
    }

    private void startRepl(List<Die> dice, Scoreboard scoreboard) {
        int numberOfRolls = 0;
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Optional<InputCommand> inputCommand;
        while (!scoreboard.isComplete()) {
            userInput = scanner.next();
            inputCommand = getFirstExecutableCommand(userInput);
            if (inputCommand.isPresent()) {
                numberOfRolls = executeCommand(scoreboard, dice, inputCommand, userInput, numberOfRolls);
            }
        }
    }

    public void printInstruction() {
        System.out.println("********");
        System.out.println("INPUT INSTRUCTIONS");
        System.out.println("--------");
        inputCommands.forEach(c -> System.out.println(c.retrieveInstructions()));
        System.out.println("********");
    }

    private void initCommands() {
        inputCommands.add(new RollDiceCommand());
        inputCommands.add(new SelectDiceCommand());
        inputCommands.add(new CancelCommand(new ChooseScoreCommand()));
        inputCommands.add(new ShowScoreboardCommand());
    }

    public int executeCommand(Scoreboard scoreboard, List<Die> dice, Optional<InputCommand> inputCommand, String userInput, int numberOfRolls) {
        try {
            inputCommand.get().execute(scoreboard, dice, userInput, numberOfRolls);
            if (inputCommand.get().isRoll()) {
                numberOfRolls++;
            }
            if (inputCommand.get().isTurnEndCommand()) {
                dice.forEach(Die::unlock);
                numberOfRolls = 0;
            }
        } catch (InputException e) {
            System.out.println(e.getMessage());
        }
        return numberOfRolls;
    }

    private Optional<InputCommand> getFirstExecutableCommand(String userInput) {
        return inputCommands.stream().filter(c -> c.isTrigger(userInput)).findFirst();
    }
}
