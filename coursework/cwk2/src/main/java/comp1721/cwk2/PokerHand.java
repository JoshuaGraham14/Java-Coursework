package comp1721.cwk2;

import comp1721.cwk2.Card.Rank;
import comp1721.cwk2.Card.Suit;

/**
 * Representation of a poker hand.
 * @author Joshua Graham
 */
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
        //First check that the PokerHand contains 5 cards 
        if (!isFiveCards()) return false;

        int totalPairs = 0;
        //Loop through each possible rank in ascending order
        for (Rank r : Rank.values()) 
        {
            int totalCards = 0;

            //Loop through the cards in the PokerHand
            for (Card c : cards)
            {
                //If a Card's rank matches the current Rank r of the outer loop,
                //then we have found a card from that Rank, so increment the totalCards variable.
                if(r.compareTo(c.getRank()) == 0)
                {
                    totalCards++;
                }
            }
            
            //If two cards have been found from the same rank, 
            //then a pair has been found, so increment totalPairs variable.
            if (totalCards == 2)
            {
                totalPairs++;
            }
        }

        //IF the number of pairs found is 1, then return true.
        if(totalPairs == 1)
        {
            return true;
        }

        return false; //else no pairs have been found, so return false
    }

    /**
   * Checks if the PokerHand contains two pairs.
   * @return True if PokerHand contains two pairs, false otherwise
   */
    public boolean isTwoPairs()
    {
        //First check that the PokerHand contains 5 cards
        if (!isFiveCards()) return false;

        int totalPairs = 0;
        //Loop through each possible rank in ascending order
        for (Rank r : Rank.values()) 
        {
            int totalCards = 0;

            //Loop through the cards in the PokerHand
            for (Card c : cards)
            {
                //If a Card's rank matches the current Rank r of the outer loop,
                //then we have found a card from that Rank, so increment the totalCards variable.
                if(r.compareTo(c.getRank()) == 0)
                {
                    totalCards++;
                }
            }
            
            //If two cards have been found from the same rank, 
            //then a pair has been found, so increment totalPairs variable.
            if (totalCards == 2)
            {
                totalPairs++;
            }
        }

        //IF the number of pairs found is 2, then return true.
        if(totalPairs == 2)
        {
            return true;
        }

        return false; //else no pairs have been found, so return false
    }

    /**
   * Checks if the PokerHand contains a three of a kind.
   * @return True if PokerHand contains a three of a kind, false otherwise
   */
    public boolean isThreeOfAKind()
    {
        //First check that the PokerHand contains 5 cards
        if (!isFiveCards()) return false;

        int totalThreeOfAKind = 0;
        //Loop through each possible rank in ascending order
        for (Rank r : Rank.values()) 
        {
            int totalCards = 0;

            //Loop through the cards in the PokerHand
            for (Card c : cards)
            {
                //If a Card's rank matches the current Rank r of the outer loop,
                //then we have found a card from that Rank, so increment the totalCards variable.
                if(r.compareTo(c.getRank()) == 0)
                {
                    totalCards++;
                }
            }
            
            //If three cards have been found from the same rank, 
            //then a three of a kind has been found, so increment totalThreeOfAKind variable.
            if (totalCards == 3)
            {
                totalThreeOfAKind++;
            }
        }

        //If a three of a kind has been found and there has not been a pair found (in order to ensure that it is not a full house), then return true.
        if(totalThreeOfAKind == 1 && isPair() == false)
        {
            return true;
        }

        return false; //else no three of a kinds have been found, so return false
    }

    /**
   * Checks if the PokerHand contains a four of a kind.
   * @return True if PokerHand contains a four of a kind, false otherwise
   */
    public boolean isFourOfAKind()
    {
        //First check that the PokerHand contains 5 cards
        if (!isFiveCards()) return false;

        //Loop through each possible rank in ascending order
        for (Rank r : Rank.values()) 
        {
            int totalCards = 0;

            //Loop through the cards in the PokerHand
            for (Card c : cards)
            {
                //If a Card's rank matches the current Rank r of the outer loop,
                //then we have found a card from that Rank, so increment the totalCards variable.
                if(r.compareTo(c.getRank()) == 0)
                {
                    totalCards++;
                }
            }
            
            //If four cards have been found from the same rank, 
            //then a four of a kind has been found, so we can return true.
            if (totalCards == 4)
            {
                return true;
            }
        }

        return false; //else no four of a kinds have been found, so return false
    }

    /**
   * Checks if the PokerHand contains a full house.
   * @return True if PokerHand contains a full house, false otherwise
   */
    public boolean isFullHouse()
    {
        //First check that the PokerHand contains 5 cards
        if (!isFiveCards()) return false;

        int totalThreeOfAKind = 0;
        //Loop through each possible rank in ascending order
        for (Rank r : Rank.values()) 
        {
            int totalCards = 0;

            //Loop through the cards in the PokerHand
            for (Card c : cards)
            {
                //If a Card's rank matches the current Rank r of the outer loop,
                //then we have found a card from that Rank, so increment the totalCards variable.
                if(r.compareTo(c.getRank()) == 0)
                {
                    totalCards++;
                }
            }
            
            //If three cards have been found from the same rank, 
            //then a three of a kind has been found, so increment totalThreeOfAKind variable.
            if (totalCards == 3)
            {
                totalThreeOfAKind++;
            }
        }

        //If a three of a kind has been found and there has also been a pair found, then return true.
        if(totalThreeOfAKind == 1 && isPair() == true)
        {
            return true;
        }

        return false; //else no full houses have been found, so return false
    }

    /**
   * Checks if the PokerHand contains a flush.
   * @return True if PokerHand contains a flush, false otherwise
   */
    public boolean isFlush()
    {
        //First check that the PokerHand contains 5 cards
        if (!isFiveCards()) return false;

         //Loop through each possible suit in ascending order
        for (Suit s : Suit.values())
        {
            int totalCards = 0;

            //Loop through the cards in the PokerHand
            for (Card c : cards)
            {
                //If a Card's suit matches the current Suit s of the outer loop,
                //then we have found a card from that suit, so increment the totalCards variable.
                if(s.compareTo(c.getSuit()) == 0)
                {
                    totalCards++;
                }
            }
            
            //If five cards have been found from the same suit, 
            //then a flush has been found, so return true.
            if (totalCards == 5)
            {
                return true;
            }
        }

        return false; //else no flushes have been found, so return false
    }

    /**
   * Checks if the PokerHand contains a straight.
   * @return True if PokerHand contains a straight, false otherwise
   */
    public boolean isStraight()
    {
        //First check that the PokerHand contains 5 cards
        if (!isFiveCards()) return false;
        
        //First order the cards in the PokerHand by rank (ascending).
        sortCardsByRank();

        //CHECK: cards are adjacent:
        //Loop through each index of the cards list
        for (int i = 0; i < cards.size()-1; i++)
        {
            //Compares two adjacent cards to see if they are in ascending rank order
            if(cards.get(i).getRank().compareTo(cards.get(i+1).getRank()) != -1)
            {
                //If the first card is an Ace and the next card is a Ten, continue, as the Ace can also be a high card.
                if(cards.get(i).getRank() == Rank.ACE && cards.get(i+1).getRank() == Rank.TEN)
                {
                    continue;
                }

                //Cards are not adjacent, so a straight cannot exist, so return false
                return false; 
            }
        }

        //If successful a straight exists so return true:
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
            //Loop through each index of the cards list
            for (int i = 0; i < cards.size()-1; i++)
            {
                //If two adjacent Cards are in the wrong order (according to their rank), swap them:
                if(cards.get(i).getRank().compareTo(cards.get(i+1).getRank()) > 0)
                {
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