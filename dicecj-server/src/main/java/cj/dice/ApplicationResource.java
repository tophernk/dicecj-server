package cj.dice;

import cj.dice.entity.Player;
import cj.dice.service.PlayerService;
import cj.dice.service.ServiceSupport;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

@ApplicationPath("/resources")
public class ApplicationResource extends Application {
}
