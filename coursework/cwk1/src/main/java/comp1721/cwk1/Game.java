package comp1721.cwk1;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

import java.nio.file.*;

public class Game
{
    private static int gameNumber;
    private static String target;

    private static ArrayList<String> guessesArray = new ArrayList<String>();

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
        Scanner INPUT = new Scanner(System.in);
        System.out.printf("WORDLE %s\n\n", gameNumber);
        for (int i=1; i<=6; i++)
        {
            System.out.printf("Enter guess (%d/6): ", i);
            String guess = INPUT.nextLine();
            Guess g = new Guess(i, guess);
            String formattedGuess = g.compareWith(target);
            guessesArray.add(formattedGuess);
            System.out.println(formattedGuess);
            if(g.matches(target))
            {
                if (i == 1)
                {
                    System.out.printf("Superb - Got it in one!\n");
                }
                else if (i >= 2 && i <=5)
                {
                    System.out.printf("Well done!\n");
                }
                else
                {
                    System.out.printf("That was a close call!\n");
                }
                break;
            }
        }
        INPUT.close();
    }

    // TODO: Implement save() method, with a String parameter
    public static void save(String filename) throws IOException
    {
        Path path = Paths.get(filename);

        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(path)))
        {
            for (int i=0; i<guessesArray.size(); i++)
            {
                out.printf("%s\n", guessesArray.get(i));
            }
        }
    }
}
