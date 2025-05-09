/**
 * Name: Ziyu(Yvonne) Lin
 * Project 2
 * EN.605.201.81.FA24
 */

import java.util.ArrayList;

/**
 *  This class is to create a hand of cards for player and dealer, and relevant methods
 */
public class Hand 
{
    // Initiate instance variables
    private ArrayList<String> cards;

    /**
     * Create a constructor with a hand of cards
     */ 
    public Hand()
    {
        this.cards = new ArrayList<>();
    }

    /** This method is to add a card string into ArrayList cards 
     * @param card one card from the deck
     * @return: no returns
    */
    public void addCard(String card)
    {
        // Example of one card string: "9 of Hearts"
        this.cards.add(card);  // Add the card string into ArrayList cards
    }

    /** This method is to turn a card string into number points. 
     * 2-10 stands for 2-10; J,Q,K stands for 10; A has a value of 11 or 1; if the hand total goes above 21 and an Ace is one of the card, the Ace can revert back to a point value of 1
     * @param card one card from the deck
     * @return: corresponding card value
    */
    public int getCardValue(String card)
    {
        String cardValue = card.split(" ")[0];
        switch (cardValue)
        {
            // If it is J,Q,K or A
            // A starts with 11 here. We will adjust the point of A in the getTotalCardValue function to see if the total goes above 21
            case "J":
            case "Q":
            case "K":
                return 10;
            case "A":
                return 11;
            default:
                return Integer.parseInt(cardValue); // turn a String value into int value; if it is 2-10, it will run the default section
        }
    }

    /** This method is to calculate the total value of the hand.
     * @param: no inputs
     * @return: no returns
     */
    public int getTotalCardValue()
    {
        int totalValue = 0;
        int aceCount = 0;
        // Loop through every card in the hand
        for (String card: this.cards)
        {
            int cardValue = getCardValue(card);
            totalValue += cardValue;
            // If the hand total goes above 21 and an Ace is one of the card, the Ace can revert back to a point value of 1
            // Count the number of "A" first
            if (cardValue == 11)
            {
                aceCount ++; // Count the number of "A"
            }       
        }

        // Adjust ace from 11 to 1 if total is over 21
        while (totalValue > 21 && aceCount > 0)
        {
            totalValue -= 10;
            aceCount--;
        }

        return totalValue;
    }

    /** This method is a toString method, which will give information that consists of cards in the hand, and total card value
     * @param: no inputs
     * @return: information consists of cards in the hand, and total card value
     */
    public String toString()
    {
        return this.cards + " | Total card value: " + getTotalCardValue();
    }

    /** This method is to get the face up card string, that the dealer object will later use. The reason we have this class is because that
     * dealer will have a face up and face down card, which means a hidden card and a card. So we only want to print/deal with the face up card
     * @param: no inputs
     * @return: face up card string
     */
    public String getFaceUpCard()
    {
        String faceUpCard = this.cards.get(0);
        int faceUpCardValue = getCardValue(faceUpCard);
        return faceUpCard + " | Card value: " + faceUpCardValue;

    }

}

