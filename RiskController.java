/* RiskController.java
*
*  Description:	This class maps the user's actions in the the
*				view to the data and methods in the model.
*
*  Author: Ted Mader, 3/10/2014
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiskController implements ActionListener
{
	private RiskModel model;
	private RiskView view;
	
	private PlayerCountDialog playerCountDialog;
	
	//Constructor
	public RiskController( RiskModel model, RiskView view )
	{
		this.model = model;
		this.view = view;
		
		//Add this class' actionListener to riskView's buttons
		
		view.riskViewActionListeners( this );
	}
	
	//RiskView's controller
	public void actionPerformed( ActionEvent action )
	{
		String viewAction = action.getActionCommand();
		
		if( viewAction.equals( "newGameBtn" ) )
		{
			//Opens the playerCountDialog
			playerCountDialog = new PlayerCountDialog( view, true );
			playerCountDialog.playerCountActionListeners( new PlayerCountController( model, playerCountDialog ) );
			playerCountDialog.setVisible( true );
		}				
		else if( viewAction.equals( "loadGameBtn" ) )
		{
			model.loadGame();
		}				
		else if( viewAction.equals( "quitBtn" ) )
		{
			view.dispose();
		}
		else
		{
			System.out.println( "Error" );
		}
	}
}

class PlayerCountController implements ActionListener
{	
	private RiskModel model;
	private PlayerCountDialog view;
	
	private PlayerSettingsDialog playerSettingsDialog;
	
	//Constructor
	public PlayerCountController( RiskModel model, PlayerCountDialog view )
	{
		this.model = model;
		this.view = view;
	}
	
	public void actionPerformed( ActionEvent ae2 )
	{
		String actionEvent2 = ae2.getActionCommand();
		
		if( actionEvent2.equals( "threePlayersBtn" ) )
		{
			model.setPlayerCount( 3 );
			
			playerSettingsDialog = new PlayerSettingsDialog( view, true, model.getPlayerCount() );
			playerSettingsDialog.playerSettingsActionListeners( new PlayerSettingsController( model, playerSettingsDialog ) );
			playerSettingsDialog.setVisible( true );
		}
		
		else if( actionEvent2.equals( "fourPlayersBtn" ) )
		{
			model.setPlayerCount( 4 );
			
			playerSettingsDialog = new PlayerSettingsDialog( view, true, model.getPlayerCount() );
			playerSettingsDialog.playerSettingsActionListeners( new PlayerSettingsController( model, playerSettingsDialog ) );
			playerSettingsDialog.setVisible( true );
		}
		
		else if( actionEvent2.equals( "fivePlayersBtn" ) )
		{
			model.setPlayerCount( 5 );
			
			playerSettingsDialog = new PlayerSettingsDialog( view, true, model.getPlayerCount() );
			playerSettingsDialog.playerSettingsActionListeners( new PlayerSettingsController( model, playerSettingsDialog ) );
			playerSettingsDialog.setVisible( true );
		}
		
		else if( actionEvent2.equals( "sixPlayersBtn" ) )
		{
			model.setPlayerCount( 6 );
			
			playerSettingsDialog = new PlayerSettingsDialog( view, true, model.getPlayerCount() );
			playerSettingsDialog.playerSettingsActionListeners( new PlayerSettingsController( model, playerSettingsDialog ) );
			playerSettingsDialog.setVisible( true );
		}
		
		else if( actionEvent2.equals( "backBtn" ) )
		{
			view.dispose();
		}
		
		else
		{
			System.out.println( "Error" );
		}
	}
}
	
class PlayerSettingsController implements ActionListener
{
	private RiskModel model;
	private PlayerSettingsDialog view;

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
	
	public PlayerSettingsController( RiskModel model, PlayerSettingsDialog view )
	{
		this.model = model;
		this.view = view;
	}
	
	public void actionPerformed( ActionEvent ae3 )
	{
		String actionEvent3 = ae3.getActionCommand();
		
		if( actionEvent3.equals( "startBtn" ) )
		{
			player1Name = view.getPlayer1TextField();
			player2Name = view.getPlayer2TextField();
			player3Name = view.getPlayer3TextField();
		
			player1Team = view.getPlayer1ComboBox();
			player2Team = view.getPlayer2ComboBox();
			player3Team = view.getPlayer3ComboBox();
			
			//Gets player names based on playerCount
			if( model.getPlayerCount() > 3 )
			{
				player4Name = view.getPlayer4TextField();
				player4Team = view.getPlayer4ComboBox();
			}
			if( model.getPlayerCount() > 4 )
			{
				player5Name = view.getPlayer5TextField();
				player5Team = view.getPlayer5ComboBox();
			}
			if( model.getPlayerCount() > 5 )
			{
				player6Name = view.getPlayer6TextField();
				player6Team = view.getPlayer6ComboBox();
			}
			
			//Sets player names
			model.setPlayerNames( player1Name, player2Name, player3Name, player4Name, player5Name, player6Name );
			
			//Sets player teams
			model.setPlayerTeams( player1Team, player2Team, player3Team, player4Team, player5Team, player6Team );

		}
		
		if( actionEvent3.equals( "backBtn" ) )
		{
			view.dispose();
		}
	}
}

