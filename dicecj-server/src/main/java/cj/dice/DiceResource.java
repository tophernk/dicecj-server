package cj.dice;

import cj.dice.entity.Player;
import cj.dice.entity.Scoreboard;
import cj.dice.service.CoreService;
import cj.dice.service.PlayerService;
import cj.dice.service.TurnState;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;

@Stateless
@Path("/command")
public class DiceResource {

    @Inject
    private PlayerService playerService;

    @Inject
    private CoreService coreService;

    @POST
    @Path("/newgame")
    public String newGame(String playerName) {
        Player player = playerService.findOrCreatePlayer(playerName);
        TurnState turnState = coreService.initTurn(player);

        return JsonbBuilder.create().toJson(turnState);
    }

    @POST
    public String command(String requestData) {
        TurnState turnState = new TurnState(new Scoreboard(), new ArrayList<>(), 0);
        return "command received";
//        return JsonbBuilder.create().toJson(turnState);
    }

}
