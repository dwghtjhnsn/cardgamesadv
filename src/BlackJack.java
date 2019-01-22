/**
*  This program was modified from a program written by David Eck (eck@hws.edu)
*   Link: http://math.hws.edu/eck/cs124/javanotes4/source/BlackjackConsole.java
*   Linking site: http://math.hws.edu/eck/cs124/javanotes4/source/
*/
import java.io.*;
import java.util.*;

public class BlackJack
{

   protected String getTitle() 
   {
      return "Blackjack!";
   }

   protected static void play() 
   {

      /**
         This program lets the user play Blackjack.  The computer
         acts as the dealer.  The user has a stake of $100, and
         makes a bet on each game.  The user can leave at any time,
         or will be kicked out when he loses all the money.
         House rules:  The dealer hits on a total of 16 or less
         and stands on a total of 17 or more.  Dealer wins ties.
         A new deck of cards is used for each game.
      **/

      int money;          // Amount of money the user has.
      int bet;            // Amount user bets on a game.
      boolean userWins;   // Did the user win the game?
      Scanner keyboard = new Scanner(System.in);
      System.out.println("Welcome to the game of blackjack.");
      System.out.println();
      
      money = 100;  // User starts with $100.
   
      while (true) 
      {
          System.out.println("You have " + money + " dollars.");
          do 
          {
             System.out.print("How many dollars do you want to bet?  (Enter 0 to end.)");
             System.out.print("? ");
             bet = keyboard.nextInt();
             keyboard.nextLine();
             if (bet < 0 || bet > money)
                 System.out.println("Your answer must be between 0 and " + money + '.');
          } while (bet < 0 || bet > money);
          if (bet == 0)
             break;
          userWins = playHand();
          if (userWins)
             money = money + bet;
          else
             money = money - bet;
          System.out.println();
          if (money == 0) {
             System.out.println("Looks like you've are out of money!");
             break;
          }
      }
      
      System.out.println();
      System.out.println("You leave with $" + money + '.');
      
   } // end main()
   
   
   
   public static boolean playHand() 
   {
         // Let the user play one game of Blackjack.
         // Return true if the user wins, false if the user loses.

      Deck deck;                  // A deck of cards.  A new deck for each game.
      BlackjackHand dealerHand;   // The dealer's hand.
      BlackjackHand userHand;     // The user's hand.
      
      deck = new Deck();
      //System.out.println(deck);
      dealerHand = new BlackjackHand();
      userHand = new BlackjackHand();
      Scanner keyboard = new Scanner(System.in);
      /*  Shuffle the deck, then deal two cards to each player. */
      
      deck.shuffle();
      dealerHand.add( deck.draw(), false );
      dealerHand.add( deck.draw(), false );
      userHand.add( deck.draw(), false);
      userHand.add( deck.draw(), false );
      
      System.out.println();
      System.out.println();
      
      /** Check if one of the players has Blackjack (two cards totaling to 21).
         The player with Blackjack wins the game.  Dealer wins ties.
      */
      
      if (dealerHand.getBlackjackValue() == 21) {
           System.out.println("Dealer has the " + dealerHand.getCard(0)
                                       + " and the " + dealerHand.getCard(1) + ".");
           System.out.println("You have the " + userHand.getCard(0)
                                         + " and the " + userHand.getCard(1) + ".");
           System.out.println();
           System.out.println("Dealer has Blackjack.  Dealer wins.");
           return false;
      }
      
      if (userHand.getBlackjackValue() == 21) {
           System.out.println("Dealer has the " + dealerHand.getCard(0)
                                       + " and the " + dealerHand.getCard(1) + ".");
           System.out.println("You have the " + userHand.getCard(0)
                                         + " and the " + userHand.getCard(1) + ".");
           System.out.println();
           System.out.println("You have Blackjack.  You win.");
           return true;
      }
      
      /**  If neither player has Blackjack, play the game.  The user gets a chance
          to draw cards (i.e., to "Hit").  The while loop ends when the user
          chooses to "Stand" or when the user goes over 21.
      */
      
      while (true) 
      {
          
           /* Display user's cards, and let user decide to Hit or Stand. */

           System.out.println();
           System.out.println();
           System.out.println("Your cards are:");
           for ( int i = 0; i < userHand.count(); i++ )
              System.out.println("    " + userHand.getCard(i));
           System.out.println("Your total is " + userHand.getBlackjackValue());
           System.out.println();
           System.out.println("Dealer is showing the " + dealerHand.getCard(0));
           System.out.println();
           System.out.print("Hit (H) or Stand (S)? ");
           char userAction;  // User's response, 'H' or 'S'.
           do {
              userAction = Character.toUpperCase( keyboard.nextLine().charAt(0) );
              if (userAction != 'H' && userAction != 'S')
                 System.out.print("Please respond H or S:  ");
           } while (userAction != 'H' && userAction != 'S');

           /* If the user Hits, the user gets a card.  If the user Stands, the
              dealer gets a chance to draw and the game ends.
           */

           if ( userAction == 'S' ) {
                   // Loop ends; user is done taking cards.
               break;
           }
           else {  // userAction is 'H'.
                   // Give the user a card.  If the user goes over 21, the user loses.
               Card newCard = deck.draw();
               userHand.add(newCard, false);
               System.out.println();
               System.out.println("User hits.");
               System.out.println("Your card is the " + newCard);
               System.out.println("Your total is now " + userHand.getBlackjackValue());
               if (userHand.getBlackjackValue() > 21) {
                   System.out.println();
                   System.out.println("You busted by going over 21.  You lose.");
                   System.out.println("Dealer's other card was the " + dealerHand.getCard(1));
                   return false;  
               }
           }
           
      } // end while loop
      
      /** If we get to this point, the user has Stood with 21 or less.  Now, it's
         the dealer's chance to draw.  Dealer draws cards until the dealer's total is > 16.
      */

      System.out.println();
      System.out.println("You stand.");
      System.out.println("Dealer's cards are");
      System.out.println("    " + dealerHand.getCard(0));
      System.out.println("    " + dealerHand.getCard(1));
      while (dealerHand.getBlackjackValue() <= 16) {
         Card newCard = deck.draw();
         System.out.println("Dealer hits and gets the " + newCard);
         dealerHand.add(newCard, false);
      }
      System.out.println("Dealer's total is " + dealerHand.getBlackjackValue());
      
      /** Now, the winner can be declared. */
      
      System.out.println();
      if (dealerHand.getBlackjackValue() > 21) {
         System.out.println("Dealer busted by going over 21.  You win.");
         return true;
      }
      else if (dealerHand.getBlackjackValue() == userHand.getBlackjackValue()) {
         System.out.println("Dealer wins on a tie.  You lose.");
         return false;
      }
      else if (dealerHand.getBlackjackValue() > userHand.getBlackjackValue()) {
         System.out.println("Dealer wins, " + dealerHand.getBlackjackValue() 
                          + " points to " + userHand.getBlackjackValue() + ".");
         return false;
      }
      else {
         System.out.println("You win, " + userHand.getBlackjackValue() 
                          + " points to " + dealerHand.getBlackjackValue() + ".");
         return true;
      }

   }  // end playBlackjack()
   

}  // end class