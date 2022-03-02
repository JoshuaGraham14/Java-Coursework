package comp1721.cwk1;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class WordList 
{
    private ArrayList<String> words = new ArrayList<String>();

    public WordList (String filename) throws IOException
    {
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
        return words.size();
    }

    public String getWord (int n)
    {
        return words.get(n);
    }
}
