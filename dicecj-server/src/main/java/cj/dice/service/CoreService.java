package cj.dice.service;

import cj.dice.entity.Die;
import cj.dice.InputException;
import cj.dice.command.*;
import cj.dice.entity.Player;
import cj.dice.entity.Scoreboard;
import cj.dice.entity.Turn;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Stateless
public class CoreService {

    @Inject
    private ScoreboardSerivce scoreboardSerivce;

    public static final int NUMBER_OF_DICE = 5;

    private List<InputCommand> inputCommands = new ArrayList<>();

    public Scoreboard play(Player player) {
        initCommands();
        printInstruction();
        Turn turn = initTurn(player);
        startRepl(turn);
        return turn.getScoreboard();
    }

    public Turn initTurn(Player player) {
        return new Turn(scoreboardSerivce.buildScoreboard(player), initDice(), 0);
    }

    private List<Die> initDice() {
        List<Die> dice = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            dice.add(new Die());
        }
        return dice;
    }

    private void startRepl(Turn state) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Optional<InputCommand> inputCommand;

        while (!state.getScoreboard().isComplete()) {
            userInput = scanner.next();
            inputCommand = getFirstExecutableCommand(userInput);
            if (inputCommand.isPresent()) {
                executeCommand(inputCommand, userInput, state);
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

    public void executeCommand(Optional<InputCommand> inputCommand, String userInput, Turn turn) {
        try {
            inputCommand.get().execute(userInput, turn);
            if (inputCommand.get().isRoll()) {
                turn.setNumberOfRolls(turn.getNumberOfRolls() + 1);
            }
            if (inputCommand.get().isTurnEndCommand()) {
                turn.getDice().forEach(Die::unlock);
                turn.setNumberOfRolls(0);
            }
        } catch (InputException e) {
            System.out.println(e.getMessage());
        }
    }

    private Optional<InputCommand> getFirstExecutableCommand(String userInput) {
        return inputCommands.stream().filter(c -> c.isTrigger(userInput)).findFirst();
    }
}
