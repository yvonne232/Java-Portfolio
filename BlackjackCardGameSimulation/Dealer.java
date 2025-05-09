/**
 * Name: Ziyu(Yvonne) Lin
 * Project 2
 * EN.605.201.81.FA24
 */

/**
 *  This class is to simulate relevant methods a player might have 
 */
public class Dealer 
{
    // Initiate instance variables
    private Hand hand;

    /**
     * Set a constructor with hand
     */ 
    public Dealer()
    {
        this.hand = new Hand();
    }

    /** This method is calculate the total value of the dealer's hand
     * @param: no inputs
     * @return: total value of the dealer's hand
     */
    public int getHandTotalValue()
    {
        return this.hand.getTotalCardValue();
    }

    /** This method is to check whether the dealer is bust or not. The dealer is bust when his hand is > 21
     * @param: no inputs
     * @return: true / false (true if the dealer is bust)
     */
    public boolean isBust()
    {
        return this.hand.getTotalCardValue() > 21;
    }

    /** This method is for the dealer to play. The dealer keeps taking cards until he has a hand total of 17 or greater
     * @param deck the deck
     * @return: no return
     */
    public void play(Deck deck)
    {
        while (this.hand.getTotalCardValue() < 17)
        {
            this.hand.addCard(deck.drawCard());
        }
    }

    /** This method is for the dealer to draw a card, and add to dealer's hand
     * @param: the deck
     * @return: no returns
     */
    public void drawCard(Deck deck)
    {
        this.hand.addCard(deck.drawCard());

    }

    /** This method is to have the dealer's information into a string
     * @param: no input
     * @return: no return
     */
    public String toString()
    {
        return "Dealer's hand: " + this.hand;
    }

    /** This method is to have face up card information
     * @param: no input
     * @return: no return
     */
    public String printFaceUpCard()
    {
        return "Dealer's hand: " + this.hand.getFaceUpCard() ;
    }

}
