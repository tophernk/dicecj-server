package cj.dice.command;

import cj.dice.InputException;
import cj.dice.entity.Die;
import cj.dice.entity.Game;
import cj.dice.entity.score.Score;
import cj.dice.service.ScoreboardSerivce;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ChooseScoreCommand extends InputCommand {

    @Inject
    private ScoreboardSerivce scoreboardSerivce;

    public ChooseScoreCommand() {
        super("scr");
    }

    @Override
    public String execute(String userInput, Game game) throws InputException {
        if (game.getDice().stream().anyMatch(d -> !d.isValid())) {
            throw new InputException("please (re)roll dice");
        }
        try {
            String scoreIndex = extractScoreIndex(userInput);
            List<Score> scoringOptions = game.getScoreboard().getOpenScores();
            Score score = scoringOptions.stream().filter(s -> s.getIndex() == Integer.valueOf(scoreIndex)).findAny().orElseThrow(IllegalArgumentException::new);
            scoreboardSerivce.addScore(game.getScoreboard(), score, game.getDice());
            game.getDice().forEach(Die::reset);
            return printResult(score);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw new InputException("invalid input");
        }
    }

    private String printResult(Score score) {
        return score.getValue() + " added to " + score.getName();
    }

    private String extractScoreIndex(String userInput) throws InputException {
        String[] split = splitInputByColon(userInput);
        if (split.length > 1) {
            return split[1];
        } else {
            throw new InputException("invalid input");
        }
    }

    @Override
    public boolean isTurnEndCommand() {
        return true;
    }

    @Override
    public String retrieveInstructions() {
        return "[" + getTrigger() + ":i] lock value of score i on scoreboard";
    }

}
