package comp1721.cwk1;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class WordList 
{
    private static ArrayList<String> words = new ArrayList<String>();

    // TODO: Implement constructor with a String parameter
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

    // TODO: Implement size() method, returning an int
    public static int size()
    {
        return words.size();
    }

    // TODO: Implement getWord() with an int parameter, returning a String
    public static String getWord (int n)
    {
        return words.get(n);
    }
}
