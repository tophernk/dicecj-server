package cj;

public class Player {
    private String name;
    private Scoreboard scoreboard;

    public Player(String name) {
        this.name = name;
        this.scoreboard = new Scoreboard();
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public String getName() {
        return name;
    }
}
