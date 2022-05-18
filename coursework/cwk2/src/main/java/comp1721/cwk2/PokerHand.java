package comp1721.cwk2;

public class PokerHand extends CardCollection
{
    public static int FULL_SIZE;

    public PokerHand()
    {
        
    }

    public PokerHand(String cardsToAdd)
    {
        
    }

    @Override
    public String toString()
    {
        return "";
    }

    public int size()
    {
        return FULL_SIZE;
    }

    public void discard()
    {
    
    }

    public void discardTo(Deck d)
    {

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