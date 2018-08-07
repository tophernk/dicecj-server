package cj.dice.service;

import cj.dice.entity.Die;
import cj.dice.InputException;
import cj.dice.command.*;
import cj.dice.entity.Player;
import cj.dice.entity.Scoreboard;
import cj.dice.entity.Turn;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Instance;
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

    private List<InputCommand> inputCommands;

    public Scoreboard play(Player player) {
        initCommands();
        retrieveInstructions();
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

    public String retrieveInstructions() {
        StringBuilder stringBuilder = new StringBuilder("********\n")
                .append("INPUT INSTRUCTIONS\n")
                .append("--------\n");
        inputCommands.forEach(c -> stringBuilder.append(c.retrieveInstructions() + "\n"));
        return stringBuilder.append("********\n").toString();
    }

    @PostConstruct
    private void initCommands() {
        inputCommands = new ArrayList<>();
        inputCommands.add(new RollDiceCommand());
        inputCommands.add(new SelectDiceCommand());
        inputCommands.add(new CancelCommand(new ChooseScoreCommand()));
        inputCommands.add(new ShowScoreboardCommand());
    }

    public String executeCommand(Optional<InputCommand> inputCommand, String userInput, Turn turn) {
        try {
            String result = inputCommand.get().execute(userInput, turn);
            if (inputCommand.get().isRoll()) {
                turn.setNumberOfRolls(turn.getNumberOfRolls() + 1);
            }
            if (inputCommand.get().isTurnEndCommand()) {
                turn.getDice().forEach(Die::unlock);
                turn.setNumberOfRolls(0);
            }
            return  result;
        } catch (InputException e) {
            return e.getMessage();
        }
    }

    public Optional<InputCommand> getFirstExecutableCommand(String userInput) {
        return inputCommands.stream().filter(c -> c.isTrigger(userInput)).findFirst();
    }
}
