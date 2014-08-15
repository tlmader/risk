import java.util.ArrayList;

/**
 * Allows the creation of Risk Continent objects.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
public class Continent {

    private String name;
    private int bonusArmies;
    private ArrayList<Country> countries;

    public Continent(String name, int bonusArmies, ArrayList<Country> memberCountries) {
		this.name = name;
		this.bonusArmies = bonusArmies;
		countries = memberCountries;
		
		System.out.println("Created continent: " + name + "\nBonus armies: " + bonusArmies);
    }

    public String getName() {
		return name;
    }

    /**
     *  Returns the number of bonus armies a player gets per round when the player controls this
     * continent
     **/
    public int getBonusArmies() {
		return bonusArmies;
    }

    /**
     * Retuens a list of the country objects that are on this continent
     **/
    public ArrayList<Country> getMemberCountries() {
		return countries;
    }
}
