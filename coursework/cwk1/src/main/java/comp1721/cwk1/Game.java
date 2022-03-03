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

    private int wordWasGuessed;
    private int numberOfGuesses;

    private boolean accessibilityMode;

    private ArrayList<String> guessesArray = new ArrayList<String>();

    public Game (String filename) throws IOException
    {
        WordList w = new WordList(filename);
        LocalDate startDate = LocalDate.of(2021, 6, 19);
        LocalDate today = LocalDate.now();
        
        gameNumber = (int) ChronoUnit.DAYS.between(startDate, today);
        target = w.getWord(gameNumber+2);
        accessibilityMode=false;
    }

    public Game (String filename, boolean accessibilityMode) throws IOException
    {
        WordList w = new WordList(filename);
        LocalDate startDate = LocalDate.of(2021, 6, 19);
        LocalDate today = LocalDate.now();
        
        gameNumber = (int) ChronoUnit.DAYS.between(startDate, today);
        target = w.getWord(gameNumber+2);
        this.accessibilityMode = accessibilityMode;
    }

    public Game (int num, String filename) throws IOException
    {
        gameNumber=num;
        WordList w = new WordList(filename);
        
        target = w.getWord(num);
        accessibilityMode=false;
    }

    public Game (int num, String filename, boolean accessibilityMode) throws IOException
    {
        gameNumber=num;
        WordList w = new WordList(filename);
        
        target = w.getWord(num);
        this.accessibilityMode = accessibilityMode;
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
            if (accessibilityMode)
            {
                formattedGuess = g.compareWithAccessibility(target);
            }
            guessesArray.add(formattedGuess);
            System.out.println(formattedGuess);
            if(g.matches(target))
            {
                wordWasGuessed = 1;
                numberOfGuesses = i;
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
                INPUT.close();
                return;
            }
        }
        System.out.printf("You lose :(\n");
        wordWasGuessed = 0;
        numberOfGuesses = -1;
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
    
    public void saveHistory(String filename) throws IOException
    {
        String gameNumberStr = String.valueOf(gameNumber)+"\n";
        String wordWasGuessedStr = String.valueOf(wordWasGuessed)+"\n";
        String numberOfGuessesStr = String.valueOf(numberOfGuesses)+"\n";

        try {
            Files.write(Paths.get(filename), gameNumberStr.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

        try {
            Files.write(Paths.get(filename), wordWasGuessedStr.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

        try {
            Files.write(Paths.get(filename), numberOfGuessesStr.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public void printStatistics(String filename) throws IOException
    {
        History h = new History(filename);

        System.out.println("\n********** STATS **********");
        System.out.printf("Games Played: %d\n", h.getNumOfGamesPlayed());
        System.out.printf("Win Percentage: %.1f%%\n", h.getPercentageWon());
        System.out.printf("Current Winning Streak: %d\n", h.getCurrentWinningStreak());
        System.out.printf("Longest Winning Streak: %d\n", h.getLongestWinningStreak());
        System.out.println("*------- HISTOGRAM -------*");
        System.out.printf("1: %d\n", h.getDistrubtion()[0]);
        System.out.printf("2: %d\n", h.getDistrubtion()[1]);
        System.out.printf("3: %d\n", h.getDistrubtion()[2]);
        System.out.printf("4: %d\n", h.getDistrubtion()[3]);
        System.out.printf("5: %d\n", h.getDistrubtion()[4]);
        System.out.printf("6: %d\n", h.getDistrubtion()[5]);
    }
}