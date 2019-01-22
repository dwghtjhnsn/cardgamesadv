
/**
 * class War plays the game where 2 people each have a deck of cards. 
 * Both players turn over a card and the player with highest rank card 
 * gets both cards. If both cards have the same rank IT'S WAR. Each
 * player puts down a face down card and then each turns over another card.
 * highest rank gets all the cards played.
 * 
 * play() to play over and over until one player is out of cards
 * 
 * 
 * @author Dwight Johnson
 * @version December 8, 2015
 */
import java.io.*;
import java.util.*;

public class War
{
    /**
     * Method play will run the game repeatedly until one player is out of cards
     *
     */
    public static void play()
    {
        Deck halDeck = new Deck();
        Deck playerDeck = new Deck();
        Deck warPile = new Hand(); //accommulates the war piles until the winner gets all

        Scanner keyboard = new Scanner(System.in);
        System.out.print("My name is HAL. What's your name: ");
        String playerName = keyboard.nextLine();
        Card halCardDraw;
        Card playerCardDraw;
        halDeck.shuffle();
        playerDeck.shuffle();

        while (!gameOver(halDeck, playerDeck)) {
            halCardDraw = halDeck.draw();
            System.out.println("HAL draws a " + halCardDraw);
            playerCardDraw = playerDeck.draw();
            System.out.println(playerName +" draws a " + playerCardDraw);
            switch (halCardDraw.compare(playerCardDraw, true))
            {
                case 1: //hal wins
                System.out.println("Hal takes the cards");
                halDeck.add(halCardDraw, true);
                halDeck.add(playerCardDraw, true);
                while (warPile.count() > 0)
                {
                    halDeck.add(warPile.draw(), true);
                }
                break;
                case -1: //player wins
                System.out.println(playerName + " takes the cards");
                playerDeck.add(playerCardDraw, true);
                playerDeck.add(halCardDraw, true);
                while (warPile.count() > 0)
                {
                    playerDeck.add(warPile.draw(), true);
                }
                break;
                case 0: //war
                System.out.println("*+*+*+*+* IT'S WAR!!! *+*+*+*+*");
                warPile.add(halCardDraw, true);
                warPile.add(playerCardDraw, true);
                break;
            }
            System.out.println();
        }

        if (halDeck.count()==0)
        {
            System.out.println(playerName + " wins!!! with " + playerDeck.count() + " cards!");

        }
        else
        {
            System.out.println("Hal wins!!! with " + halDeck.count() + " cards!");
        }
    }

    /**
     * Method gameOver
     *
     * @param player1 deck of cards
     * @param player2 deck of cards
     * @return true if one player is out of cards
     */
    public static boolean gameOver(Deck player1, Deck player2)
    {
        return (player1.count()==0) || (player2.count() == 0);
    }
}