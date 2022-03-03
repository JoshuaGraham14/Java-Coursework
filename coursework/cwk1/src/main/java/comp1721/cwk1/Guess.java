package comp1721.cwk1;

import java.util.Scanner;

public class Guess 
{
    //variables which store ANSI escape codes
    private static final String ANSI_RESET_BACKGROUND = " \033[0m";
    private static final String ANSI_GREEN_BACKGROUND = "\033[30;102m ";
	private static final String ANSI_YELLOW_BACKGROUND = "\033[30;103m ";
	private static final String ANSI_WHITE_BACKGROUND = "\033[30;107m ";

    // Use this to get player input in readFromPlayer()
    private static final Scanner INPUT = new Scanner(System.in);
    private int guessNumber;
    private String chosenWord;

    //array which stores the prefix for each accessibility position, i.e. 1=1st
    private String[] accessibilityPositionsArray = {"1st", "2nd", "3rd", "4th", "5th"};

    public Guess (int num)
    {
        //Validate guessNumber
        if (num < 1 || num > 6)
        {
            throw new GameException("Guess number not between 1-6");
        }
        guessNumber = num;
        readFromPlayer();
    }

    public Guess (int num, String word)
    {
        //Validate guessNumber
        if (num < 1 || num > 6)
        {
            throw new GameException("Guess number not between 1-6");
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
        String outputString = ""; //string to be built up and outputted
        StringBuilder targetCopy = new StringBuilder(target); //copy of target String so it can be modified to compare with the user's chosen word 

        //find any characters in the user's guess which are perfect matches and hide the letter by replacing it with an '_'
        for (int i=0; i<5; i++)
        {
            if(chosenWord.charAt(i) == target.charAt(i))
            {
                targetCopy.setCharAt(i, '_');
                continue;
            }
        }

        //Iterate through each character in the user's guess
        for (int i=0; i<5; i++)
        {
            if(targetCopy.charAt(i) == '_')
            {
                //Green - perfect match
                outputString+= ANSI_GREEN_BACKGROUND + chosenWord.charAt(i) + ANSI_RESET_BACKGROUND; //append that character to the outputString
                continue;
            }
            
            boolean isFound = false; //set a flag which is initialised to false
                                     //if the character at index 'i' (of chosenWord) is not found in the targetCopy array, then it will remain false

            //Iterate through each character in the targetCopy string
            for (int j=0; j<5; j++)
            {
                //compare the character in the user's guess string (chosenWord) to the character in the targetCopy string
                if(chosenWord.charAt(i) == targetCopy.charAt(j))
                {
                    //Yellow - correct but in wrong place
                    outputString+= ANSI_YELLOW_BACKGROUND + chosenWord.charAt(i) + ANSI_RESET_BACKGROUND;
                    targetCopy.setCharAt(j, '-'); //hide the character by replacing it with a '-'
                    isFound = true; //set flag to true
                    break;
                }
            }
            if(!isFound)
            {
                //White - the character at index 'i' (of chosenWord) was not found in the targetCopy array
                outputString+= ANSI_WHITE_BACKGROUND + chosenWord.charAt(i) + ANSI_RESET_BACKGROUND;
            }
        }
        return outputString;
    }

    public String compareWithAccessibility (String target)
    {
        String outputString = ""; //string to be built up and outputted
        StringBuilder targetCopy = new StringBuilder(target); //copy of target String so it can be modified to compare with the user's chosen word 

        //find any characters in the user's guess which are perfect matches and hide the letter by replacing it with an '_'
        for (int i=0; i<5; i++)
        {
            if(chosenWord.charAt(i) == target.charAt(i))
            {
                targetCopy.setCharAt(i, '_');
                continue;
            }
        }

        //Iterate through each character in the user's guess
        for (int i=0; i<5; i++)
        {
            if(targetCopy.charAt(i) == '_')
            {
                //Green - perfect match
                outputString+= accessibilityPositionsArray[i] + " perfect, ";
                continue;
            }
            
            //Iterate through each character in the targetCopy string
            for (int j=0; j<5; j++)
            {
                //compare the character in the user's guess string (chosenWord) to the character in the targetCopy string
                if(chosenWord.charAt(i) == targetCopy.charAt(j))
                {
                    //Yellow - correct but in wrong place
                    outputString+= accessibilityPositionsArray[i] + " correct but in wrong place, ";
                    targetCopy.setCharAt(j, '-'); //hide the character by replacing it with a '-'
                    break;
                }
            }
        }
        outputString = outputString.substring(0, outputString.length() - 2); //remove the last two characters (" ,") from the outputString 
        return outputString;
    }

    public Boolean matches (String target)
    {
        //if the user's chosenWord is equal to the target word then return true; else false
        if (chosenWord.equals(target))
        {
            return true;
        }
        return false;
    }
}