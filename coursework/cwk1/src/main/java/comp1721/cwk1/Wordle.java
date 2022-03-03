// Main program for COMP1721 Coursework 1
// DO NOT CHANGE THIS!

package comp1721.cwk1;

import java.io.IOException;

public class Wordle {
    public static void main(String[] args) throws IOException 
    {
        Game game;

        if (args.length > 0) 
        {
            
            if (args[0].equals("-a") && args.length > 1)
            {
                // Accessiblity mode fixed
                game = new Game(Integer.parseInt(args[1]), "data/words.txt", true);
            }
            else if (args[0].equals("-a") && args.length == 1)
            {
                // Accessiblity mode + today's game
                game = new Game("data/words.txt", true);
            }
            else
            {
                // Fixed
                game = new Game(Integer.parseInt(args[0]), "data/words.txt");
            }
        }
        else 
        {
            // Play today's game
            game = new Game("data/words.txt");
        }

        game.play();
        game.save("build/lastgame.txt");
        //my additional methods
        game.saveHistory("build/history.txt");
        game.printStatistics("build/history.txt");
    }
}