package comp1721.cwk1;

import java.util.Scanner;

public class Guess 
{
    public static final String ANSI_RESET_BACKGROUND = "\033[0m";
    public static final String ANSI_GREEN_BACKGROUND = "\033[30;102m";
	public static final String ANSI_YELLOW_BACKGROUND = "\033[30;103m";
	public static final String ANSI_WHITE_BACKGROUND = "\033[30;107m";

    // Use this to get player input in readFromPlayer()
    private static final Scanner INPUT = new Scanner(System.in);
    private static int guessNumber;
    private static String chosenWord;

    // TODO: Implement constructor with int parameter
    public Guess (int num)
    {
        //Validate guessNumber
        if (num >= 1 || num <= 6)
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
        if (num >= 1 || num <= 6)
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
            if(!(word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') || word.length() !=5)
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
            throw new GameException("Guess not valid");
        }
    }

    // TODO: Implement getGuessNumber(), returning an int
    public static int getGuessNumber ()
    {
        return guessNumber;
    }

    // TODO: Implement getChosenWord(), returning a String
    public static String getChosenWord ()
    {
        return chosenWord;
    }

    // TODO: Implement readFromPlayer()
    public static void readFromPlayer ()
    {
        System.out.print("Enter guess: ");
        INPUT.nextLine();
    }

    // TODO: Implement compareWith(), giving it a String parameter and String return type
    public static String compareWith (String target)
    {
        String outputString = "";

        for (int i=0; i<5; i++)
        {
            if(chosenWord.charAt(i) == target.charAt(i))
            {
                outputString+= ANSI_GREEN_BACKGROUND + chosenWord.charAt(i) + ANSI_RESET_BACKGROUND;
            }
            else
            {
                boolean isFound = false;
                for (int j=0; j<5; j++)
                {
                    if (j == i)
                    {
                        continue;
                    }
                    if(chosenWord.charAt(i) == target.charAt(j))
                    {
                        outputString+= ANSI_YELLOW_BACKGROUND + chosenWord.charAt(i) + ANSI_RESET_BACKGROUND;
                        isFound = true;
                        break;
                    }
                }
                if(!isFound)
                {
                    outputString+= ANSI_WHITE_BACKGROUND + chosenWord.charAt(i) + ANSI_RESET_BACKGROUND;
                }
            }
        }
        return outputString;
    }

    // TODO: Implement matches(), giving it a String parameter and boolean return type
    public static Boolean matches (String target)
    {
        if (chosenWord.equals(target))
        {
            return true;
        }
        return false;
    }
}