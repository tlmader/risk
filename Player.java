import java.util.ArrayList;
import java.util.HashMap;

/**
 * Allows the creation of Risk player objects.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
public class Player {

	private boolean isAI;

    private int armies;
	private int turnInCount;
	private int index;

    private String name;
		
    private HashMap<String, Country> countriesHeld;
    private HashMap<String, Continent> continentsHeld;
	
	private Hand hand;

    public Player(String name, int armies, int index, boolean isAI) {
	
		this.name = name;
		this.armies = armies;
		this.index = index;
		this.isAI = isAI;
		
		countriesHeld = new HashMap<String, Country>();
		continentsHeld = new HashMap<String, Continent>();
		
		hand = new Hand();
		
		turnInCount = 0;
    }
	
	public String getName() {
		return name;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getArmies() {
		return armies;
	}
	
	public boolean getAI() {
		return isAI;
	}
	
    /**
     * Decreases the count of the number of numArmies the player has on the board
     * This count has to reflect the actual number of numArmies the players has on 
     * the board
     **/
    public void decrementArmies(int numArmies) {
	
		armies = armies - numArmies;
		System.out.println(name + " has " + armies + " reinforcements remaining.");
    }

    /**
     * Increases the count of the number of numArmies the player has on the board
     * This count has to reflect the actual number of numArmies the players has on 
     * the board
     **/
    public void incrementArmies(int numArmies) {
	
		armies = armies + numArmies;
		System.out.println(name + " has gained " + numArmies + " reinforcements. Total available: " + armies);
    }

    /**
     * When a player conquers a country the country needs to be added to a data structure
     * that keeps track of all the countries the player occupies
     **/
    public void addCountry(Country country) {
	
		System.out.println(name + " now occupies " + country.getName() + "!");
		countriesHeld.put(country.getName(), country);
    }

    /**
     * Works like addCountry above, but can be used to add multiple countries at once
     **/
    public void addCountry(ArrayList<Country> countriesList) {
	
		for(int i = 0; i < countriesList.size(); i++) {
		
			countriesHeld.put(countriesList.get(i).getName(), countriesList.get(i));
		}
    }

    /**
     * When a player loses a country, the country must be removed from the data structure
     * tracking which countries the player occupies
     **/
    public void removeCountry(String countryName) {
		
		System.out.println(name + " no longer occupies " + countryName + "!");
		countriesHeld.remove(countryName);
    }

    /**
     * When a player occupies all the countries on a continent the continent must be added to
     * a data structure that tracks which continents the player occupies
     **/
    public void addContinent(Continent continent) {
	
		System.out.println(name + " now controls " + continent.getName() + ", granting them " + continent.getBonusArmies() + " per turn!");
		continentsHeld.put(continent.getName(), continent);
    }

    /**
     * Removes a contient from the data structure to reflect that the player no longer controls
     * the whole continent
     **/
    public void removeContinent(String continentName) {
	
		continentsHeld.remove(continentName);
    }

    /**
     * Adds a risk card to the players hand
     **/
    public void addRiskCard(Card riskCard) {
	
		hand.add(riskCard);
    }

    /**
     * Removed a set of risk cards from the players hand to reflect risk cards being turned in
     **/
    public void removeCards(int[] cardsTurnedInIndex) {
	
		hand.removeCardsFromHand(cardsTurnedInIndex[0], cardsTurnedInIndex[1], cardsTurnedInIndex[2]);
    }
	
	public ArrayList<Country> getOwnedCountries() {
	
		return new ArrayList<Country>(countriesHeld.values());
	}
	
	public int getTurnInCount() {
	
		turnInCount++;
		return turnInCount;
	}
	
	public ArrayList<Card> getHand() {
	
		return hand.getCards();
	}
	
	public Hand getHandObject() {
		
		return hand;
	}
	
	public boolean mustTurnInCards() {
	
		return hand.mustTurnInCards();
	}
}