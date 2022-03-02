package comp1721.cwk1;

import java.util.Scanner;

public class Guess 
{
    private static final String ANSI_RESET_BACKGROUND = " \033[0m";
    private static final String ANSI_GREEN_BACKGROUND = "\033[30;102m ";
	private static final String ANSI_YELLOW_BACKGROUND = "\033[30;103m ";
	private static final String ANSI_WHITE_BACKGROUND = "\033[30;107m ";

    // Use this to get player input in readFromPlayer()
    private static final Scanner INPUT = new Scanner(System.in);
    private int guessNumber;
    private String chosenWord;

    public Guess (int num)
    {
        //Validate guessNumber
        if (num < 1 || num > 6)
        {
            throw new GameException("Guess number not between 0-6");
        }
        guessNumber = num;
        readFromPlayer();
    }

    public Guess (int num, String word)
    {
        //Validate guessNumber
        if (num < 1 || num > 6)
        {
            throw new GameException("Guess number not between 0-6");
        }
        guessNumber = num;

        //Validate chosenWord
        boolean isWord = true;
        word = word.toUpperCase();

        if (word.length() !=5)
        {
            throw new GameException("Guess not valid");
        }

        for (int i=0; i<5; i++)
        {
            if(!(word.charAt(i) >= 'A' && word.charAt(i) <= 'Z'))
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

    public int getGuessNumber ()
    {
        return guessNumber;
    }

    public String getChosenWord ()
    {
        return chosenWord;
    }

    public void readFromPlayer ()
    {
        System.out.print("Enter guess: ");
        INPUT.nextLine();
    }

    public String compareWith (String target)
    {
        String outputString = "";
        StringBuilder targetCopy = new StringBuilder(target);

        for (int i=0; i<5; i++)
        {
            if(chosenWord.charAt(i) == target.charAt(i))
            {
                targetCopy.setCharAt(i, '_');
                continue;
            }
        }

        for (int i=0; i<5; i++)
        {
            //System.out.printf("TargetCopy: %s\n", targetCopy);
            if(targetCopy.charAt(i) == '_')
            {
                outputString+= ANSI_GREEN_BACKGROUND + chosenWord.charAt(i) + ANSI_RESET_BACKGROUND;
                continue;
            }
            
            boolean isFound = false;
            for (int j=0; j<5; j++)
            {
                //System.out.printf("guess:%d, target:%d\n", i,j);
                if(chosenWord.charAt(i) == targetCopy.charAt(j))
                {
                    //System.out.println("Yellow");
                    outputString+= ANSI_YELLOW_BACKGROUND + chosenWord.charAt(i) + ANSI_RESET_BACKGROUND;
                    targetCopy.setCharAt(j, '-');
                    isFound = true;
                    break;
                }
            }
            if(!isFound)
            {
                outputString+= ANSI_WHITE_BACKGROUND + chosenWord.charAt(i) + ANSI_RESET_BACKGROUND;
            }
        }
        return outputString;
    }

    public Boolean matches (String target)
    {
        if (chosenWord.equals(target))
        {
            return true;
        }
        return false;
    }
}