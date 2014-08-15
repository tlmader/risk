import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Allows the creation of Risk Dice objects used to handle player rolling.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
public class Dice {

	private int roll;
	private int[] diceArray;
    private Random die;

    public Dice() {
	
    }

    /**
     * Returns an integer array of values between 1 and 6 representing the
     * outcome of rolling the dice.  The number of values in the array should be
     * between 1 and 3, depending on the number of dice rolled by the player.  The 
     * number of dice rolled by the player is specified by the argument numberOfDice
     **/
    public int[] roll(int numberOfDice) {
	
		diceArray = new int[numberOfDice];
		
		for(int i = 0; i < diceArray.length; i++) {
			die = new Random();
			roll = die.nextInt(5) + 1;
			diceArray[i] = roll;
		}
		
		Arrays.sort(diceArray);
		Collections.reverse(Arrays.asList(diceArray));
		
		return diceArray;
    }
}
