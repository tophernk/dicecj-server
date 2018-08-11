package cj.dice.service;

import cj.dice.command.InputCommand;
import cj.dice.entity.Game;
import cj.dice.entity.Player;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Optional;

@Stateless
@Path("/command")
public class DiceResource {

    @Inject
    private PlayerService playerService;

    @Inject
    private CoreService coreService;

    @Inject
    ScoreboardSerivce scoreboardSerivce;

    @Inject
    private GameDao gameDao;

    @POST
    @Path("/newgame")
    public String newGame(String playerName) {
        Player player = playerService.findOrCreatePlayer(playerName);

        Game game = coreService.initNewGame(player);
        scoreboardSerivce.printAndHighlightScoreboardsOfPlayer(game.getScoreboard());

        String result = coreService.retrieveInstructions();
        return JsonbBuilder.create().toJson(new CommandResponse(game.getId(), result,
                game.getScoreboard().isComplete(), scoreboardSerivce.printScores(game.getScoreboard()),
                game.getCurrentNumberOfRolls()));
    }

    @POST
    public String command(String commandRequest) {
        CommandRequest request = JsonbBuilder.create().fromJson(commandRequest, CommandRequest.class);
        Game game = gameDao.findGameById(request.getGameId());
        Optional<InputCommand> inputCommand = coreService.getFirstExecutableCommand(request.getUserInput());
        String result = null;
        if (inputCommand.isPresent()) {
            result = coreService.executeCommand(inputCommand, request.getUserInput(), game);
        }
        else {
            result = "invalid input";
        }
        return JsonbBuilder.create().toJson(new CommandResponse(game.getId(), result,
                game.getScoreboard().isComplete(), scoreboardSerivce.printScores(game.getScoreboard()),
                game.getCurrentNumberOfRolls()));
    }

    @POST
    @Path("/finishGame")
    public String finishGame(String gameId) {
        Game game = gameDao.findGameById(Integer.valueOf(gameId));
        return scoreboardSerivce.printAndHighlightScoreboardsOfPlayer(game.getScoreboard());
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

        public CommandResponse(int gameId, String result, boolean isComplete, String scoreboard, int numberOfRolls) {
            this.gameId = gameId;
            this.result = result;
            this.isComplete = isComplete;
            this.scoreboard = scoreboard;
            this.numberOfRolls = numberOfRolls;
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
    }

}
