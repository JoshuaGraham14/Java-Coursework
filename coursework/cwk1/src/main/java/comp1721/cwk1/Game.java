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

    //array which stores the formatted guesses, so that they can be later saved to the history file. 
    private ArrayList<String> guessesArray = new ArrayList<String>();

    //constructor to play today's game
    public Game (String filename) throws IOException
    {
        WordList w = new WordList(filename);

        //calculate gameNumber by calculating difference in days between today and the start date
        LocalDate startDate = LocalDate.of(2021, 6, 19);
        LocalDate today = LocalDate.now();
        gameNumber = (int) ChronoUnit.DAYS.between(startDate, today);

        target = w.getWord(gameNumber+2);
        accessibilityMode=false;
    }

    //constructor to play today's game in accessibilityMode
    public Game (String filename, boolean accessibilityMode) throws IOException
    {
        WordList w = new WordList(filename);

        //calculate gameNumber by calculating difference in days between today and the start date
        LocalDate startDate = LocalDate.of(2021, 6, 19);
        LocalDate today = LocalDate.now();
        gameNumber = (int) ChronoUnit.DAYS.between(startDate, today);

        target = w.getWord(gameNumber+2);
        this.accessibilityMode = accessibilityMode;
    }

    //constructor to play fixed game
    public Game (int num, String filename) throws IOException
    {
        gameNumber=num;
        WordList w = new WordList(filename);
        
        target = w.getWord(num);
        accessibilityMode=false;
    }

    //constructor to play fixed game in accessibilityMode
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

        //Iterate 6 times - for the 6 guesses
        for (int i=1; i<=6; i++)
        {
            System.out.printf("Enter guess (%d/6): ", i);
            String guess = INPUT.nextLine(); //user input
            Guess g = new Guess(i, guess); //create new guess object

            //formattedGuess string is the result of the conpareWith method
            String formattedGuess = g.compareWith(target);
            if (accessibilityMode)
            {
                //run accessibility version of compareWith() method if accessiblityMode is enabled
                formattedGuess = g.compareWithAccessibility(target);
            }

            //add the formattedGuess to the guesses array
            guessesArray.add(formattedGuess);
            System.out.println(formattedGuess); //print formattedGuess

            //if the guess was correct
            if(g.matches(target))
            {
                wordWasGuessed = 1; //wordWasGuessed = true
                numberOfGuesses = i;

                //print success message depending on how many guesses the user made
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
                INPUT.close(); //close scanner
                return;
            }
        }

        //if the user couldn't guess the input in 6 guesses:
        System.out.printf("You lose :(\n");
        System.out.printf("The word was: %s\n", target); //reveal the word
        wordWasGuessed = 0;
        numberOfGuesses = -1;
        INPUT.close(); //close scanner
    }

    public void save(String filename) throws IOException
    {
        Path path = Paths.get(filename);

        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(path)))
        {
            //write each element of the guessesArray to the file on seperate lines 
            for (int i=0; i<guessesArray.size(); i++)
            {
                out.printf("%s\n", guessesArray.get(i));
            }
        }
    }
    
    public void saveHistory(String filename) throws IOException
    {
        //create String versions of 3 statistics to store of each game
        String gameNumberStr = String.valueOf(gameNumber)+"\n";
        String wordWasGuessedStr = String.valueOf(wordWasGuessed)+"\n";
        String numberOfGuessesStr = String.valueOf(numberOfGuesses)+"\n";

        //append each statistic to the end of the file: 
        try {
            Files.write(Paths.get(filename), gameNumberStr.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
        }

        try {
            Files.write(Paths.get(filename), wordWasGuessedStr.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
        }

        try {
            Files.write(Paths.get(filename), numberOfGuessesStr.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
        }
    }

    public void printStatistics(String filename) throws IOException
    {
        History h = new History(filename);

        //print the stats - making use of the methods in the History class
        System.out.println("\n********** STATS **********");
        System.out.printf("Games Played: %d\n", h.getNumOfGamesPlayed());
        System.out.printf("Win Percentage: %.1f%%\n", h.getPercentageWon());
        System.out.printf("Current Winning Streak: %d\n", h.getCurrentWinningStreak());
        System.out.printf("Longest Winning Streak: %d\n", h.getLongestWinningStreak());
        
        //the getDistribution method returns an array containing 6 integers
        //each position in the array stores the number of games won in that number of guesses
        System.out.println("*------- HISTOGRAM -------*");
        System.out.printf("1: %d\n", h.getDistrubtion()[0]);
        System.out.printf("2: %d\n", h.getDistrubtion()[1]);
        System.out.printf("3: %d\n", h.getDistrubtion()[2]);
        System.out.printf("4: %d\n", h.getDistrubtion()[3]);
        System.out.printf("5: %d\n", h.getDistrubtion()[4]);
        System.out.printf("6: %d\n", h.getDistrubtion()[5]);
    }
}