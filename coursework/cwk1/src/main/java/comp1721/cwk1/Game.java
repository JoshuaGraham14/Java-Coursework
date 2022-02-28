package comp1721.cwk1;

import java.io.IOException;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Game
{
    private static int gameNumber;
    private static String target;

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
        
    }

    // TODO: Implement save() method, with a String parameter
    public static void save(String filename)
    {
        
    }
}
