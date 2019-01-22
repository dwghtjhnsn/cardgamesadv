/**
 * class Deck is used to represent a deck of cards.
 * 
 * @author Josh Bush 
 * @version December 18, 2015
 */ 

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Deck 
{

    private boolean shuffled = false;
    private ArrayList<Integer> staticCards = new ArrayList<Integer>();

    protected ArrayList<Card> cards= new ArrayList<Card>(); 
    public enum SortType{RANK, SUIT, BOTH};

    /**
     * Deck Constructor
     *
     */
    public Deck() 
    {
        fill();
        shuffle();
    }

    /**
     * Method fill builds the deck of cards in rank and suit order
     *
     */
    protected void fill()
    {
        cards.clear();

        for (Suit s: Suit.getList())
        {
            for (Rank r: Rank.getList())
            {
                cards.add(new Card(r, s));
            }
        }

        shuffled = true;
    }

    /**
     * Method sort
     *
     * @param sortType (by RANK, SUIT or BOTH)
     */
    public void sort(SortType sortType)
    {
        shuffled = false;
        staticCards.clear();

        Collections.sort(cards, new Comparator<Card>() //below is new compare card block as a parameter
            {
                @Override
                public int compare(Card card1, Card card2) 
                {
                    int result=0;
                    switch (sortType)
                    {
                        case RANK:
                        result = card1.getRank().compareTo(card2.getRank());
                        break;
                        case SUIT: 
                        result = card1.getSuit().compareTo(card2.getSuit());
                        break;
                        case BOTH:
                        default:
                        result = card1.compare(card2, true);
                    }
                    return result;
                }           
            }
        );
    }

    /**
     * Method shuffle
     * The deck is left unchanged but draw will pick a random card
     */
    public void shuffle()
    {
        shuffled = true;
    }

    /**
     * Method getShuffleIndex
     *
     * @return random index for a card
     */
    protected int getShuffleIndex()
    {
        return (int)(Math.random()*cards.size());
    }

    /**
     * Method draw
     * Draws a random card if deck shuffled or top card if not
     *
     * @return the selected Card and removes it from the Deck
     */
   public Card draw()
    {
        int index = 0;
        if (!staticCards.isEmpty()){
            index = staticCards.remove(0);
            for (int i = 0; i < staticCards.size(); i++){
            	if (staticCards.get(i) > index){
            		staticCards.set(i, staticCards.get(i)-1);
            	}
            }
        } else if (shuffled)
            index = getShuffleIndex();
        // System.out.println("Debug: index = "+ index + "; size: " + this.count());
        return cards.remove(index);
    }

    /**
     * Method peek
     *
     * @param count of cards to peek at
     * @return list of cards
     */
    public Card[] peek(int count)
    {
        return peek(count, false);
    }

    private Card[] peek(int count, boolean keepPresent)
    {
        if (count > cards.size())
            count = cards.size();

        Card[] list = new Card[count];

        if (keepPresent)
        	for (int i = 0; i < staticCards.size(); i++){
        		if (i >= count)
        			break;
        		list[i]=cards.get(staticCards.get(i));
        	}
        else
        	staticCards.clear();
        
        for (int i = staticCards.size(); i < count; i++)
        {
            int nextCard = 0;
            do {
                nextCard = shuffled? getShuffleIndex(): i;
            } while (staticCards.contains(nextCard));

            staticCards.add(nextCard);
            list[i]=cards.get(nextCard);
        }

        return list;
    }

    /**
     * Method add
     * 
     * @param c Card to add
     * @param forceDuplicate allows a Card added to be a duplicate of another Card in the Deck
     * @return false if card is duplicate and duplicate not allowed
     */
    public boolean add(Card c, boolean forceDuplicate)
    {
        if (cards.contains(c))
        {
            if (forceDuplicate) 
                cards.add(c);

            return false;
        }

        cards.add(c);
        return true;
    }

    /**
     * Method count
     *
     * @return number of Cards in Deck
     */
    public int count()
    {
        return cards.size();
    }

    public String toString()
    {
        Card[] fixedDeck = peek(count());
        String deckStr ="";
        for (int i = 0; i < count(); i++)
        {
            deckStr += i + " " + fixedDeck[i] + "\n";
        }
        return deckStr;
    }

      public Card getCard(int i) //0-based
    {
    	if (i+1 >= staticCards.size())
    		peek(i+1, true);
    	
        return cards.get(staticCards.get(i));
    }

}
