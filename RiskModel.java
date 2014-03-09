/* RiskModel.java
*
*  Description:	This class is the model for the Example program.
*				It contains the data and the methods and
*				functions that are required to manipulate the
*				data.
*
*  Author: Ted Mader, 3/10/2014
*/

import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JFileChooser;

public class RiskModel extends Observable
{
	private int playerCount;
	private int currentPlayer;
	private int troopCount;
	
	private String player1Name;
	private String player2Name;
	private String player3Name;
	private String player4Name;
	private String player5Name;
	private String player6Name;
	
	private String player1Team;
	private String player2Team;
	private String player3Team;
	private String player4Team;
	private String player5Team;
	private String player6Team;	
	
	private String currentPlayerName;
	
	private String reinforceString;
	private String attackString;
	private String fortifyString;
	
	private JFileChooser fileChooser;
	
	//RiskView.java methods
	
	//Constructor
	protected RiskModel()
	{
	
	}
	
	protected void quitGame()
	{
		System.exit( 0 );
	}
	
	//PlayerCountDialog.java methods
	
	//Sets the number of players
	protected void setPlayerCount( int playerCount )
	{
		this.playerCount = playerCount;
	}
	
	//Gets the number of players
	protected int getPlayerCount()
	{
		return playerCount;
	}
	
	//Sets all player names
	public void setPlayerNames( String player1Name, String player2Name, String player3Name, String player4Name, String player5Name, String player6Name )
	{
		this.player1Name = player1Name;
		this.player2Name = player2Name;
		this.player3Name = player3Name;
		this.player4Name = player4Name;
		this.player5Name = player5Name;
		this.player6Name = player6Name;
	}
	
	public void setPlayerTeams( String player1Team, String player2Team, String player3Team, String player4Team, String player5Team, String player6Team )
	{
		this.player1Team = player1Team;
		this.player2Team = player2Team;
		this.player3Team = player3Team;
		this.player4Team = player4Team;
		this.player5Team = player5Team;
		this.player6Team = player6Team;
	}
	
	public void initializeGame()
	{
		currentPlayer = 1;
		currentPlayerName = player1Name;
	}
	
	//Get methods for player names
	
	protected String getPlayer1Name()
	{
		return player1Name;
	}
	
	protected String getPlayer2Name()
	{
		return player2Name;
	}
	
	protected String getPlayer3Name()
	{
		return player3Name;
	}
	
	protected String getPlayer4Name()
	{
		return player4Name;
	}
	
	protected String getPlayer5Name()
	{
		return player5Name;
	}
	
	protected String getPlayer6Name()
	{
		return player6Name;
	}
	
	protected void setCurrentPlayer()
	{
		currentPlayer++;
		
		if( currentPlayer == 1 )
		{
			currentPlayerName = player1Name;
		}
		
		if( currentPlayer == 2 )
		{
			currentPlayerName = player2Name;
		}
		
		if( currentPlayer == 3 )
		{
			currentPlayerName = player3Name;
		}
		
		if( currentPlayer == 4 )
		{
			currentPlayerName = player4Name;
		}
		
		if( currentPlayer == 5 )
		{
			currentPlayerName = player5Name;
		}
		
		if( currentPlayer == 6 )
		{
			currentPlayerName = player6Name;
		}
	}
	
	protected int getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	//Message Strings for dialog boxes
	
	protected void buildReinforceString( int troopCount, String selectedTerritory )
	{
		reinforceString = currentPlayerName + " has chosen to reinforce " + selectedTerritory + " with " + troopCount + " troops.";
	}
	
	protected void buildAttackString( int troopCount, String selectedTerritory, String targetTerritory )
	{
		attackString = currentPlayerName + " has chosen to attack from " + selectedTerritory + " to " + targetTerritory + " with " + troopCount + " troops.";
	}
	
	protected void buildFortifyString( int troopCount, String selectedTerritory, String targetTerritory )
	{
		fortifyString = currentPlayerName + " has chosen to fortify " + targetTerritory + " with " + troopCount + " troops from " + selectedTerritory + ".";
	}
	
	protected String getReinforceString()
	{
		return reinforceString;
	}
	
	protected String getAttackString()
	{
		return attackString;
	}
	
	protected String getFortifyString()
	{
		return fortifyString;
	}
}