package cj.dice.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SCOREBOARD_ID")
    private Scoreboard scoreboard;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "GAME_DIE", joinColumns = @JoinColumn(name = "GAME_ID"),
            inverseJoinColumns = @JoinColumn(name = "DIE_ID"))
    private List<Die> dice;

    @Column(name = "CURRENT_NUMBER_OF_ROLLS")
    private int currentNumberOfRolls;

    public Game() {
    }

    public Game(Scoreboard scoreboard, List<Die> dice, int currentNumberOfRolls) {
        this.scoreboard = scoreboard;
        this.dice = dice;
        this.currentNumberOfRolls = currentNumberOfRolls;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public List<Die> getDice() {
        return dice;
    }

    public void setDice(List<Die> dice) {
        this.dice = dice;
    }

    public int getCurrentNumberOfRolls() {
        return currentNumberOfRolls;
    }

    public void setCurrentNumberOfRolls(int currentNumberOfRolls) {
        this.currentNumberOfRolls = currentNumberOfRolls;
    }
}
