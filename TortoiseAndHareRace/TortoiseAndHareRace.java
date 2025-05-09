/**
 * Name: Ziyu(Yvonne) Lin
 * Project 1
 * EN.605.201.81.FA24
 */

public class TortoiseAndHareRace
{
    public static void main(String[] args) 
    {
        
        int tortoiseRace  = 1;
        int hareRace  = 1;

        System.out.println("AND THEY’RE OFF!!");
        System.out.println();


        while ((tortoiseRace < 50) && (hareRace < 50))
        {
            /* First Part: Tortoise */
            tortoiseRace = updateTortoisePosition(tortoiseRace);
            /* Second Part: Hare */
            hareRace = updateHarePosition(hareRace);
            /* Display current race */
            displayCurrentRace(tortoiseRace, hareRace);

        }

        // Print the winner
        System.out.println();
        if (tortoiseRace >= 50 && hareRace >= 50) 
        {
            System.out.println("IT’S A TIE!!");
        } 
        else if (tortoiseRace >= 50) 
        {
            System.out.println("TORTOISE WINS!!");
        } 
        else if (hareRace >= 50) 
        {
            System.out.println("HARE WINS!!");
        } 
        System.out.println();
        
    }


    public static int updateTortoisePosition(int tortoiseRace) 
    /** This method is to update the position of tortoise
     * @ param tortoiseRace: previous tortoise position
     * @ return tortoiseRace: current tortoise position
     */
    {
        int randomForTortoise;
        /* First Part: Tortoise*/
        // Generate a random number: n the range 1 ≤ n ≤ 10. For the tortoise, perform a fast plod if the number is 1-5, a slow plod if the number is 6-8, and a slip if the number is 9-10. 
        randomForTortoise = (int) (Math.random() * 10) + 1; 
        // Get the current tortoise position
        if ((randomForTortoise >= 1) && (randomForTortoise <= 5)) 
        {
            // Fast plod: 1-5, 50% of the time, 3 squares to right
            tortoiseRace += 3; 
        }
        else if ((randomForTortoise >= 6) && (randomForTortoise <= 8)) 
        {
            // Slow plod: 6-8, 30% of the time, 1 square to right
            tortoiseRace += 1; 
        }
        else if ((randomForTortoise >= 9) && (randomForTortoise <= 10)) 
        {
            // Slip: 9-10, 20% of the time, 6 squares to left
            tortoiseRace -= 6;
            // Back to the starting point instead of the negative numbers
            if (tortoiseRace < 1 ) 
            { 
                tortoiseRace = 1;
            }
        }

        // If tortoiseRace reaches the final point, get it back to position 50
        if (tortoiseRace > 50) 
        {
            tortoiseRace = 50;
        }

        return tortoiseRace;

    }

    public static int updateHarePosition(int hareRace)
    /** This method is to update the position of hare
     * @ param hareRace: previous hare position
     * @ return hareRace: current hare position
     */
    {
        /* Second Part: Hare */
        int randomForHare;
        // Generate a random number: n the range 1 ≤ n ≤ 10. For the hare, perform a big hop if the number is 1-2, a small hop if the number is 3-5, a big slip if the number is 6, a small slip if the number is 7-8, and fall asleep if the number is 9-10.
        randomForHare = (int) (Math.random() * 10) + 1; 
        // Get the current hare position
        if ((randomForHare >= 1) && (randomForHare <= 2)) 
        {
            // Big hop: 1-2, 20% of the time, 9 squares to right
            hareRace += 9; 
        }
        else if ((randomForHare >= 3) && (randomForHare <= 5)) 
        {
            // Small hop: 3-5, 30% of the time, 1 square to right
            hareRace += 1; 
        }
        else if (randomForHare == 6) 
        {
            // Big slip: 6, 10% of the time, 12 squares to left
            hareRace -= 12;
            if (hareRace < 1 ) 
            { 
                hareRace = 1;
            }
        }
        else if ((randomForHare >= 7) && (randomForHare <= 8)) 
        {
            // Small slip: 7-8, 20% of the time, 2 squares to left
            hareRace -= 2;
            if (hareRace < 1 ) 
            { 
                hareRace = 1;
            }
        }
        else if ((randomForHare >= 9) && (randomForHare <= 10)) 
        {
            // Fall asleep: 9-10, 20% of the time, 0 move
        }

        // If hareRace reaches the final point, get it back to position 50
        if (hareRace > 50) 
        {
            hareRace = 50;
        }

        return hareRace;

    }

    public static void displayCurrentRace(int tortoiseRace, int hareRace)
    /** This method is to display the current race without any returns
     * @ param tortoiseRace: current tortoise position
     * @ param hareRace: current hare position
     */
    {
        String tortoiseRaceString = new String();
        String hareRaceString = new String();
    
        // Convert the tortoise position to string
        for (int i = tortoiseRace; i > 0; i--)
        {
            tortoiseRaceString += "T";
        }

        // System.out.println(tortoiseRace);
        System.out.println(tortoiseRaceString);

        // Convert the hare position to string
        for (int i = hareRace; i > 0; i--)
        {
            hareRaceString += "H";
        }

        // System.out.println(hareRace);
        System.out.println(hareRaceString);
        

        // It is possible for the contenders to land on the same square
        // The tortoise bites the hare,
        if (hareRace == tortoiseRace) 
        {
            System.out.println("OUCH!!");
        }


        System.out.println("------------------");

    }

}