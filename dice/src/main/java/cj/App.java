package cj;

import cj.entity.Player;
import cj.entity.Scoreboard;
import cj.service.CrudSupport;
import cj.service.ServiceSupport;

/**
 * Hello dice!
 */
public class App {

    public static void main(String[] args) {
        Player player = ServiceSupport.getPlayerService().findOrCreatePlayer("CJ");

        Scoreboard scoreboard = ServiceSupport.getCoreService().play(player);

        ServiceSupport.getScoreboardSerivce().save(scoreboard);
        ServiceSupport.getScoreboardSerivce().printAndHighlightScoreboardByPlayer(scoreboard, player);

        CrudSupport.getEntityManager().close();
        System.exit(0);
    }

}
