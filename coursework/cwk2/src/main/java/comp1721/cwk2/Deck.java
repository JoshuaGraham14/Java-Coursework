package comp1721.cwk2;

import comp1721.cwk2.Card.Rank;
import comp1721.cwk2.Card.Suit;

public class Deck extends CardCollection
{
    public Deck()
    {
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
        return null;
    }
    
    public void shuffle()
    {
        
    }
}