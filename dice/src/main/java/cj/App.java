package cj;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

/**
 * Hello dice!
 */
public class App {
    public static final int NUMBER_OF_DICE = 5;
    private static final List<InputCommand> INPUT_COMMANDS = new ArrayList<>();

    static {
        INPUT_COMMANDS.add(new RollDiceCommand());
        INPUT_COMMANDS.add(new SelectDiceCommand());
        INPUT_COMMANDS.add(new CancelCommand(new ChooseScoreCommand()));
        INPUT_COMMANDS.add(new ShowScoreboardCommand());
    }

    public static void main(String[] args) {
        List<Die> dice = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            dice.add(new Die());
        }

        Player player = new PlayerDao().findPlayerByName("CJ");
        System.out.println(player != null ? player.getName() : "");

        System.out.println("********");
        System.out.println("INPUT INSTRUCTIONS");
        System.out.println("--------");
        INPUT_COMMANDS.forEach(c -> System.out.println(c.retrieveInstructions()));
        System.out.println("********");

        Scoreboard scoreboard = play(player, dice);

        System.out.println("********");
        System.out.println("FINAL SCOREBOARD (" + player.getName() + ")");
        System.out.println("********");
        System.out.println(scoreboard);
        System.out.println("********");

        int scoreboardRank = new ScoreboardSerivce().determineScoreboardRank(scoreboard);
        if (scoreboardRank <= 9) {
            new ScoreboardDao().create(scoreboard);
            System.out.println("Score has been saved - highscore rank " + scoreboardRank);
        }
        System.out.println("Score has not been saved - no top 10 score");
    }

    private static Scoreboard play(Player player, List<Die> dice) {
        Scanner scanner = new Scanner(System.in);
        Optional<InputCommand> inputCommand;
        String userInput;
        int numberOfRolls = 0;
        Scoreboard scoreboard = new Scoreboard(player);
        while (!scoreboard.isComplete()) {
            userInput = scanner.next();
            inputCommand = getFirstExecutableCommand(userInput, numberOfRolls);
            if (inputCommand.isPresent()) {
                numberOfRolls = executeCommand(scoreboard, dice, inputCommand, userInput, numberOfRolls);
            }
        }
        return scoreboard;
    }

    private static int executeCommand(Scoreboard scoreboard, List<Die> dice, Optional<InputCommand> inputCommand, String userInput, int numberOfRolls) {
        try {
            inputCommand.get().execute(scoreboard, dice, userInput);
            if (inputCommand.get().isRoll()) {
                numberOfRolls++;
            }
            if (inputCommand.get().isTurnEndCommand()) {
                dice.forEach(Die::unlock);
                numberOfRolls = 0;
            }
        } catch (InputException e) {
            System.out.println(e.getMessage());
        }
        return numberOfRolls;
    }

    private static Optional<InputCommand> getFirstExecutableCommand(String userInput, int numberOfRolls) {
        return INPUT_COMMANDS.stream().filter(c -> c.isExecutable(userInput, numberOfRolls)).findFirst();
    }

}
