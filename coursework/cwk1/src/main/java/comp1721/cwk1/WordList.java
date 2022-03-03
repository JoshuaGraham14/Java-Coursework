package comp1721.cwk1;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class WordList 
{
    //arrayList which stores each 5 letter word as an element
    private ArrayList<String> words = new ArrayList<String>();

    public WordList (String filename) throws IOException
    {
        //read all lines from the file (until end of file), and add each individual line to the words array
        try(Scanner input = new Scanner(Paths.get(filename)))
        {
            while (input.hasNextLine())
            {
                String line = input.nextLine();
                words.add(line);
            }
        }
    }

    public int size()
    {
        //returns size of words array = number of words
        return words.size();
    }

    public String getWord (int n)
    {
        //validate n is in range before trying to get that element from the words array
        if (n<0 || n>size()-1)
        {
            throw new GameException("Word number not valid");
        }
        return words.get(n);
    }
}