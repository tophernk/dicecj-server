package cj.service;

import cj.entity.Player;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyVisibilityStrategy;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Path("/players")
@ApplicationPath("/resources")
public class PlayerResource extends Application {

    @Path("/player")
    @GET
    public String player() {
        Player cj = ServiceSupport.getPlayerService().findPlayerByName("CJ");

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
