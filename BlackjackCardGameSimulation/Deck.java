/**
 * Name: Ziyu(Yvonne) Lin
 * Project 2
 * EN.605.201.81.FA24
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 *  This class is to create a deck with pokers, and relevant methods 
 */
public class Deck 
{
    // Initiate instance variables
    private ArrayList<String> cards; 

    /**
     * Create a constructor with a deck of cards
     */
    public Deck() 
    {
        String[] suits = {"Diamonds", "Hearts", "Clubs", "Spades"}; // One poker has 4 suits: "Diamonds", "Hearts", "Clubs", and "Spades"
        String[] cardNumbers = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"}; // In one suit, it has 2-10, J,Q,K,A
        this.cards = new ArrayList<>(); // Use ArrayList, so we could use shuffle and remove method later

        for (String suit: suits)
        {
            for (String cardNumber: cardNumbers)
            {
                this.cards.add(cardNumber + " of " + suit);
            }
        }

        shuffleDeck();
    }

    /** This method is to shuffle all cards use shuffle method from collections 
     * @return: no returns
     * @param: no inputs
    */

    public void shuffleDeck()
    {
        Collections.shuffle(this.cards); 
    }

    /** This method is for player and dealer to draw card from this deck. If the deck is not empty, this method is going to draw a card.
     * @return: return the card that's been drawn. Or if the deck is empty, it will return "no more cards"
     * @param: no inputs
    */
    public String drawCard() 
    {
        if (!this.cards.isEmpty())
        {
            return this.cards.remove(0); // Remove and return the top card from the deck
        } else
        {
            System.out.println("There is no more card in the deck.");
            return "No more card";  // If there is no cards in the deck, it will print the message and then return "no more card". 
        }
        
    }

    /** This method is to print the deck if we need to test.
    * @return: return the deck information.
    * @param: no inputs
    */
    public String toString()
    {
        String info = "";
        // Add every card string in the cards into the info of the deck
        for (String card: this.cards)
        { 
            info += (card + ", ");
        }
        // Need to remove the last comma
        if (!this.cards.isEmpty())
        {
            info = info.substring(0, info.length() - 2);
        }
        info += ("\nThe deck has " + this.cards.size() + " cards.");       
        return info;
    }
        
}
