package comp1721.cwk2;

import comp1721.cwk2.Card.Rank;
import comp1721.cwk2.Card.Suit;

public class PokerHand extends CardCollection
{
    public final static int FULL_SIZE = 5;

      /**
   * Class constructor: creates a PokerHand object.
   */
    public PokerHand()
    {   
        super(); //call super constructor
    }

      /**
   * Class constructor: creates a PokerHand object.
   * @param cardsToAdd string of cards to add to the PokerHand.
   */
    public PokerHand(String cardsToAdd)
    {
        String[] splitStr = cardsToAdd.split("\\s+"); //Split string by spaces

        //loop through each split string 
        for (String s : splitStr) 
        {
            Card c = new Card(s);
            add(c);
        }
    }

    /**
   * Overrides superclass: Adds the given card to this collection.
   *
   * @param card Card to be added
   */
    public void add(Card card) 
    {
        //Check card is not a duplicate and that the PokerHand is not full.
        if (!contains(card) && cards.size() < FULL_SIZE)
        {
            super.add(card);
        }
        else
        {
            throw new CardException("Duplicate card");
        }
    }

     /**
   * Creates a two-character string representation for every card in the PokerHand.
   *
   * <p>The first character represents rank, the second represents suit.
   * Special Unicode symbols will be used for the latter if
   * <code>Card.fancySymbols</code> is set to <code>true</code>.</p>
   *
   * @return String representation of the PokerHand
   */
    @Override
    public String toString()
    {
        String stringToPrint = "";

        //loop through the cards in the PokerHand and append the Card's string representation to the stringToPrint.
        for (Card c : cards) 
        {
            stringToPrint=stringToPrint+c.toString()+ " ";
        }

        //Remove space at the end of string if the PokerHand is empty.
        if(stringToPrint!="")
        {
            stringToPrint = stringToPrint.substring(0, stringToPrint.length() - 1);
        }

        return stringToPrint; //return string.
    }

    /**
   * Overrides superclass: Discards all the cards from this collection.
   */
    public void discard()
    {
        //Only discard IF the deck is not empty:
        if (cards.size() > 0)
        {
            super.discard();
        }
        else
        {
            throw new CardException("hand empty");
        }
    }

    /**
   * Discards all the cards from PokerHand BUT returns cards to the deck specified.
   * @param d the deck to return cards to
   */
    public void discardTo(Deck d)
    {
        for (Card c : cards)
        {
            d.add(c);
        }
        discard();
    }

    /**
   * Checks if the PokerHand contains a pair.
   * @return True if PokerHand contains a pair, false otherwise
   */
    public boolean isPair()
    {
        if (!isFiveCards()) return false;

        int totalPairs = 0;
        for (Rank r : Rank.values()) 
        {
            int totalCards = 0;
            for (Card c : cards)
            {
                if(r.compareTo(c.getRank()) == 0)
                {
                    totalCards++;
                }
            }
            
            if (totalCards == 2)
            {
                totalPairs++;
            }
        }

        if(totalPairs == 1)
        {
            return true;
        }

        return false;
    }

    /**
   * Checks if the PokerHand contains two pairs.
   * @return True if PokerHand contains two pairs, false otherwise
   */
    public boolean isTwoPairs()
    {
        if (!isFiveCards()) return false;

        int totalPairs = 0;
        for (Rank r : Rank.values()) 
        {
            int totalCards = 0;
            for (Card c : cards)
            {
                if(r.compareTo(c.getRank()) == 0)
                {
                    totalCards++;
                }
            }
            
            if (totalCards == 2)
            {
                totalPairs++;
            }
        }

        if(totalPairs == 2)
        {
            return true;
        }

        return false;
    }

    /**
   * Checks if the PokerHand contains a three of a kind.
   * @return True if PokerHand contains a three of a kind, false otherwise
   */
    public boolean isThreeOfAKind()
    {
        if (!isFiveCards()) return false;

        int totalThreeOfAKind = 0;
        for (Rank r : Rank.values()) 
        {
            int totalCards = 0;
            for (Card c : cards)
            {
                if(r.compareTo(c.getRank()) == 0)
                {
                    totalCards++;
                }
            }
            
            if (totalCards == 3)
            {
                totalThreeOfAKind++;
            }
        }

        if(totalThreeOfAKind == 1 && isPair() == false)
        {
            return true;
        }

        return false;
    }

    /**
   * Checks if the PokerHand contains a four of a kind.
   * @return True if PokerHand contains a four of a kind, false otherwise
   */
    public boolean isFourOfAKind()
    {
        if (!isFiveCards()) return false;

        for (Rank r : Rank.values()) 
        {
            int totalCards = 0;
            for (Card c : cards)
            {
                if(r.compareTo(c.getRank()) == 0)
                {
                    totalCards++;
                }
            }
            
            if (totalCards == 4)
            {
                return true;
            }
        }

        return false;
    }

    /**
   * Checks if the PokerHand contains a full house.
   * @return True if PokerHand contains a full house, false otherwise
   */
    public boolean isFullHouse()
    {
        if (!isFiveCards()) return false;

        int totalThreeOfAKind = 0;
        for (Rank r : Rank.values()) 
        {
            int totalCards = 0;
            for (Card c : cards)
            {
                if(r.compareTo(c.getRank()) == 0)
                {
                    totalCards++;
                }
            }
            
            if (totalCards == 3)
            {
                totalThreeOfAKind++;
            }
        }

        if(totalThreeOfAKind == 1 && isPair() == true)
        {
            return true;
        }

        return false;
    }

    /**
   * Checks if the PokerHand contains a flush.
   * @return True if PokerHand contains a flush, false otherwise
   */
    public boolean isFlush()
    {
        if (!isFiveCards()) return false;

        for (Suit s : Suit.values())
        {
            int totalCards = 0;
            for (Card c : cards)
            {
                if(s.compareTo(c.getSuit()) == 0)
                {
                    totalCards++;
                }
            }
            
            if (totalCards == 5)
            {
                return true;
            }
        }

        return false;
    }

    /**
   * Checks if the PokerHand contains a straight.
   * @return True if PokerHand contains a straight, false otherwise
   */
    public boolean isStraight()
    {
        if (!isFiveCards()) return false;
        
        sortCardsByRank();

        //Check cards are adjacent
        for (int i = 0; i < cards.size()-1; i++)
        {
            if(cards.get(i).getRank().compareTo(cards.get(i+1).getRank()) != -1)
            {
                if(cards.get(i).getRank() == Rank.ACE && cards.get(i).getRank().compareTo(cards.get(i+1).getRank()) == -9)
                {
                    continue;
                }
                return false;
            }
        }

        return true;
    }

    /**
   * Checks if the PokerHand contains 5 cards.
   * @return True if PokerHand contains 5 cards, false otherwise
   */
    public boolean isFiveCards()
    {
        if(cards.size() < 5)
        {
            return false;
        }
        return true;
    }

    /**
   * Sorts the PokerHand cards by rank.
   */
    public void sortCardsByRank()
    {
        //Sort cards (using bubble sort)
        boolean sorted = false;
        while (!sorted)
        {
            sorted = true;
            for (int i = 0; i < cards.size()-1; i++)
            {
                if(cards.get(i).getRank().compareTo(cards.get(i+1).getRank()) > 0)
                {
                    //Cards are in wrong order so swap:
                    Card temp = cards.get(i+1);
                    cards.remove(i+1);
                    cards.add(i+1, cards.get(i));
                    cards.remove(i);
                    cards.add(i, temp);
                    sorted = false;
                }
            }
        }
    }
}