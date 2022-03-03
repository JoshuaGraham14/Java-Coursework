package comp1721.cwk1;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class History 
{
    //3 array lists which will store the stats retrieved from the history.txt file
    private ArrayList<Integer> gameNumbersArray = new ArrayList<Integer>();
    private ArrayList<Integer> wordWasGuessedArray = new ArrayList<Integer>();
    private ArrayList<Integer> numberOfGuessesArray = new ArrayList<Integer>();

    public History (String filename) throws IOException
    {
        int x = 0;
        //read to the end of the file
        try(Scanner input = new Scanner(Paths.get(filename)))
        {
            while (input.hasNextLine())
            {
                x++;
                String line = input.nextLine();

                //cycle every 3rd line
                if(x%3==1)
                {
                    gameNumbersArray.add(Integer.parseInt(line));
                }
                else if(x%3==2)
                {
                    wordWasGuessedArray.add(Integer.parseInt(line));
                }
                else
                {
                    numberOfGuessesArray.add(Integer.parseInt(line));
                }
            }
        }
    }

    public Integer getNumOfGamesPlayed()
    {
        return gameNumbersArray.size();
    }

    public Float getPercentageWon()
    {
        float winTotal = 0;
        //total number of wins is = the sum of elements in the wordWasGuessedArray because:
        //1 = user won the game (guessed the word within 6 guesses)
        //0 = user lost the game
        for (int i=0; i<wordWasGuessedArray.size(); i++)
        {
            winTotal+=wordWasGuessedArray.get(i);
        }

        //winPercentage = ( totalNumberOfWins / numberOfGamesPlayed ) x 100
        float percentage = (winTotal/getNumOfGamesPlayed())*100;
        
        return percentage;
    }

    public Integer getCurrentWinningStreak()
    {
        int total = 0;

        //iterate from end of getNumOfGamesPlayed array, counting number of wins until a loss is encountered
        for (int i=getNumOfGamesPlayed()-1; i>=0; i--)
        {
            if(wordWasGuessedArray.get(i) == 1)
            {
                total++;
            }
            else
            {
                break;
            }
        }

        return total;
    }

    public Integer getLongestWinningStreak()
    {
        int total = 0;
        int maxTotal = 0;

        //iterate through getNumOfGamesPlayed array
        for (int i=0; i<getNumOfGamesPlayed(); i++)
        {
            //count each streak by totalling the number of 1's (wins) found in a row
            if(wordWasGuessedArray.get(i) == 1)
            {
                total++;
            }
            else
            {
                //if the total is bigger than maxTotal, then it is the new longest winning streak
                if (total>maxTotal)
                {
                    maxTotal = total;
                }
                total = 0;
            }
        }

        //replace maxTotal with total if the users current steak is the longest streak
        if (total>maxTotal)
        {
            maxTotal = total;
        }

        return maxTotal;
    }

    public Integer[] getDistrubtion()
    {
        Integer[] distributionArray = {0,0,0,0,0,0};

        //iterate through getNumOfGamesPlayed arrray
        for (int i=0; i<getNumOfGamesPlayed(); i++)
        {
            if (numberOfGuessesArray.get(i) != -1)
            {
                //increment the value of the element in the distributionArray at the element corresponding to the number of guesses taken
                distributionArray[numberOfGuessesArray.get(i)-1]++;
            }
        }

        return distributionArray;
    }
}