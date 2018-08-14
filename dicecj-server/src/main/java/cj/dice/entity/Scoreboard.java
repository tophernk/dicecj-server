package cj.dice.entity;

import cj.dice.entity.score.Score;

import javax.persistence.*;
import java.util.*;

@Entity
public class Scoreboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "INSERT_DATE")
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SCOREBOARD_CLOSEDSCORE", joinColumns = @JoinColumn(name = "SCOREBOARD_ID"),
            inverseJoinColumns = @JoinColumn(name = "SCORE_ID"))
    @OrderBy(value = "index")
    private TreeSet<Score> closedScores = new TreeSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SCOREBOARD_OPENSCORE", joinColumns = @JoinColumn(name = "SCOREBOARD_ID"),
            inverseJoinColumns = @JoinColumn(name = "SCORE_ID"))
    @OrderBy(value = "index")
    private List<Score> openScores = new ArrayList<>();

    public Scoreboard() {
    }

    public boolean isComplete() {
        return openScores.isEmpty();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Score> getOpenScores() {
        return openScores;
    }

    public void setOpenScores(List<Score> openScores) {
        this.openScores = openScores;
    }

    public TreeSet<Score> getClosedScores() {
        return closedScores;
    }

    public void setClosedScores(TreeSet<Score> closedScores) {
        this.closedScores = closedScores;
    }
}
