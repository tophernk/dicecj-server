package cj;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ScoreboardSerivce {

    public List<Scoreboard> findTopScoreboardsByPlayer(Player player) {
        return new ScoreboardDao().findScoreboardsByPlayer(player);
    }

    public int determineScoreboardRank(Scoreboard scoreboard) {
        List<Scoreboard> topScoreboardsByPlayer = findTopScoreboardsByPlayer(scoreboard.getPlayer());
        topScoreboardsByPlayer.add(scoreboard);
        topScoreboardsByPlayer.sort(Comparator.comparingInt(Scoreboard::getTotal));
        return topScoreboardsByPlayer.indexOf(scoreboard);
    }
}
