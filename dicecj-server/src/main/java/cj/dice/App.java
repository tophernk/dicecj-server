package cj.dice;

import cj.dice.entity.Player;
import cj.dice.entity.Scoreboard;
import cj.dice.service.CrudSupport;
import cj.dice.service.ServiceSupport;

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
