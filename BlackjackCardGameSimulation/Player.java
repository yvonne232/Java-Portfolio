/**
 * Name: Ziyu(Yvonne) Lin
 * Project 2
 * EN.605.201.81.FA24
 */

 /**
  *  This class is to simulate relevant methods a player might have 
  */
 public class Player 
 {
    // Initiate instance variables
    private Hand hand;
    private int balance;

    /**
     * Set a constructor with hand, and balance
     * @param balance player's initial balance
     */ 
    public Player(int balance)
    {
        this.hand = new Hand();
        this.balance = balance;
    }

    /** This method is for the player to draw a card, and add to player's hand
     * @param deck the deck
     * @return: no returns
     */
    public void drawCard(Deck deck)
    {
        String cardDrawn = deck.drawCard();
        this.hand.addCard(cardDrawn);
        System.out.println("Player draws: " + cardDrawn);
    }

    /** This method is calculate the total value of the player's hand
     * @param: no inputs
     * @return: total value of the player's hand
     */
    public int getHandTotalValue()
    {
        return this.hand.getTotalCardValue();
    }

    /** This method is to check whether the player is bust or not. The player is bust when his hand is > 21
     * @param: no inputs
     * @return: true / false (true if the player is bust)
     */
    public boolean isBust()
    {
        return this.hand.getTotalCardValue() > 21;
    }

    /** This method is to get the player's current balance
     * @param: no inputs
     * @return: player's balance
     */
    public int getBalance()
    {
        return this.balance;
    }

    /** This method is for the player to place a bet 
     * @param bet player's bet
     * @return: no returns
     */
    public void placeBet(int bet)
    {
        if (bet > 0 && bet <= this.balance)
        {
            this.balance -= bet;
        } 
        else
        {
            System.out.println("Bet is an invalid number.");
        }
    }

    /** This method is to change player's balance if he wins the bet
     * @param bet player's bet
     * @return: no return
     */
    public void winBet(int bet)
    {
        this.balance += 2 * bet;
    }

    /** This method is to return the bet if it is a tie
     * @param bet player's bet
     * @return: no return
     */
    public void returnBet(int bet)
    {
        this.balance += bet;
    }

    /** This method is to have the player's information into a string
     * @param: no input
     * @return: no return
     */
    public String toString()
    {
        return "Player's hand: " + this.hand;
    }


}

