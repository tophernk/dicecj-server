package cj.dice.service;

import cj.dice.entity.Player;
import cj.dice.entity.Scoreboard;
import cj.dice.entity.Turn;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

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

//        return JsonbBuilder.create().toJson(turn);
        return turn.toString();
    }

    @POST
    public String command(String playerName) {
        turnDao.findTurnByPlayer(playerName);
        Turn turn = new Turn(new Scoreboard(), new ArrayList<>(), 0);
        return "command received";
//        return JsonbBuilder.create().toJson(turn);
    }

}
