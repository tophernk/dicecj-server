package cj;

import java.util.*;

/**
 * Hello dice!
 */
public class App {
    private static final int NUMBER_OF_DICE = 5;
    private static final int NUMBER_OF_ROLLS = 3;

    public static void main(String[] args) {
        List<Die> dice = new ArrayList<>();
        Player player = new Player("CJ");

        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            dice.add(new Die());
        }

        while (!player.getScoreboard().isComplete()) {
            turn(player, dice);
        }

        System.out.println("********");
        System.out.println("FINAL SCOREBOARD (" + player.getName() + ")");
        System.out.println("********");
        System.out.println(player.getScoreboard());
        System.out.println("********");
    }

    private static void turn(Player player, List<Die> dice) {
        Scanner scanner = new Scanner(System.in);
        int rolls = 0;
        while (rolls < NUMBER_OF_ROLLS) {
            Util.rollDice(dice);
            rolls++;
            if (rolls == NUMBER_OF_ROLLS) {
                break;
            }
            dice.forEach(d -> d.unlock());
            System.out.println("die number(s) to lock di(c)e || s to score");
            String userInput = scanner.next();
            if (userInput.equals("s")) {
                break;
            }
            lockDice(dice, userInput);
        }
        chooseScore(player, dice, scanner);
    }

    private static void lockDice(List<Die> dice, String userInput) {
        char[] chars = userInput.toCharArray();
        for (int x = 0; x < chars.length; x++) {
            dice.get(Character.getNumericValue(chars[x]) - 1).lock();
        }
    }

    private static void chooseScore(Player player, List<Die> dice, Scanner scanner) {
        System.out.println("choose score");
        List<Score> scoringOptions = player.getScoreboard().retrieveScoringOptions();
        for (Score s : scoringOptions) {
            System.out.println(s.getName() + ": " + s.evaluate(dice) + " [" + scoringOptions.indexOf(s) + "]");
        }
        String next = scanner.next();
        player.getScoreboard().addScore(scoringOptions.get(Integer.valueOf(next)), dice);
    }


}
