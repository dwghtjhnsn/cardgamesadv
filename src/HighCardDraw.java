
/**
 * Write a description of class PlayHighCardDraw here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;

public class HighCardDraw
{

    /**
     * Method isYes will true if the user answer yes to a question.
     *
     * @param question will be displayed on the screen for the user to answer
     * @return true if the user answers yes otherwise false.
     */
    public static boolean isYes(String question)
    {
        char answerChar;
        Scanner keyboardInput = new Scanner(System.in);
        System.out.print(question + "? ");
        String answer = keyboardInput.nextLine();
        answerChar = answer.toUpperCase().charAt(0);
        while (answerChar != 'Y' && answerChar != 'N')
        { 
            System.out.print("Please answer with a 'Y' or 'N' : ");
            answer = keyboardInput.nextLine();
            answerChar = answer.toUpperCase().charAt(0);
        }
        return answerChar=='Y';

    }

    public static void play()
    {
        Scanner keyboard = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffle();
        Card myCard;
        Card compCard;
        System.out.println("let's play high card draw!");
        do
        {
            System.out.print("\nPress ENTER to draw a card.");
            keyboard.nextLine();
            myCard = deck.draw();
            System.out.print("You drew: ");
            System.out.println(myCard);
            System.out.print("\nPress ENTER to have the computer draw a card.");
            keyboard.nextLine();
            compCard = deck.draw();
            System.out.print("The computer drew: ");
            System.out.println(compCard);
            if (myCard.compare(compCard, true)== 1)
            {
                System.out.println("\nYou win! The computer is a loser!");
            }
            else
            {
                System.out.println("\nYou loser! The computer wins!");
            }
        } while (isYes("\nDo you want to play again")); 
        System.out.println("\nThanks for playing!");
    }

}
