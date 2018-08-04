package cj;

public class ServiceSupport {

    private static CoreService CORE_SERVICE = new CoreService();
    private static ScoreboardSerivce SCOREBOARD_SERVICE = new ScoreboardSerivce();

    private static PlayerService PLAYER_SERVICE = new PlayerService();

    public static CoreService getCoreService() {
        return CORE_SERVICE;
    }

    public static ScoreboardSerivce getScoreboardSerivce() {
        return SCOREBOARD_SERVICE;
    }

    public static PlayerService getPlayerService() {
        return PLAYER_SERVICE;
    }

}
