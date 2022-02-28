package comp1721.cwk1;

import java.util.Scanner;

public class Guess 
{
    // Use this to get player input in readFromPlayer()
    private static final Scanner INPUT = new Scanner(System.in);
    private static int guessNumber;
    private static String chosenWord;

    // TODO: Implement constructor with int parameter
    public Guess (int num)
    {
        //Validate guessNumber
        if (num < 1 || num > 6)
        {
            guessNumber = num;
        }
        else
        {
            throw new GameException("Guess number not between 0-6");
        }
    }

    // TODO: Implement constructor with int and String parameters
    public Guess (int num, String word)
    {
        //Validate guessNumber
        if (num < 1 || num > 6)
        {
            guessNumber = num;
        }
        else
        {
            throw new GameException("Guess number not between 0-6");
        }

        //Validate chosenWord
        boolean isWord = true;
        word = word.toUpperCase();
        for (int i=0; i<5; i++)
        {
            if(!(word.charAt(0) >= 'A' && word.charAt(0) <= 'Z'))
            {
                isWord = false;
                break;
            }
        }
        if (isWord)
        {
            chosenWord = word;
        }
        else
        {
            throw new GameException("Guess must be a word of alphabet characters");
        }
    }

    // TODO: Implement getGuessNumber(), returning an int
    public static int getGuessNumber ()
    {
        return 0;
    }

    // TODO: Implement getChosenWord(), returning a String
    public static String getChosenWord ()
    {
        return "";
    }

    // TODO: Implement readFromPlayer()
    public static void readFromPlayer ()
    {
        
    }

    // TODO: Implement compareWith(), giving it a String parameter and String return type
    public static String compareWith (String target)
    {
        return "";
    }

    // TODO: Implement matches(), giving it a String parameter and boolean return type
    public static Boolean matches (String target)
    {
        return false;
    }
}
