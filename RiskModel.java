/**
 * This class is the model for the Risk program.
 * It contains the data, methods, and functions that are required to manipulate the data of 
 * the Risk Game.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Random;

import java.lang.StringBuilder;
import java.lang.IllegalArgumentException;
import java.lang.NumberFormatException;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class RiskModel extends Observable {

	private boolean isAI;
	private boolean canTurnInCards;
	private boolean canReinforce;
	private boolean canAttack;
	private boolean canFortify;
	private boolean deployPhase;
	private boolean deployed;
	private boolean isInt;
	private boolean isLoaded;
	private boolean add;
	private boolean add1;
	private boolean add2;
	
	private int i;
	private int j;
	private int k;
	private int r;
	private int r1;
	private int r2;
	private int current;
	private int repeat;
	private int playerCount;
	private int playerIndex;
	private int deployTurn;
	private int noArmiesCount;
	private int turnInCount;
	private int armies;
	private int attackerArmies;
	private int defenderArmies;
	private int attackerLosses;
	private int defenderLosses;
	private int attackerDice;
	private int defenderDice;
	
	private int[] attackerRolls;
	private int[] defenderRolls;
	private int[] cards;
	
	private String countriesFile = "countries.txt";
	private String continentsFile = "continents.txt";
	private String adjacenciesFile = "adjacencies.txt";
	private String line;
	private String input;
	private String country;
	private String countryAName;
	private String countryBName;
	private String countryASelection;
	
	private String[] countriesArray;
	private String[] continentsArray;
	private String[] adjacenciesArray;
	private String[] ownedCountries;
	private String[] unownedCountries;
	
	private ArrayList<String> list;
	private ArrayList<String> playerNames;
	private ArrayList<String> playerTypes;
	private ArrayList<String> priorityCountryStrings;
	
	private ArrayList<Player> players;
	
	private ArrayList<Country> countries;
	private ArrayList<Country> priorityCountries;
	private ArrayList<Country> priorityTargets;
	
	private Random rng;
	
	private StringBuilder stringBuilder;
	private BufferedReader reader;
	private JFileChooser fileChooser;
	
	private Board board;
	private Deck deck;
	private Dice dice;
	private Player currentPlayer;
	private Country countryA;
	private Country countryB;
	
	/**
	 * This is the constructor for the RiskModel object.
	 **/
	protected RiskModel() {
	
	}
	
	protected void saveGame() {
	
	}
	
	/**
	 * This method handles exiting the game.
	 **/
	protected void quitGame() {
		System.exit(0);
	}
	
	/**
	 * Sets the number of players.
	 * @param playerCount is an integer input by the player in the PlayerCountDialog GUI.
	 **/
	protected void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	
	/**
	 * Gets the number of players.
	 * @return the number of players in the Risk game.
	 **/
	protected int getPlayerCount() {
		return playerCount;
	}
	
	/**
	 * Sets up the Risk game.
	 * @param playerNames is an ArrayList of the player names.
	 * @param playerTypes is an ArrayList of the player teams.
	 * @return true if the game was successfully initialized
	 **/
	public boolean initializeGame(ArrayList<String> playerNames, ArrayList<String> playerTypes) throws FileNotFoundException {
		
		isLoaded = false;
		this.playerNames = playerNames;
		this.playerTypes = playerTypes;
		board = new Board();
		
		try {
		// Reads countries file
			reader = new BufferedReader(new FileReader(countriesFile));			
			stringBuilder = new StringBuilder();
			
			while((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			input = stringBuilder.toString();
			System.out.println("Input from " + countriesFile + ": " + input);
			// Splits the text in the file into an array
			countriesArray = input.split("\t");
			System.out.println("Loading board...");
			
			// Reads adjacencies file
			reader = new BufferedReader(new FileReader(adjacenciesFile));			
			stringBuilder = new StringBuilder();
			
			while((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			input = stringBuilder.toString();
			System.out.println("Input from " + adjacenciesFile + ": " + input);
			
			// Creates an array of each line from the file
			adjacenciesArray = input.split("\t");
			
			// Reads continents file
			reader = new BufferedReader(new FileReader(continentsFile));
			stringBuilder = new StringBuilder();
			
			while((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			input = stringBuilder.toString();
			System.out.println("Input from " + continentsFile + ": " + input);
			
			continentsArray = input.split("\t");
			
			// Creates game board object
			isLoaded = board.loadBoard(countriesArray, adjacenciesArray, continentsArray);
			
			// Creates deck
			System.out.println("Populating deck...");
			deck = new Deck(board.getCountries());
			
			// Creates ArrayList of players
			System.out.println("Preparing players...");
			players = new ArrayList<Player>();
			
			// Players are created here
			for (i = 0; i < playerNames.size(); i++) {
				if (playerTypes.get(i).equals("Human")) {
					isAI = false;
				} else if (playerTypes.get(i).equals("AI")) {
					isAI = true;
				} else {
					System.out.println("Error: playerType " + playerTypes.get(i) + " not found!");
				}
				players.add(new Player(playerNames.get(i), 50 - (playerNames.size() * 5), i, isAI));
			}
			
			System.out.println("Starting deploy phase...");
			
			current = -1;
			deployTurn = -1;
			deployPhase = true;
			deployed = true;
			
			canTurnInCards = false;
			canReinforce = true;
			canAttack = false;
			canFortify = false;
			
		} catch (FileNotFoundException error) {
			System.out.println(error.getMessage());
		} catch (IOException error) {
			System.out.println(error.getMessage());
		}
		return isLoaded;
	}
	
	/**
	 * Starts the game and prints out welcome messages.
	 **/
	protected void startGame() {
	
		System.out.println("Welcome to RISK, the classic WORLD DOMINATION game!\nEach player rolls a DICE in order to determine the order of turns...");
		
		Collections.shuffle(players);
		
		System.out.println("Here is the order of turns:");
	
		for (i = 0; i < players.size(); i++) {
		
			System.out.println((i + 1) + ": " + players.get(i).getName());
		}
		System.out.println("How to begin: Claim territories by selecting them from the list and clicking the 'Place Reinforcements' button.");
		nextPlayer();
	}
	
	/**
	 * Handles turning in Risk cards.
	 * @param cardsToRemove is an integer array of the indexes of cards to be removed.
	 **/
	protected void turnInCards(int[] cardsToRemove) {
	
		if (canTurnInCards == true || isAI == true) {
		
			if (cardsToRemove.length == 3) {
			
				if (currentPlayer.getHand().get(cardsToRemove[0]).getCountry().getOccupant().equals(currentPlayer) || currentPlayer.getHand().get(cardsToRemove[1]).getCountry().getOccupant().equals(currentPlayer) || currentPlayer.getHand().get(cardsToRemove[2]).getCountry().getOccupant().equals(currentPlayer)) {
				// Checks if player owns a country on the cards to remove
					currentPlayer.incrementArmies(2);
					if(isAI == false) {
						setChanged();
						notifyObservers("cards");
					}
				}
				turnInCount = currentPlayer.getTurnInCount();
				
				if (turnInCount <= 5) {
				// Increments armies according to how many turn-ins have occurred
					currentPlayer.incrementArmies(2 + (2 * turnInCount));
					if(isAI == false) {
						setChanged();
						notifyObservers("cards");
					}
				}
				else if (turnInCount >= 6) {
				// Increments armies by 15 for every turn-in after the 5th
					currentPlayer.incrementArmies(15);
					if(isAI == false) {
						setChanged();
						notifyObservers("cards");
					}
				}
				currentPlayer.removeCards(cardsToRemove);
				if(isAI == false) {
					setChanged();
					notifyObservers("cards");
				}
			
			} else {
				System.out.println("You must trade in three cards of the same type or one of each three types.");
			}			
		} else {
			System.out.println("You can't turn in cards right now.");
		}
	}
	
	/**
	 * Handles placing reinforcements.
	 * @param country is a String of the country in which the reinforcements will be placed
	 **/
	protected void reinforce(String countryAName) {
	
		countryA = board.getCountryByName(countryAName);
		
		if (canReinforce == true || isAI == true) {
		
			if (countryA.hasPlayer() == false || currentPlayer.equals(countryA.getOccupant())) {
			// If countryA is occupied by player or unoccupied
				if (deployTurn >= 42) {
				// If all countries have been claimed
					if (isAI == true) {
						rng = new Random();
						armies = rng.nextInt(currentPlayer.getArmies());
						if (currentPlayer.getArmies() > 0 && armies == 0) {
							armies = 1;
						}
					} else {
						isInt = false;
						try {
						// Player inputs how many armies to reinforce selected country
							armies = Integer.parseInt(JOptionPane.showInputDialog("Commander, how many armies do you wish to send to reinforce " + countryAName + "?"));
							isInt = true;
						} catch (NumberFormatException e) {
							System.out.println("Commander, please take this seriously. We are at war.");
						}
					}
					if (isInt == true || isAI == true) {
						
						if (currentPlayer.getArmies() >= armies) {
						// If player has enough armies
							// Subtracts player armies and adds armies to country
							currentPlayer.decrementArmies(armies);
							countryA.incrementArmies(armies);
							// Reinforce dialog
							System.out.println(currentPlayer.getName() + " has chosen to reinforce " + countryAName + " with " + armies + " armies.");
							deployed = true;
							if (isAI == false) {
								setChanged();
								notifyObservers("countryA");
							}
							if (currentPlayer.getArmies() == 0) {
								canAttack = true;
								canFortify = true;
							}
						} else {
							System.out.println("You do not have enough armies to reinforce " + countryAName + " with " + armies + " armies.\nReinforcements available: " + currentPlayer.getArmies());
						}
					}
				} else if (countryA.hasPlayer() == false) {
				// If there are remaining countries to claim, makes sure country is unoccupied
					countryA.setOccupant(currentPlayer);
					currentPlayer.addCountry(countryA);
					// Places one army at the unoccupied territory
					countryA.incrementArmies(1);
					currentPlayer.decrementArmies(1);
					deployed = true;
					if (isAI == false) {
						nextPlayer();
					}
				} else {
					System.out.println("Error: Can't determine reinforce method");
				}
			} else {
				System.out.println("You do not occupy " + countryAName + ".");
			}
		} else {
			System.out.println("Commander, we are unable to send reinforcements right now.");
		}
	}
	
	/**
	 * Handles the attack function.
	 * Attacking allows the player to engage in battles, with outcomes decided by RNG, with
	 * opposing players in order to lower the number of armies in a territory to 0 in order
	 * to occupy it.
	 * @param countryA is a String of the point A country.
	 * @param countryB is a String of the point B country.
	 **/
	protected void attack(String countryAName, String countryBName) {
	
		countryA = board.getCountryByName(countryAName);
		countryB = board.getCountryByName(countryBName);
		
		if (canAttack == true || isAI == true) {
		
			if (!currentPlayer.equals(countryB.getOccupant())) {
			// Check if countryB is occupied by an opponent
				if (board.checkAdjacency(countryA.getName(), countryB.getName()) == true) {
				// Check if countryA is adjacent to countryB
				
					dice = new Dice();
					
					// Set default values
					attackerLosses = 0;
					defenderLosses = 0;
					attackerDice = 1;
					defenderDice = 1;
					isInt = false;
					
					if (isAI == true) {
						rng = new Random();
						if (countryA.getArmies() <= 3) {
							attackerDice = 1;
						} else {
							attackerDice = rng.nextInt(2) + 1;
						}
					} else {
					// If current player is Human
						try {
						// Attacker chooses how many dice to roll
							
							attackerDice = Integer.parseInt(JOptionPane.showInputDialog(countryA.getOccupant().getName() + ", you are attacking " + countryAName + " from " + countryBName + "! How many dice will you roll?"));
							
							if (attackerDice < 1 || attackerDice > 3 || attackerDice >= countryA.getArmies()) {
								throw new IllegalArgumentException();
							}
							isInt = true;
							
						} catch (NumberFormatException e) {
							// Error: attacker inputs non-integer
							System.out.println("Commander, please take this seriously. We are at war.");
							
						} catch (IllegalArgumentException e) {
							// Error: attacker inputs invalid number of dice
							System.out.println("Roll 1,2 or 3 dice. You must have at least one more army in your country than the number of dice you roll.");
						}
					}
					if (isInt == true || currentPlayer.getAI() == true) {
						attackerRolls = dice.roll(attackerDice);
						isInt = false;							
						if (countryB.getOccupant().getAI() == true) {
						// If the current player is AI
							rng = new Random();
							if (countryB.getArmies() <= 1) {
								defenderDice = 1;
							} else {
								defenderDice = rng.nextInt(1) + 1;
							}
						} else {
						// If current player is Human
							while(isInt == false) {
								try {
								// Defender chooses how many dice to roll after attacker
									defenderDice = Integer.parseInt(JOptionPane.showInputDialog(countryB.getOccupant().getName() + ", you are defending " + countryBName + " from " + countryA.getOccupant().getName() + "! How many dice will you roll?"));
									
									if (defenderDice < 1 || defenderDice > 2 || defenderDice > countryA.getArmies()) {
										throw new IllegalArgumentException();
									}
									isInt = true;
									
								} catch (NumberFormatException e) { 
									// Error: defender inputs non-integer
									System.out.println("Commander, please take this seriously. We are at war.");
									
								} catch (IllegalArgumentException e) {
									// Error: defender inputs invalid number of dice
									System.out.println("Roll either 1 or 2 dice. To roll 2 dice, you must have at least 2 armies on your country.");
								}
							}
						}
						if (isInt == true || countryB.getOccupant().getAI() == true) {
							defenderRolls = dice.roll(defenderDice);
							// Rolls arrays have been ordered in descending order. Index 0 = highest pair
							if (attackerRolls[0] > defenderRolls[0]) {
								defenderLosses++;
							}
							else if (attackerRolls[0] < defenderRolls[0]) {
								attackerLosses++;
							}
							// Index 1 = second highest pair
							if (attackerDice > 1 && defenderDice > 1) {
							
								if (attackerRolls[1] > defenderRolls[1]) {
									defenderLosses++;
									
								} else if (attackerRolls[1] < defenderRolls[1]) {
									attackerLosses++;
								}
							}
							// Calculate losses
							System.out.println("<COMBAT REPORT>");
							countryA.decrementArmies(attackerLosses);
							countryB.decrementArmies(defenderLosses);
							
							// If defending country loses all armies
							if (countryB.getArmies() < 1) {
							
								System.out.println("WORLD NEWS: " + countryA.getOccupant().getName() + " has defeated all of " + countryB.getOccupant().getName() + "'s armies in " + countryBName + " and has occupied the country!");
								
								// Remove country from defender's list of occupied territories and adds to attacker's list
								countryB.getOccupant().removeCountry(countryBName);
								countryA.getOccupant().addCountry(countryB);

								// Check if defender is eliminated from game
								if (countryB.getOccupant().getOwnedCountries().size() == 0)	{
									
									System.out.println("WORLD NEWS: " + countryB.getOccupant().getName() + " has surrendered to " + currentPlayer.getName() + " after his last military defeat at " + countryBName + ". " + currentPlayer.getName() + " has issued an execution, with " + countryB.getOccupant().getName() + " charged as a war criminal.");
									
									players.remove(countryB.getOccupant().getIndex());
								}
								// Set country occupant to attacker
								countryB.setOccupant(countryA.getOccupant());
								countryA.decrementArmies(1);
								countryB.incrementArmies(1);
								
								if(isAI == false) {
									setChanged();
									notifyObservers("countryA");
								}
							}
							canReinforce = false;
						}
					}
				} else {
					System.out.println("Commander, " + countryAName + " is not adjacent to " + countryBName + ".");
				}
			} else {
				System.out.println("Commander, you cannot attack your own territories.");
			}
		} else {
			System.out.println("Commander, our forces are not prepared to launch an attack right now.");
		}
	}
	
	/**
	 * Handles the fortify function.
	 * Fortifying allows the player to move armies from one country to another occupied 
	 * country once per turn.
	 * @param countryA is a String of the point A country.
	 * @param countryB is a String of the point B country.
	 **/
	protected void fortify(String countryAName, String countryBName) {	
	
		countryA = board.getCountryByName(countryAName);
		countryB = board.getCountryByName(countryBName);
		
		if (canFortify == true || currentPlayer.getAI() == true) {
		
			if (currentPlayer.equals(countryA.getOccupant()) && currentPlayer.equals(countryB.getOccupant())) {
			// Check player owns countryA and countryB
				if (board.checkAdjacency(countryAName, countryBName) == true) {
				// Check if countryA and countryB are adjacent
					isInt = false;
					
					if (isAI == true ) {
					// If current player is AI
						rng = new Random();
						System.out.println(countryA.getArmies());
						armies = rng.nextInt(countryA.getArmies());
						if (countryA.getArmies() > 0 && armies == 0) {
							armies = 1;
						}
					} else {
					// If current player is Human
						try {
						// Player inputs how many armies to move from country A to country B
							armies = Integer.parseInt(JOptionPane.showInputDialog("Commander, how many armies from " + countryAName + " do you wish to send to fortify " + countryBName + "?"));
							isInt = true;
							
						} catch (NumberFormatException e) {
							System.out.println("Commander, please take this seriously. We are at war.");
						}
					}
					// Decrements armies in country A and increments armies in country B
					if (isInt == true || currentPlayer.getAI() == true) {
						
						if (countryA.getArmies() >= armies) {
							System.out.println(currentPlayer.getName() + " has chosen to fortify " + countryBName + " with " + armies + " armies from " + countryAName + ".");
							
							countryA.decrementArmies(armies);
							countryB.incrementArmies(armies);
							
							if (isAI == false) {
								setChanged();
								notifyObservers("countryA");
							}
							nextPlayer();
							
						} else {
							System.out.println("Commander, you do not have enough armies in " + countryAName + " to fortify " + countryBName + " with " + armies + " armies.\nNumber of armies in " + countryAName + ": " + countryA.getArmies());
						}
					}
				} else {
					System.out.println("Commander, " + countryAName + " is not adjacent to " + countryBName + ".");
				}
			} else {
				System.out.println("Commander, you do not occupy both " + countryAName + " and " + countryBName + ".");
			}
		} else {
			System.out.println("Commander, we can't relocate troops right now.");
		}
	}
	
	/**
	 * Handles turn transitions in both the deploy phase and game phase.
	 **/
	protected void nextPlayer() {
	
		if (players.size() > 1) {
		// If at least one player remains
			if (deployed == true) {
			// Prevents players from skipping turns during the deploy phase
				// Prevents actions between turn transitions
				canTurnInCards = false;
				canReinforce = false;
				canAttack = false;
				canFortify = false;
				playerIndex++;
				
				if (playerIndex >= players.size()) {
				// Loops player index back to 0 when it exceeds the number of players
					playerIndex = 0;
				}
				currentPlayer = players.get(playerIndex);
				isAI = currentPlayer.getAI();
				noArmiesCount = 0;
				
				for(i = 0; i < players.size(); i++) {
				
					if (players.get(i).getArmies() == 0) {
					// Used to determine when to end the deploy phase
						noArmiesCount++;
					}
					if (deployPhase == true && noArmiesCount == players.size()) {
						deployPhase = false;
						deployed = true;
						System.out.println("\n=== The deploy phase has ended! ===\nWhat to do:\n1. Get new armies by turning in matching cards\n2. Attack and conquer neighbor territories.\n3. End your turn by fortifying a country with armies from another occupied country.\nGood luck, commander!");
					}
				}
				if (deployPhase == false) {
				// If game phase is active
					// Draw card
					System.out.println("\n===" + currentPlayer.getName().toUpperCase() +  "===");
					currentPlayer.addRiskCard(deck.draw());	
					
					if (currentPlayer.getOwnedCountries().size() < 12) {
					// Increment armies based on the number of territories occupied
						currentPlayer.incrementArmies(3);
						
					} else {
						currentPlayer.incrementArmies(currentPlayer.getOwnedCountries().size() / 3);
					}
					for (i = 0; i < board.getContinents().size(); i++) {
					// Check continent ownership for bonus armies
						if (currentPlayer.getOwnedCountries().containsAll(board.getContinents().get(i).getMemberCountries())) {
						// If the current player's list of owned territories contains all the territories within a continent
							currentPlayer.incrementArmies(board.getContinents().get(i).getBonusArmies());
							System.out.println(currentPlayer.getName() + " has received " + board.getContinents().get(i).getBonusArmies() + " bonus reinforcements from controlling " + board.getContinents().get(i).getName() + "!");
						}
					}
					System.out.println(currentPlayer.getName() + "'s turn is ready!\nReinforcements available: " + currentPlayer.getArmies());
					deployed = true;
					
					if (isAI == true) {
					// Current player is AI
						System.out.println("***turnAI-Game");
						turnAI();
						nextPlayer();
						
					} else {
					// Current player is human
						while(currentPlayer.mustTurnInCards()) {
						// While player has 5 or more cards
							System.out.println("Commander, your hand is full. Trade in cards for reinforcements to continue.");
						}
						canTurnInCards = true;
						canReinforce = true;
						
						setChanged();
						notifyObservers("cards");
						setChanged();
						notifyObservers("countryA");
					}
				} else if (deployPhase == true) {
				// If deploy phase is active
					deployTurn++;
					
					if (currentPlayer.getArmies() == 0) {
						nextPlayer();
					} else {
						deployed = false;
						System.out.println("\n===" + currentPlayer.getName().toUpperCase() +  "===\n" + currentPlayer.getName() + "'s turn is ready! (Deploy Phase)\nReinforcements available: " + currentPlayer.getArmies());
						setChanged();
						notifyObservers("countryA");
						
						if (isAI == true) {
						// Current player is AI
							System.out.println("**turnAI-Deploy");
							turnAI();
							nextPlayer();
						} else {
							canReinforce = true;
						}
					}
				}
			} else {
				System.out.println("Commander, you must place your reinforcements during the deploy phase.");
			}
		} else {
		
		}
	}
	
	/**
	 * Handles the AI's use of the game functions.
	 **/
	protected void turnAI() {
	
		rng = new Random();
		
		if (deployPhase == false) {
		// If game phase is active
			// AI turnInCards
			//System.out.println("**AI turnInCards - start");
			cards = new int[3];
			for (i = 0; i < currentPlayer.getHand().size(); i++) {
				
				for (j = 0; j < currentPlayer.getHand().size(); j++) {
					
					for (k = 0; k < currentPlayer.getHand().size(); k++) {
						
						if (currentPlayer.getHandObject().canTurnInCards(i, j, k) == true) {
							cards[0] = i;
							cards[1] = j;
							cards[2] = k;
							turnInCards(cards);
							System.out.println("**AI attempted to turn in cards");
						}
					}
				}
			}
			//System.out.println("**AI turnInCards - end");
		}
		
		// AI reinforce
		priorityCountries = new ArrayList<Country>();
		
		if (deployTurn < 42) {
		// If unoccupied countries remain
			for (i = 0; i < board.getUnoccupied().size(); i++) {
				
				for (j = 0; j < board.getUnoccupied().get(i).getAdjacencies().size(); j++) {
					
					if (board.getUnoccupied().get(i).getAdjacencies().get(j).hasPlayer() == true) {
						
						if (board.getUnoccupied().get(i).getAdjacencies().get(j).getOccupant().equals(currentPlayer)) {
							add = true;
						}
					}
				}
				if (add == true) {
					priorityCountries.add(board.getUnoccupied().get(i));
				}
			}
			if (priorityCountries.size() > 0) {
				r = rng.nextInt(priorityCountries.size());
				reinforce(priorityCountries.get(r).getName());
			} else {
				r = rng.nextInt(board.getUnoccupied().size());
				reinforce(board.getUnoccupied().get(r).getName());
			}
		} else {
		// If all countries are occupied
			//System.out.println("**AI reinforce - start");
			for (i = 0; i < currentPlayer.getOwnedCountries().size(); i++) {
				add = false;
				
				for (j = 0; j < currentPlayer.getOwnedCountries().get(i).getAdjacencies().size(); j++) {
					
					if (!currentPlayer.getOwnedCountries().get(i).getAdjacencies().get(j).getOccupant().equals(currentPlayer)) {
						add = true;
					}
				}
				if (add == true) {
					priorityCountries.add(currentPlayer.getOwnedCountries().get(i));
				}
			}
			if (priorityCountries.size() > 0) {
			
				do {
				// 70% chance to repeat action
					r = rng.nextInt(priorityCountries.size());
					reinforce(priorityCountries.get(r).getName());
					repeat = rng.nextInt(9);
				} while (repeat >= 3 && currentPlayer.getArmies() > 0 && currentPlayer.getArmies() > 0);
			}
			//System.out.println("**AI reinforce - end");
			
			if(deployPhase == false) {
			// If game phase is active
			
				// AI attack
				//System.out.println("**AI attack - start");
				
					
				do {
				// 50% chance to repeat action
					priorityCountries = new ArrayList<Country>();
					
					for (i = 0; i < currentPlayer.getOwnedCountries().size(); i++) {
						add = false;
						
						for (j = 0; j < currentPlayer.getOwnedCountries().get(i).getAdjacencies().size(); j++) {
							
							if (currentPlayer.getOwnedCountries().get(i).getArmies() > 2 && !currentPlayer.getOwnedCountries().get(i).getAdjacencies().get(j).getOccupant().equals(currentPlayer)) {
							// If priority country has more than 2 armies and has an adjacent, enemy country
								add = true;
							}
						}
						if (add == true) {
							priorityCountries.add(currentPlayer.getOwnedCountries().get(i));
						}
					}
					//System.out.println("**AI attack - Created priorityCountries list");
					
					if (priorityCountries.size() > 0) {
						r1 = rng.nextInt(priorityCountries.size());
						priorityTargets = new ArrayList<Country>();
						
						for (i = 0; i < priorityCountries.get(r1).getAdjacencies().size(); i++) {
						
							if (!priorityCountries.get(r1).getAdjacencies().get(i).getOccupant().equals(currentPlayer.getName())) {
								priorityTargets.add(priorityCountries.get(r1).getAdjacencies().get(i));
							}
						}
						//System.out.println("**AI attack - Created priorityTargets list");
						
						if (priorityTargets.size() > 0) {
							r2 = rng.nextInt(priorityTargets.size());
							//System.out.println("**AI attack - Attacking...");
							attack(priorityCountries.get(r1).getName(), priorityTargets.get(r2).getName());
							//System.out.println("**AI attack - Successful attack");
							repeat = rng.nextInt(9);
						}
					}
				} while (repeat >= 5 && currentPlayer.getArmies() > 0);
				
				//System.out.println("**AI attack - end");
				
				// AI fortify
				//System.out.println("**AI fortify - start");
				priorityTargets = new ArrayList<Country>();
				
				for (i = 0; i < currentPlayer.getOwnedCountries().size(); i++) {
					add1 = false;
					add2 = false;
					
					for (j = 0; j < currentPlayer.getOwnedCountries().get(i).getAdjacencies().size(); j++) {
					// Checks if country has both adjacent friendly and enemy countries
						if (!currentPlayer.getOwnedCountries().get(i).getAdjacencies().get(j).getOccupant().equals(currentPlayer)) {
						// If priority country has an adjacent, enemy country
							add1 = true;
							
						} else if (currentPlayer.getOwnedCountries().get(i).getAdjacencies().get(j).getOccupant().equals(currentPlayer)) {
							add2 = true;
						}
					}
					if (add1 == true && add2 == true) {
						priorityTargets.add(currentPlayer.getOwnedCountries().get(i));
					}
				}
				//System.out.println("**AI fortify - Created priorityTargets list");
				
				if (priorityTargets.size() > 0) {
					priorityCountries = new ArrayList<Country>();
					r1 = rng.nextInt(priorityTargets.size());
					
					for (i = 0; i < priorityTargets.get(r1).getAdjacencies().size(); i++) {

						if (priorityTargets.get(r1).getAdjacencies().get(i).getOccupant().equals(currentPlayer) && priorityTargets.get(r1).getAdjacencies().get(i).getArmies() > 1 ) {
							priorityCountries.add(priorityTargets.get(r1).getAdjacencies().get(i));
						}
					}
					if (priorityCountries.size() > 0) {
						//System.out.println("**AI fortify - Created priorityCountries list");
						r2 = rng.nextInt(priorityCountries.size());
						//System.out.println("**AI fortify - Fortifying...");
						fortify(priorityCountries.get(r2).getName(), priorityTargets.get(r1).getName());
						//System.out.println("**AI fortify - Successful fortify");
					}
				}
				//System.out.println("**AI fortify - end");
			}
		}
	}
	
	/**
	 * Creates and returns the information for the cardsList in the BoardView.
	 * @return a list of Strings to be displayed in the cardsList.
	 **/
	protected ArrayList<String> getCardsList() {
	
		list = new ArrayList<String>();
		
		for (i = 0; i < currentPlayer.getHand().size(); i++) {	
		
			list.add(currentPlayer.getHand().get(i).getName());
		}
		return list;
	}
	
	/**
	 * Creates and returns the information for the countryAList in the BoardView.
	 * @return a list of Strings to be displayed in the countryAList.
	 **/
	protected ArrayList<String> getCountryAList() {
	
		list = new ArrayList<String>();
		
		if (deployTurn >= 42) {
		
			for (i = 0; i < currentPlayer.getOwnedCountries().size(); i++) {
				list.add(currentPlayer.getOwnedCountries().get(i).getArmies() + "-" + currentPlayer.getOwnedCountries().get(i).getName());
			}
		} else {
			for (i = 0; i < board.getUnoccupied().size(); i++) {
				list.add(board.getUnoccupied().get(i).getName());
			}
		}
		return list;
	}
	
	/** 
	 * Creates and returns the information for the countryBList in the BoardView.
	 * @return a list of Strings to be displayed in the countryBList.
	 **/
	protected ArrayList<String> getCountryBList() {
	
		list = new ArrayList<String>();
		
		for (i = 0; i < board.getCountries().size(); i++) {
		
			if (board.checkAdjacency(countryASelection, board.getCountries().get(i).getName())) {
				list.add(board.getCountries().get(i).getArmies() + "-" + board.getCountries().get(i).getName());
			}
		}
		return list;
	}
	
	/**
	 * Receives information on which country is selected in countryAList.
	 * @param a String of the selected country
	 **/
	protected void setCountryASelection(String country) {
		countryASelection = country;
		setChanged();
		notifyObservers("countryB");
	}
	
	protected Board getBoard() {
		return board;
	}
}