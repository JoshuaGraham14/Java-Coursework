package comp1721.cwk2;

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
        Card.useFancySymbols(false);
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
        return false;
    }

    public boolean isTwoPairs()
    {
        return false;
    }

    public boolean isThreeOfAKind()
    {
        return false;
    }

    public boolean isFourOfAKind()
    {
        return false;
    }

    public boolean isFullHouse()
    {
        return false;
    }

    public boolean isFlush()
    {
        return false;
    }

    public boolean isStraight()
    {
        return false;
    }
}