/**
 * class Card used to represent a playing card.
 * 
 * @author Josh Bush 
 * @version December 18, 2015
 */
public class Card 
{

    private Rank rank;
    private Suit suit;

    /**
     * Card Constructor
     *
     */
    public Card()
    {
        Card random = Card.getRandom();
        rank = random.getRank();
        suit = random.getSuit();
    }

    /**
     * Card Constructor
     *
     * @param r Rank of Card to create
     * @param s Suit of Card to create
     */
    public Card(Rank r, Suit s) 
    {
        rank = r;
        suit = s;
    }

    /**
     * Method getRandom
     *
     * @return returns a random card with Suit and Rank
     */
    static public Card getRandom()
    {
        return new Card(Rank.random(), Suit.random());
    }

    /**
     * Method getSuit
     *
     * @return Suit of this Card
     */
    public Suit getSuit()
    {
        return suit;
    }

    /**
     * Method getRank
     *
     * @return Rank of this Card
     */
    public Rank getRank()
    {
        return rank;
    }

    /**
     * Method compare
     *
     * @param otherCard Card to compare
     * @param aceHigh Compare with Aces great than Kings
     * @return 1 if this card is greater than the otherCard otherwise -1
     */
    public int compare(Card otherCard, boolean aceHigh)
    {
        if (rank.equals(otherCard.getRank())) 
            return suit.compare(otherCard.getSuit());
        else if (rank.gt(otherCard.getRank(), aceHigh)) 
            return 1;
        else 
            return -1;
    }

    /**
     * Method toString
     *
     * @return String value of Card "Rank of Suit"
     */
    public String toString()
    {
        return getRank() + " of " + getSuit();
    }
}
