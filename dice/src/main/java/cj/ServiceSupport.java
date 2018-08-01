package cj;

public class ServiceSupport {

    private static CoreService CORE_SERVICE = new CoreService();
    private static ScoreboardSerivce SCOREBOARD_SERVICE = new ScoreboardSerivce();

    public static CoreService getCoreService() {
        return CORE_SERVICE;
    }

    public static ScoreboardSerivce getScoreboardSerivce() {
        return SCOREBOARD_SERVICE;
    }

}
