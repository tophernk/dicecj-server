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
        INPUT_COMMANDS.add(new ChooseScoreCommand());
        INPUT_COMMANDS.add(new LockDiceCommand());
        INPUT_COMMANDS.add(new ShowScoreboardCommand());
    }

    public static void main(String[] args) {
        List<Die> dice = new ArrayList<>();
        Player player = new Player("CJ");

        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            dice.add(new Die());
        }


        System.out.println("********");
        System.out.println("INPUT INSTRUCTIONS");
        System.out.println("--------");
        INPUT_COMMANDS.forEach(c -> System.out.println(c.retrieveInstructions()));
        System.out.println("********");

        play(player, dice);

        System.out.println("********");
        System.out.println("FINAL SCOREBOARD (" + player.getName() + ")");
        System.out.println("********");
        System.out.println(player.getScoreboard());
        System.out.println("********");

        testPersistance(player);
    }

    private static void testPersistance(Player player) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScoreUnit");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(player.getScoreboard());
        em.getTransaction().commit();
        em.close();
    }

    private static void play(Player player, List<Die> dice) {
        Scanner scanner = new Scanner(System.in);
        Optional<InputCommand> inputCommand;
        String userInput;
        int numberOfRolls = 0;
        while (!player.getScoreboard().isComplete()) {
            userInput = scanner.next();
            inputCommand = getFirstExecutableCommand(userInput, numberOfRolls);
            if (inputCommand.isPresent()) {
                numberOfRolls = executeCommand(player, dice, inputCommand, userInput, numberOfRolls);
            }
        }
    }

    private static int executeCommand(Player player, List<Die> dice, Optional<InputCommand> inputCommand, String userInput, int numberOfRolls) {
        inputCommand.get().execute(player, dice, userInput);
        if (inputCommand.get().isRoll()) {
            numberOfRolls++;
        }
        if (inputCommand.get().isTurnEndCommand()) {
            dice.forEach(Die::unlock);
            numberOfRolls = 0;
        }
        return numberOfRolls;
    }

    private static Optional<InputCommand> getFirstExecutableCommand(String userInput, int numberOfRolls) {
        return INPUT_COMMANDS.stream().filter(c -> c.isExecutable(userInput, numberOfRolls)).findFirst();
    }

}
