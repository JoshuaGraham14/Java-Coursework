package comp1721.cwk1;

import java.io.IOException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Game
{
    private static int gameNumber;
    private static String target;

    private static ArrayList<Guess> guessesArray = new ArrayList<Guess>();

    // TODO: Implement constructor with String parameter
    public Game (String filename) throws IOException
    {
        WordList w = new WordList(filename);
        LocalDate startDate = LocalDate.of(2021, 6, 19);
        LocalDate today = LocalDate.now();

        gameNumber = (int) ChronoUnit.DAYS.between(startDate, today);
        target = w.getWord(gameNumber);
    }

    // TODO: Implement constructor with int and String parameters
    public Game (int num, String filename) throws IOException
    {
        WordList w = new WordList(filename);
        target = w.getWord(num);
    }

    // TODO: Implement play() method
    public static void play()
    {
        System.out.printf("WORDLE %s/n/n", gameNumber);
        for (int i=1; i<=6; i++)
        {
            System.out.printf("Enter guess (%d/6): ", i);
            Scanner INPUT = new Scanner(System.in);
            String guess = INPUT.nextLine();
            INPUT.close();
            Guess g = new Guess(i, guess);
            System.out.println(g.compareWith(target));
            if(g.matches(target))
            {
                System.out.print("Well done!");
                break;
            }
        }
    }

    // TODO: Implement save() method, with a String parameter
    public static void save(String filename)
    {
        
    }
}
