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
    public Deck()
    {
        super();

        for (Suit s : Suit.values()) 
        { 
            for (Rank r : Rank.values()) 
            { 
                Card c = new Card(r, s);
                add(c);
            }
        }
    }
    
    public Card deal()
    {
        if (cards.size() > 0)
        {
            Card c = cards.remove(0);
            return c;
        }
        else
        {
            throw new CardException("deck empty");
        }
    }
    
    public void shuffle()
    {
        Collections.shuffle(cards);
    }
}