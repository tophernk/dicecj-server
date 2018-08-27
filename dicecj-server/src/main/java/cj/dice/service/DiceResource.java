package cj.dice.service;

import cj.dice.command.InputCommand;
import cj.dice.entity.Game;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Optional;

@Stateless
@Path("/command")
public class DiceResource {

    public static final String NO_RESPONSE = "no response";
    @Inject
    private CoreService coreService;

    @Inject
    private GameDao gameDao;

    @POST
    public String command(String commandRequest) {
        try (Jsonb json = coreService.createJson()) {
            CoreService.CommandRequest request = json != null ? json.fromJson(commandRequest, CoreService.CommandRequest.class) : null;
            if (request == null) {
                return NO_RESPONSE;
            }
            Game game = gameDao.findGameById(request.getGameId());
            Optional<InputCommand> inputCommand = coreService.getFirstExecutableCommand(extractTrigger(request.getUserInput()));
            if (inputCommand.isPresent()) {
                return coreService.executeCommand(inputCommand.get(), extractArgumentByPosition(request.getUserInput(), 1), game);
            } else {
                return coreService.buildResult(game, "invalid input");
            }
        } catch (Exception e) {
            return NO_RESPONSE;
        }
    }

    private String extractTrigger(String userInput) {
        return extractArgumentByPosition(userInput, 0);
    }

    private String extractArgumentByPosition(String userInput, int position) {
        String[] splittedInput = splitByColon(userInput);
        return splittedInput.length > position ? splittedInput[position] : null;
    }

    private String[] splitByColon(String userInput) {
        return userInput.split(":");
    }

    @GET
    @Path("/overview")
    public String commandOverView() {
        return coreService.retrieveCommandOverview();
    }

}
