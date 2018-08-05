package cj.dice.service;

import cj.dice.entity.Player;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyVisibilityStrategy;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
