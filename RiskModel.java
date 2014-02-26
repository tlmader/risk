/*	RiskModel.java
*
*  Description:	This class is the model for the Example program.
*				It contains the data and the methods and
*				functions that are required to manipulate the
*				data.
*
*  Author: Ted Mader, 2/25/2014
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
	
	protected RiskModel()
	{
	
	}
	
	protected void newGame()
	{
		
	}
	
	protected void loadGame()
	{
	
	}
	
	protected void quit()
	{
		System.exit(0);
	}
	
	protected void setPlayerCount(int playerCount)
	{
		this.playerCount = playerCount;
	}
	
	protected int getPlayerCount()
	{
		return this.playerCount;
	}
	
	protected void setPlayerNames( String player1Name, String player2Name, String player3Name, String player4Name, String player5Name, String player6Name )
	{
		this.player1Name = player1Name;
		this.player2Name = player2Name;
		this.player3Name = player3Name;
		this.player4Name = player4Name;
		this.player5Name = player5Name;
		this.player6Name = player6Name;
	}
	
	protected String getPlayer1Name()
	{
		return this.player1Name;
	}
	
	protected String getPlayer2Name()
	{
		return this.player2Name;
	}
	
	protected String getPlayer3Name()
	{
		return this.player3Name;
	}
	
	protected String getPlayer4Name()
	{
		return this.player4Name;
	}
	
	protected String getPlayer5Name()
	{
		return this.player5Name;
	}
	
	protected String getPlayer6Name()
	{
		return this.player6Name;
	}
}