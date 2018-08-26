package cj.dice.service;

import cj.dice.entity.Player;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Stateless
@Path("/players")
public class PlayerResource {

    @Inject
    private PlayerService playerService;

    @Path("/player")
    @POST
    public String player(String playerName) {
        Player player = playerService.findPlayerByName(playerName);
        return player != null ? player.getName() + "has been found" : playerName + "has not been found";
    }
}
