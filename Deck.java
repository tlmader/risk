import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

/**
 * Allows the creation of the Risk deck containing the 42 Risk cards.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
public class Deck {

	private int i;
	
	private String input;
	private String name;
	
	private String[] typesArray;
	
	private ArrayList<Card> deck;
	private ArrayList<Country> countries;

	private Card drawCard;

	/**
	* Creates all 42 cards, one for each territory. Each card has either 
	* a type of Infantry, Cavalry, or Artillery. Ensure that the number of
	* Infantry, Cavalry, and Artillery are the same
	**/
	public Deck (ArrayList<Country> countries) {		
	
		Collections.shuffle(countries);
		
		//Types of cards
		typesArray = new String[]{ "Infantry", "Cavalry", "Artillery" };
		
		deck = new ArrayList<Card>();
		
		for (i = 0; i < countries.size(); i++) {
		// Add new cards to deck
			deck.add(new Card(typesArray[i / 14], countries.get(i)));
			System.out.println("Added new card to deck: " + deck.get(i).getName());
		}
		Collections.shuffle(deck);
	}

	/**
	* Removes a card from the deck and return it
	**/
	public Card draw() {
	
		drawCard = deck.get(0);
		deck.remove(0);
		
		return drawCard;
	}

	/**
	* Add a card to the deck
	**/
	public void add(Card card) {
	
		deck.add(card);
	}

	/**
	* Shuffle the deck of cards
	**/
	public void shuffle() {
	
		Collections.shuffle(deck);
	}
}
