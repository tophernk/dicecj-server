package cj.dice.service;

import cj.dice.entity.Die;
import cj.dice.InputException;
import cj.dice.command.*;
import cj.dice.entity.Game;
import cj.dice.entity.Player;

import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class CoreService {

    @Inject
    private ScoreboardSerivce scoreboardSerivce;

    @Inject
    private GameDao gameDao;

    public static final int NUMBER_OF_DICE = 5;

    @Inject
    @Any
    private Instance<InputCommand> inputCommands;

    public Game initNewGame(Player player) {
        Game game = gameDao.findGameByPlayer(player);
        if (game == null) {
            game = gameDao.createGame(new Game(scoreboardSerivce.buildScoreboard(player), initDice(), 0));
        }
        if (game.getScoreboard().isComplete()) {
            game.setScoreboard(scoreboardSerivce.buildScoreboard(player));
        }
        return game;
    }

    private List<Die> initDice() {
        List<Die> dice = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            dice.add(new Die());
        }
        return dice;
    }

    public String retrieveInstructions() {
        StringBuilder stringBuilder = new StringBuilder("********\n")
                .append("INPUT INSTRUCTIONS\n")
                .append("--------\n");
        inputCommands.forEach(c -> stringBuilder.append(c.retrieveInstructions() + "\n"));
        return stringBuilder.append("********\n").toString();
    }

    public String executeCommand(Optional<InputCommand> inputCommand, String userInput, Game game) {
        try {
            String result = inputCommand.get().execute(userInput, game);
            if (inputCommand.get().isRoll()) {
                game.setCurrentNumberOfRolls(game.getCurrentNumberOfRolls() + 1);
            }
            if (inputCommand.get().isTurnEndCommand()) {
                game.getDice().forEach(Die::unlock);
                game.setCurrentNumberOfRolls(0);
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
