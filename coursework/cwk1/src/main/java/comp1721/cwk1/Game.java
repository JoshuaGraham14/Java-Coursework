package comp1721.cwk1;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import java.nio.file.*;

public class Game
{
    private int gameNumber;
    private String target;

    private ArrayList<String> guessesArray = new ArrayList<String>();

    public Game (String filename) throws IOException
    {
        WordList w = new WordList(filename);
        LocalDate startDate = LocalDate.of(2021, 6, 19);
        LocalDate today = LocalDate.now();
        
        gameNumber = (int) ChronoUnit.DAYS.between(startDate, today);
        target = w.getWord(gameNumber-2);
    }

    public Game (int num, String filename) throws IOException
    {
        gameNumber=num;
        WordList w = new WordList(filename);
        
        target = w.getWord(num);
    }

    public void play()
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

    public void save(String filename) throws IOException
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
