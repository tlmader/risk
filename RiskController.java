/*	RiskController.java
*
*  Description:	This class maps the user's actions in the the
*				view to the data and methods in the model.
*
*  Author: Ted Mader, 2/25/2014
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiskController implements ActionListener
{
	private RiskModel model;
	private RiskView view;
	private PlayerCountDialog playerCountDialog;
	private PlayerNamesDialog playerNamesDialog;
	
	public static RiskModel model;
	
	public RiskController( RiskModel newModel, RiskView riskView )
	{
		model = newModel;
		view = riskView;
		
		//Add this class' actionListener to riskView's buttons
		view.riskViewActionListener( this );
	}
	
	public void actionPerformed( ActionEvent ae1 )
	{
		String viewAction = ae1.getActionCommand();
		
		if( viewAction.equals( "newGameBtn" ) )
		{
			playerCountDialog = new playerCountDialog(view, true);
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

	class PlayerCountController implements ActionListener
	{
		PlayerCountDialog view;

		public PlayerCountController( PlayerCountDialog view2 )
		{
			this.view = view2;
			
			view.playerCountActionListener( this );
		}
		
		public void actionPerformed( ActionEvent ae2 )
		{
			String actionEvent2 = ae2.getActionCommand();
			
			if( actionEvent2.equals( "twoPlayersBtn" ) )
			{
				model.setPlayerCount( 2 );
				System.out.println( "DONE!" );
				playerNamesDialog = new PlayerNamesDialog(view, true, model.getPlayerCount() );
			}
			
			else if( actionEvent2.equals( "threePlayersBtn" ) )
			{
				model.setPlayerCount( 3 );
				System.out.println( "DONE!" );
				playerNamesDialog = new PlayerNamesDialog(view, true, model.getPlayerCount() );
			}
			
			else if( actionEvent2.equals( "fourPlayersBtn" ) )
			{
				model.setPlayerCount( 4 );
				System.out.println( "DONE!" );	
				playerNamesDialog = new PlayerNamesDialog(view, true, model.getPlayerCount() );
			}
			
			else if( actionEvent2.equals( "fivePlayersBtn" ) )
			{
				model.setPlayerCount( 5 );
				System.out.println( "DONE!" );
				playerNamesDialog = new PlayerNamesDialog(view, true, model.getPlayerCount() );
			}
			
			else if( actionEvent2.equals( "sixPlayersBtn" ) )
			{
				model.setPlayerCount( 6 );
				System.out.println( "DONE!" );
				playerNamesDialog = new PlayerNamesDialog(view, true, model.getPlayerCount() );
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
	
	class PlayerNamesController implements ActionListener
	{
		PlayerNamesDialog view;
		
		private String player1Name;
		private String player2Name;
		private String player3Name;
		private String player4Name;
		private String player5Name;
		private String player6Name;
		
		public PlayerNamesController( PlayerNamesDialog view3 )
		{
			this.view = view3;
			
			view.playerNamesActionListener( this );
		}
		
		public void actionPerformed( ActionEvent ae3 )
		{
			String actionEvent3 = ae3.getActionCommand();
			
			if( actionEvent3.equals( "startBtn" ) )
			{
				player1Name = view.getPlayer1TextField();
				player2Name = view.getPlayer2TextField();
				player3Name = view.getPlayer3TextField();
				player4Name = view.getPlayer4TextField();
				player5Name = view.getPlayer5TextField();
				player6Name = view.getPlayer6TextField();
				
				model.setPlayerNames( player1Name, player2Name, player3Name, player4Name, player5Name, player6Name );
			}
		}
	}
}	

