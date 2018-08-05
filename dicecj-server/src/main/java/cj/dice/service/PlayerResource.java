package cj.dice.service;

import cj.dice.entity.Player;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyVisibilityStrategy;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Stateless
@Path("/players")
public class PlayerResource {

    @Inject
    private PlayerService playerService;

    @Path("/player")
    @GET
    public String player() {
        Player cj = playerService.findPlayerByName("CJ");

        JsonbConfig config = new JsonbConfig().withPropertyVisibilityStrategy(new PropertyVisibilityStrategy() {
            @Override
            public boolean isVisible(Field field) {
                return true;
            }

            @Override
            public boolean isVisible(Method method) {
                return false;
            }
        });
        return JsonbBuilder.create(config).toJson(cj);
    }
}
