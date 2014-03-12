/* RiskController.java
*
*  Description:	This class maps the user's actions in the the
*				view to the data and methods in the model.
*
*  Author: Ted Mader, 3/10/2014
*/

import java.io.File;
import java.lang.Integer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

public class RiskController implements ActionListener
{
	private RiskModel model;
	private RiskView view;
	private JFileChooser fileChooser;
	private File file;
	
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
	public void actionPerformed( ActionEvent e )
	{
		String actionEvent = e.getActionCommand();
		
		if( actionEvent.equals( "newGameButton" ) )
		{
			//Opens the playerCountDialog
			playerCountDialog = new PlayerCountDialog( view, true );
			playerCountDialog.playerCountActionListeners( new PlayerCountController( model, playerCountDialog ) );
			playerCountDialog.setVisible( true );
		}				
		else if( actionEvent.equals( "loadGameButton" ) )
		{
			fileChooser = new JFileChooser();
			
			if ( fileChooser.showOpenDialog( view ) == JFileChooser.APPROVE_OPTION )
			{
				file = fileChooser.getSelectedFile();
			}
		}				
		else if( actionEvent.equals( "quitButton" ) )
		{
			model.quitGame();
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
	
	public void actionPerformed( ActionEvent e )
	{
		String actionEvent = e.getActionCommand();
		
		if( actionEvent.equals( "threePlayersButton" ) )
		{
			model.setPlayerCount( 3 );
			
			playerSettingsDialog = new PlayerSettingsDialog( view, true, model.getPlayerCount() );
			playerSettingsDialog.playerSettingsActionListeners( new PlayerSettingsController( model, playerSettingsDialog ) );
			playerSettingsDialog.setVisible( true );
		}
		
		else if( actionEvent.equals( "fourPlayersButton" ) )
		{
			model.setPlayerCount( 4 );
			
			playerSettingsDialog = new PlayerSettingsDialog( view, true, model.getPlayerCount() );
			playerSettingsDialog.playerSettingsActionListeners( new PlayerSettingsController( model, playerSettingsDialog ) );
			playerSettingsDialog.setVisible( true );
		}
		
		else if( actionEvent.equals( "fivePlayersButton" ) )
		{
			model.setPlayerCount( 5 );
			
			playerSettingsDialog = new PlayerSettingsDialog( view, true, model.getPlayerCount() );
			playerSettingsDialog.playerSettingsActionListeners( new PlayerSettingsController( model, playerSettingsDialog ) );
			playerSettingsDialog.setVisible( true );
		}
		
		else if( actionEvent.equals( "sixPlayersButton" ) )
		{
			model.setPlayerCount( 6 );
			
			playerSettingsDialog = new PlayerSettingsDialog( view, true, model.getPlayerCount() );
			playerSettingsDialog.playerSettingsActionListeners( new PlayerSettingsController( model, playerSettingsDialog ) );
			playerSettingsDialog.setVisible( true );
		}
		
		else if( actionEvent.equals( "backButton" ) )
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

	private RiskBoard riskBoard;
	
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
	
	public void actionPerformed( ActionEvent e )
	{
		String actionEvent = e.getActionCommand();
		
		if( actionEvent.equals( "startButton" ) )
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
	
			//Initializes values for a new game
			model.initializeGame();
			
			//Opens the Risk game board
			riskBoard = new RiskBoard( view, true );
			riskBoard.riskBoardActionListeners( new RiskBoardController( model, riskBoard ) );
			riskBoard.setVisible( true );
		}
		
		else if( actionEvent.equals( "backButton" ) )
		{
			view.dispose();
		}
		
		else
		{
			System.out.println( "actionEvent not found: " + actionEvent );
		}
	}
}

class RiskBoardController implements ActionListener
{
	private RiskModel model;
	private RiskBoard view;
	
	private MenuDialog menuDialog;
	
	private int troopCount;
	
	private String isInt;
	
	private String troopCountString;
	
	private String selectedTerritory;
	private String targetTerritory;
	
	private String reinforceString;
	private String attackString;
	private String fortifyString;
	private String turnInString;
	
	public RiskBoardController( RiskModel model, RiskBoard view )
	{
		this.model = model;
		this.view = view;
	}
	
	public void actionPerformed( ActionEvent e )
	{
		String actionEvent = e.getActionCommand();
		
		if( actionEvent.equals( "menuButton" ) )
		{
			menuDialog = new MenuDialog( view, true );
			menuDialog.menuDialogActionListeners( new MenuDialogController( model, menuDialog ) );
			menuDialog.setVisible( true );			
		}
		
		else if( actionEvent.equals( "reinforceButton" ) )
		{
			selectedTerritory = view.getSelectedComboBox();
			
			isInt = "no";
			
			//To Do: Allow escape from try/catch block when clicking "Cancel"
			
			try
			{
				troopCountString = JOptionPane.showInputDialog( "How many troops do you wish to send to reinforce " + selectedTerritory + "?" );
				troopCount = Integer.parseInt( troopCountString );
				
				isInt = "yes";
			}
			
			catch( Exception error )
			{
				JOptionPane.showMessageDialog( null, "Please type a number." );
			}
			
			if( isInt.equals( "yes" ) )
			{
				model.buildReinforceString( troopCount, selectedTerritory );
				
				reinforceString = model.getReinforceString();
				
				JOptionPane.showMessageDialog( null, reinforceString );
			}
		}
		
		else if( actionEvent.equals( "attackButton" ) )
		{
			selectedTerritory = view.getSelectedComboBox();
			targetTerritory = view.getTargetComboBox();
			
			isInt = "no";
			
			//To Do: Allow escape from try/catch block when clicking "Cancel"
			
			try
			{
				troopCountString = JOptionPane.showInputDialog( "How many troops from " + selectedTerritory + " do you wish to send to attack " + targetTerritory + "?" );
				troopCount = Integer.parseInt( troopCountString );
		
				isInt = "yes";
			}
			catch( Exception error )
			{
				JOptionPane.showMessageDialog( null, "Please type a number." );
			}
			
			if( isInt.equals( "yes" ) )
			{
				model.buildAttackString( troopCount, selectedTerritory, targetTerritory );
				
				attackString = model.getAttackString();
				
				JOptionPane.showMessageDialog( null, attackString );
			}
		}
		
		else if( actionEvent.equals( "fortifyButton" ) )
		{
			selectedTerritory = view.getSelectedComboBox();
			targetTerritory = view.getTargetComboBox();
			
			isInt = "no";
			
			//To Do: Allow escape from try/catch block when clicking "Cancel"
			
			try
			{
				troopCountString = JOptionPane.showInputDialog( "How many troops from " + selectedTerritory + " do you wish to send to fortify " + targetTerritory + "?" );
				troopCount = Integer.parseInt( troopCountString );
			
				isInt = "yes";
			}
			catch( Exception error )
			{
				JOptionPane.showMessageDialog( null, "Please type a number." );
			}
			
			if( isInt.equals( "yes" ) )
			{
				model.buildFortifyString( troopCount, selectedTerritory, targetTerritory );
				
				fortifyString = model.getFortifyString();
				
				JOptionPane.showMessageDialog( null, fortifyString );
			}
		}
		
		else if( actionEvent.equals( "turnInButton" ) )
		{
			turnInString = "The player turned in his Risk cards.";
			
			JOptionPane.showMessageDialog( null, turnInString );
		}
		
		else
		{
			System.out.println( "actionEvent not found: " + actionEvent );
		}
	}
}

class MenuDialogController implements ActionListener
{
	private RiskModel model;
	private MenuDialog view;
	
	private JFileChooser fileChooser;
	private File file;
	
	public MenuDialogController( RiskModel model, MenuDialog view )
	{
		this.model = model;
		this.view = view;
	}
	
	public void actionPerformed( ActionEvent e )
	{
		String actionEvent = e.getActionCommand();
	
		if( actionEvent.equals( "returnButton" ) )
		{
			view.dispose();
		}
		
		else if( actionEvent.equals( "saveButton" ) )
		{
			fileChooser = new JFileChooser();
			
			if ( fileChooser.showSaveDialog( view ) == JFileChooser.APPROVE_OPTION )
			{
				file = fileChooser.getSelectedFile();
			}
		}
		
		else if( actionEvent.equals( "quitButton" ) )
		{
			model.quitGame();
		}
		
		else
		{
			System.out.println( "actionEvent not found: " + actionEvent );
		}
	}

}

