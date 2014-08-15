/**
 * Allows the creation of Risk Hand objects used to handle the cards held by a player.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/

import java.util.ArrayList;

public class Hand {

	private boolean condition;
	
	private ArrayList<Card> hand;

	/**
	 * No-arg constructor. Instantiate Deck.
	 **/
	public Hand() {
	
		hand = new ArrayList<Card>();
	}

	/**
	 * Adds the card to the hand 
	 **/
	public void add(Card card) {
	
		hand.add(card);
	}
	/**
	 * Removes the cards at the given indices from the hand
	 **/
	public void removeCardsFromHand(int index1, int index2, int index3) {
	
		if (canTurnInCards(index1, index2, index3) == true) {
			hand.remove(index3);
			hand.remove(index2);
			hand.remove(index1);
		
		} else {
			System.out.println("You must trade in three cards of the same type or one of each type.");
		}
	}

	/**
	 * returns true if the player can turn in cards
	 **/
	public boolean canTurnInCards(int index1, int index2, int index3) {
	
		condition = false;
		
		if (hand.size() >= 3) {
			if (hand.get(index1).getType().equals(hand.get(index2).getType()) && hand.get(index1).getType().equals(hand.get(index3).getType())) {
			//If all three cards have the same type
				condition = true;
				
			} else if (
				!hand.get(index1).getType().equals(hand.get(index2).getType()) && !hand.get(index1).getType().equals(hand.get(index3).getType()) && !hand.get(index2).getType().equals(hand.get(index3).getType())) {
			//If all three cards have different types
				condition = true;
			}
		}
		return condition;
	}

	/**
	 * Returns true if the player must turn in cards
	 **/
	public boolean mustTurnInCards() {
	
		condition = false;
		
		if (hand.size() >= 5) {
			condition = true;
		}
		return condition;
	}

	/**
	 * Returns the hand
	 **/
	public ArrayList<Card> getCards() {
		return hand;
	}

}