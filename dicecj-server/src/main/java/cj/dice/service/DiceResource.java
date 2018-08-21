package cj.dice.service;

import cj.dice.command.InputCommand;
import cj.dice.entity.Game;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Optional;

@Stateless
@Path("/command")
public class DiceResource {

    @Inject
    private CoreService coreService;

    @Inject
    private GameDao gameDao;

    @POST
    public String command(String commandRequest) {
        CoreService.CommandRequest request = JsonbBuilder.create().fromJson(commandRequest, CoreService.CommandRequest.class);
        Game game = gameDao.findGameById(request.getGameId());
        Optional<InputCommand> inputCommand = coreService.getFirstExecutableCommand(request.getUserInput());
        if (inputCommand.isPresent()) {
            return coreService.executeCommand(inputCommand, request.getUserInput(), game);
        } else {
            return coreService.buildResult(game, "invalid input");
        }
    }

    @Path("/overview")
    @GET
    public String commandOverView() {
        return coreService.retrieveCommandOverview();
    }

}
