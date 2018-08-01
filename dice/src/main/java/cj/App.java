package cj;

/**
 * Hello dice!
 */
public class App {

    public static void main(String[] args) {
        Player player = new PlayerDao().findPlayerByName("CJ");
        System.out.println(player != null ? player.getName() : "");

        Scoreboard scoreboard = ServiceSupport.getCoreService().play(player);

        ServiceSupport.getScoreboardSerivce().save(scoreboard);
        ServiceSupport.getScoreboardSerivce().printAndHighlightScoreboardByPlayer(scoreboard, player);

        CrudSupport.getEntityManager().close();
        System.exit(0);
    }

}
