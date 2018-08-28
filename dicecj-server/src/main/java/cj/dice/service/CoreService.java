package cj.dice.service;

import cj.dice.InputException;
import cj.dice.command.InputCommand;
import cj.dice.entity.Die;
import cj.dice.entity.Game;
import cj.dice.entity.Player;

import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.*;
import java.util.stream.Collector;

@Stateless
public class CoreService {

    public static final String JSONB_ERROR = "Jsonb error";
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
        } else if (game.getScoreboard().isComplete()) {
            game.setScoreboard(scoreboardSerivce.buildScoreboard(player));
            game.setCurrentNumberOfRolls(0);
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

    public String retrieveCommandOverview() {
        Jsonb json = createJson();
        return json != null ? json.toJson(new StartResponse("These commands are available", collectAvailableCommands())) : JSONB_ERROR;
    }

    public String executeCommand(InputCommand inputCommand, String userInput, Game game) {
        try {
            String result = inputCommand.execute(userInput, game);
            if (inputCommand.isRoll()) {
                game.setCurrentNumberOfRolls(game.getCurrentNumberOfRolls() + 1);
            }
            if (inputCommand.isTurnEndCommand()) {
                game.getDice().forEach(Die::unlock);
                game.setCurrentNumberOfRolls(0);
            }
            return game != null ? buildResult(game, result) : result;
        } catch (InputException e) {
            return buildResult(game, e.getMessage());
        }
    }

    public Optional<InputCommand> getFirstExecutableCommand(String userInput) {
        return inputCommands.stream().filter(c -> c.isTrigger(userInput)).findFirst();
    }

    public String buildResult(Game game, String result) {
        Jsonb json = createJson();
        if (json == null) {
            return JSONB_ERROR;
        }
        if (game == null) {
            return json.toJson(new CommandResponse(-1, result + ": create a new game", false, "-", -1, "xxxxx", collectAvailableCommands()));
        }
        return json.toJson(new CommandResponse(game.getId(), result,
                game.getScoreboard().isComplete(), scoreboardSerivce.printScores(game.getScoreboard(), game.getDice()),
                game.getCurrentNumberOfRolls(), printDice(game), collectAvailableCommands()));
    }

    public Jsonb createJson() {
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb;
        } catch (Exception e) {
            return null;
        }
    }

    private HashMap<String, String> collectAvailableCommands() {
        return inputCommands.stream().collect(Collector.of(HashMap::new,
                (tmpResult, command) -> tmpResult.put(command.retrieveInstructions(), command.getTrigger()),
                (finalResult, subResult) -> {
                    finalResult.putAll(subResult);
                    return finalResult;
                }));
    }

    public String printDice(Game game) {
        StringBuilder dice = new StringBuilder("");
        for (Die d : game.getDice()) {
            dice.append(d);
        }
        return dice.toString();
    }

    public static class StartResponse {
        private String hello;
        private Map<String, String> availableCommands;

        public StartResponse(String hello, Map<String, String> availableCommands) {
            this.hello = hello;
            this.availableCommands = availableCommands;
        }

        public String getHello() {
            return hello;
        }

        public void setHello(String hello) {
            this.hello = hello;
        }

        public Map<String, String> getAvailableCommands() {
            return availableCommands;
        }

        public void setAvailableCommands(Map<String, String> availableCommands) {
            this.availableCommands = availableCommands;
        }
    }

    public static class CommandRequest {
        private int gameId;
        private String userInput;

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public String getUserInput() {
            return userInput;
        }

        public void setUserInput(String userInput) {
            this.userInput = userInput;
        }
    }


    public static class CommandResponse {
        private int gameId;
        private String result;
        private boolean isComplete;
        private String scoreboard;
        private int numberOfRolls;
        private String dice;
        private Map<String, String> availableCommands;

        public CommandResponse(int gameId, String result, boolean isComplete, String scoreboard, int numberOfRolls, String dice, Map<String, String> availableCommands) {
            this.gameId = gameId;
            this.result = result;
            this.isComplete = isComplete;
            this.scoreboard = scoreboard;
            this.numberOfRolls = numberOfRolls;
            this.dice = dice;
            this.availableCommands = availableCommands;
        }

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public boolean isComplete() {
            return isComplete;
        }

        public void setComplete(boolean complete) {
            isComplete = complete;
        }

        public String getScoreboard() {
            return scoreboard;
        }

        public void setScoreboard(String scoreboard) {
            this.scoreboard = scoreboard;
        }

        public int getNumberOfRolls() {
            return numberOfRolls;
        }

        public void setNumberOfRolls(int numberOfRolls) {
            this.numberOfRolls = numberOfRolls;
        }

        public String getDice() {
            return dice;
        }

        public void setDice(String dice) {
            this.dice = dice;
        }

        public Map<String, String> getAvailableCommands() {
            return availableCommands;
        }

        public void setAvailableCommands(Map<String, String> availableCommands) {
            this.availableCommands = availableCommands;
        }
    }
}
