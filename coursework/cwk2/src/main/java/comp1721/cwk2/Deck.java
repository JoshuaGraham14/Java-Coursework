package comp1721.cwk2;

import java.util.Collections;

import comp1721.cwk2.Card.Rank;
import comp1721.cwk2.Card.Suit;

/**
 * Representation of a deck of cards.
 * @author Joshua Graham
 */
public class Deck extends CardCollection
{
    /**
   * Class constructor: creates a Deck object.
   */
    public Deck()
    {
        super(); //Calls the super constructor

        //Loop through each Suit in the enum
        for (Suit s : Suit.values()) 
        { 
            //Loop through each Rank in the enum
            for (Rank r : Rank.values()) 
            { 
                //Using every combination of Suit and Rank, create a Card object and add it to the deck.
                Card c = new Card(r, s);
                super.add(c);
            }
        }
    }
    
    /**
   * Deals a card from the Deck.
   * @return Card to be dealt (top card of the Deck)
   */
    public Card deal()
    {
        //First check that the deck isn't empty
        if (cards.size() > 0)
        {
            //Remove the top card from the Deck and then return it:
            Card c = cards.remove(0);
            return c;
        }
        else
        {
            throw new CardException("deck empty");
        }
    }
    
    /**
   * Shuffles the Deck (by randomising positions of the cards list).
   */
    public void shuffle()
    {
        Collections.shuffle(cards);
    }
}