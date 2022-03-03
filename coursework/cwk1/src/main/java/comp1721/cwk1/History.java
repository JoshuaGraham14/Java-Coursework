package comp1721.cwk1;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class History 
{
    private ArrayList<Integer> gameNumbersArray = new ArrayList<Integer>();
    private ArrayList<Integer> wordWasGuessedArray = new ArrayList<Integer>();
    private ArrayList<Integer> numberOfGuessesArray = new ArrayList<Integer>();

    public History (String filename) throws IOException
    {
        int x = 0;
        try(Scanner input = new Scanner(Paths.get(filename)))
        {
            while (input.hasNextLine())
            {
                x++;
                String line = input.nextLine();
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
        for (int i=0; i<wordWasGuessedArray.size(); i++)
        {
            winTotal+=wordWasGuessedArray.get(i);
        }

        float percentage = (winTotal/getNumOfGamesPlayed())*100;
        
        return percentage;
    }

    public Integer getCurrentWinningStreak()
    {
        int total = 0;

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

        for (int i=0; i<getNumOfGamesPlayed(); i++)
        {    
            if(wordWasGuessedArray.get(i) == 1)
            {
                total++;
            }
            else
            {
                if (total>maxTotal)
                {
                    maxTotal = total;
                }
                total = 0;
            }
        }

        if (total>maxTotal)
        {
            maxTotal = total;
        }

        return maxTotal;
    }

    public Integer[] getDistrubtion()
    {
        Integer[] distributionArray = {0,0,0,0,0,0};

        for (int i=0; i<getNumOfGamesPlayed(); i++)
        {
            if (numberOfGuessesArray.get(i) != -1)
            {
                distributionArray[numberOfGuessesArray.get(i)-1]++;
            }
        }

        return distributionArray;
    }
}