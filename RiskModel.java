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

public class RiskModel extends Observable
{
	private int playerCount;
	
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
	
	//RiskView.java methods
	
	//Constructor
	protected RiskModel()
	{
	
	}
	
	//If load button is pressed
	protected void loadGame()
	{
	
	}
	
	//PlayerCountDialog.java methods
	
	//Sets the number of players
	protected void setPlayerCount( int playerCount )
	{
		this.playerCount = playerCount;
		
		System.out.println( "playerCount = " + playerCount );
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
		
		System.out.println( "player1Name = " + player1Name );
		System.out.println( "player2Name = " + player2Name );
		System.out.println( "player3Name = " + player3Name );
		System.out.println( "player4Name = " + player4Name );
		System.out.println( "player5Name = " + player5Name );
		System.out.println( "player6Name = " + player6Name );
	}
	
	public void setPlayerTeams( String player1Team, String player2Team, String player3Team, String player4Team, String player5Team, String player6Team )
	{
		this.player1Team = player1Team;
		this.player2Team = player2Team;
		this.player3Team = player3Team;
		this.player4Team = player4Team;
		this.player5Team = player5Team;
		this.player6Team = player6Team;
		
		System.out.println( "player1Team = " + player1Team );
		System.out.println( "player2Team = " + player2Team );
		System.out.println( "player3Team = " + player3Team );
		System.out.println( "player4Team = " + player4Team );
		System.out.println( "player5Team = " + player5Team );
		System.out.println( "player6Team = " + player6Team );
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
}