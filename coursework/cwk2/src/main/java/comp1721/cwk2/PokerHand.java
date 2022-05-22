package comp1721.cwk2;

import comp1721.cwk2.Card.Rank;
import comp1721.cwk2.Card.Suit;

public class PokerHand extends CardCollection
{
    public final static int FULL_SIZE = 5;

    public PokerHand()
    {   
        super();
    }

    public PokerHand(String cardsToAdd)
    {
        String[] splitStr = cardsToAdd.split("\\s+");
        for (String s : splitStr) 
        {
            Card c = new Card(s);
            add(c);
        }
    }

    public void add(Card card) 
    {
        if (!contains(card) && cards.size() < FULL_SIZE)
        {
            super.add(card);
        }
        else
        {
            throw new CardException("Duplicate card");
        }
    }

    @Override
    public String toString()
    {
        String stringToPrint = "";
        for (Card c : cards) 
        {
            stringToPrint=stringToPrint+c.toString()+ " ";
        }
        if(stringToPrint!="")
        {
            stringToPrint = stringToPrint.substring(0, stringToPrint.length() - 1);
        }
        return stringToPrint;
    }

    public void discard()
    {
        if (cards.size() > 0)
        {
            super.discard();
        }
        else
        {
            throw new CardException("hand empty");
        }
    }

    public void discardTo(Deck d)
    {
        for (Card c : cards)
        {
            d.add(c);
        }
        discard();
    }

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

    public boolean isStraight()
    {
        if (!isFiveCards()) return false;

        //Card.useFancySymbols(true);
        // for (Card c : cards)
        // {
        //     System.out.print(c.toString() + ", ");
        // }
        // System.out.println();
        
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

    public boolean isFiveCards()
    {
        if(cards.size() < 5)
        {
            return false;
        }
        return true;
    }

    public void sortCardsByRank()
    {
        //Sort cards
        boolean sorted = false;
        while (!sorted)
        {
            sorted = true;
            for (int i = 0; i < cards.size()-1; i++)
            {
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