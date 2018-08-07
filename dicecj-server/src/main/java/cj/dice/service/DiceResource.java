package cj.dice.service;

import cj.dice.command.InputCommand;
import cj.dice.entity.Player;
import cj.dice.entity.Scoreboard;
import cj.dice.entity.Turn;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
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
    private TurnDao turnDao;

    @POST
    @Path("/newgame")
    public String newGame(String playerName) {
        Player player = playerService.findOrCreatePlayer(playerName);
        List<Scoreboard> scoreboardsByPlayer = scoreboardSerivce.findScoreboardsByPlayer(player);

        Turn turn = turnDao.findTurnByPlayer(playerName);
        if (turn == null) {
            turn = coreService.initTurn(player);
            turnDao.createTurn(turn);
        }
        String result = coreService.retrieveInstructions();
        return JsonbBuilder.create().toJson(new CommandResponse(turn.getId(), result, turn.getScoreboard().isComplete()));
    }

    @POST
    public String command(String commandRequest) {
        CommandRequest request = JsonbBuilder.create().fromJson(commandRequest, CommandRequest.class);
        Turn turn = turnDao.findTurnById(request.getTurnId());
        Optional<InputCommand> inputCommand = coreService.getFirstExecutableCommand(request.getUserInput());
        String result = null;
        if (inputCommand.isPresent()) {
            result = coreService.executeCommand(inputCommand, request.getUserInput(), turn);
        }
        return JsonbBuilder.create().toJson(new CommandResponse(turn.getId(), result, turn.getScoreboard().isComplete()));
    }

    public static class CommandRequest {
        private int turnId;
        private String userInput;

        public int getTurnId() {
            return turnId;
        }

        public void setTurnId(int turnId) {
            this.turnId = turnId;
        }

        public String getUserInput() {
            return userInput;
        }

        public void setUserInput(String userInput) {
            this.userInput = userInput;
        }
    }

    public static class CommandResponse {
        private int turnId;
        private String result;
        private boolean isComplete;

        public CommandResponse(int turnId, String result, boolean isComplete) {
            this.turnId = turnId;
            this.result = result;
            this.isComplete = isComplete;
        }

        public int getTurnId() {
            return turnId;
        }

        public void setTurnId(int turnId) {
            this.turnId = turnId;
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
    }

}
