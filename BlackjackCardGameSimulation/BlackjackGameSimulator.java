/**
 * Name: Ziyu(Yvonne) Lin
 * Project 2
 * EN.605.201.81.FA24
 */

import java.util.Scanner;

/** 
 * This class is to simulate the blackjack game 
 */
public class BlackjackGameSimulator 
{
    // Initiate instance variables
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private Scanner scanner;

    /**
     *  Set a constructor with scanner, deck, player and dealer
     */ 
    public BlackjackGameSimulator()
    {   
        this.scanner = new Scanner(System.in);

        // Ask user for initial balance
        int initialBalance;
        do
        {
            System.out.println();
            System.out.println("Welcome to BlackJack! How much balance would you like to start with?");
            initialBalance = this.scanner.nextInt();
            if (initialBalance <= 0)
            {
                System.out.println("Initial balance must be greater than 0. Please enter a valid number.");
            }
        } while (initialBalance <= 0);

        System.out.println("Game begins!");

        // Initiate deck, player and dealer
        this.deck = new Deck();
        this.player = new Player(initialBalance); // Get initial balance and then put into Player
        this.dealer = new Dealer();
    }
    
    /** This method is to simulate the game
     * @param: no param
     * @return: no return
     */
    public void playGame()
    {

        while (this.player.getBalance() > 0)
        {
            
            // It is a new game round. Initiate a new player and a new dealer.
            this.player = new Player(this.player.getBalance());
            this.dealer = new Dealer();

            // Prompt user for bet value
            int bet;
            do
            {
                System.out.println("Your current balance is: " + this.player.getBalance());
                System.out.println("How much would you like to bet?");
                bet = this.scanner.nextInt();
                if (bet > this.player.getBalance() || bet <= 0)
                {
                    System.out.println("The bet is an invalid number. Please enter a valid number.");
                }
            } while (bet > this.player.getBalance() || bet <= 0);


            // Player places the bet
            this.player.placeBet(bet);

            // Player Initial cards
            this.player.drawCard(deck);
            this.player.drawCard(deck);
            // Dealer initial cards: 
            this.dealer.drawCard(deck);
            this.dealer.drawCard(deck);

            System.out.println(this.player);
            System.out.println("Dealer has one face up and one face down card. | " + this.dealer.printFaceUpCard());

            // Check if player wins immediately with 21
            if (this.player.getHandTotalValue() == 21) 
            {
                System.out.println("Player hits exactly 21! Player wins immediately."); // The player could not be busted in the first round
                this.player.winBet(bet);
            }
            else
            {
                // If the player has not won immediately, it is player's turn
                String action;
                do
                {
                    // Repeatedly prompt for a valid action ("hit" or "stay")
                    do
                    {
                        System.out.println("Hit or Stay? (h for hit, s for stay)");
                        action = this.scanner.next();

                        if (!action.equalsIgnoreCase("h") && !action.equalsIgnoreCase("s"))
                        {
                            System.out.println("Please enter a valid action.");
                        }
                    } while (!action.equalsIgnoreCase("h") && !action.equalsIgnoreCase("s"));
                    
                    // If it is hit, the player draws another card
                    if (action.equalsIgnoreCase("h")) 
                    {
                        // If it is hit, the player draws another card
                        this.player.drawCard(this.deck);
                        System.out.println(this.player);

                        // If after the player draws a card and the player busts, then the dealer wins
                        if (this.player.isBust())
                        {
                            System.out.println("Player busts! Dealer wins!");
                            break;
                        } 
                        else if (this.player.getHandTotalValue() == 21) // If the player has 21 points, then the player wins
                        {
                            System.out.println("Player wins!");
                            this.player.winBet(bet);
                            break;
                        }      
                    } 
                } while (action.equalsIgnoreCase("h"));
                


                // If the player is not bust and the player chooses to stay
                // It is dealer's turn
                if (!this.player.isBust() && this.player.getHandTotalValue() != 21)
                {
                    System.out.println("It is dealer's turn.");
                    System.out.println("Dealer finished drawing cards.");
                    this.dealer.play(deck);
                    System.out.println(this.dealer);

                    // If the dealer busts, the player wins
                    if (this.dealer.isBust())
                    {
                        System.out.println("Dealer busts! Player wins!");
                        this.player.winBet(bet); // If the player wins, he wins the bet
                    } else if (this.dealer.getHandTotalValue() > this.player.getHandTotalValue()) // Compare the dealer's and the player's hand 
                    {
                        System.out.println("Dealer wins!"); 
                    } else if (this.dealer.getHandTotalValue() < this.player.getHandTotalValue()) // Compare the dealer's and the player's hand 
                    {
                        System.out.println("Player wins!");
                        this.player.winBet(bet); // If the player wins, he wins the bet
                    } else if (this.dealer.getHandTotalValue() == this.player.getHandTotalValue()) // Compare the dealer's and the player's hand 
                    {
                        System.out.println("It is a tie!");
                        this.player.returnBet(bet); // If it is a tie, he is going to have his tie back
                    }
                }
            }


            // Ask the player if he wants to play again
            String playAgainAction;
            do
            {
                System.out.print("Your current balance is " + this.player.getBalance() + ". ");
                System.out.println("Do you want to play again? (y for yes, n for no)");
                playAgainAction = this.scanner.next();
                if (!playAgainAction.equalsIgnoreCase("y") && !playAgainAction.equalsIgnoreCase("n"))
                {
                    System.out.println("Please enter a valid action.");
                }
            } while (!playAgainAction.equalsIgnoreCase("y") && !playAgainAction.equalsIgnoreCase("n"));
            
            if (playAgainAction.equalsIgnoreCase("n"))
            {   
                System.out.println("See you next time!");
                break; // If the player does not want to play, end this game
            }
            
            System.out.println();

        }

        // If the player current balance < 0
        if (this.player.getBalance() <= 0) 
        {
            System.out.println("Oops! You do not have enough money. See you next time!");
        }

    }

    /**
     * This method is the main method that calls the object BlackjackGameSimulator
     */
    public static void main(String[] args) 
    {
       BlackjackGameSimulator blackJackGame = new BlackjackGameSimulator();
       blackJackGame.playGame();
    }
}

