/* PlayerSettingsDialog.java
*
*  Description: This class creates a dialog for the user to decide the name
*				and color for each player.
*
*  Author: Ted Mader, 3/10/2014
*/

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.lang.StringBuilder;

public class PlayerSettingsDialog extends JDialog
{
	private JPanel playerNamesPanel;
	private JPanel playerTeamsPanel;
	
	private GridLayout mainLayout;
	private GridLayout playerNamesLayout;
	private GridLayout playerTeamsLayout;
	
	private JButton startButton;
	private JButton backButton;
	
	private String startButtonName = "startButton";
	private String backButtonName = "backButton";
	
	private JTextField player1TextField;
	private JTextField player2TextField;
	private JTextField player3TextField;
	private JTextField player4TextField;
	private JTextField player5TextField;
	private JTextField player6TextField;
	
	private JComboBox player1ComboBox;
	private JComboBox player2ComboBox;
	private JComboBox player3ComboBox;
	private JComboBox player4ComboBox;
	private JComboBox player5ComboBox;
	private JComboBox player6ComboBox;
	
	private int playerCount;
	
	private String[] teams = { "North America", "South America", "Europe", "Asia", "Africa", "Australia" };
	
	public PlayerSettingsDialog( PlayerCountDialog owner, boolean modality, int playerCount )
	{		
		super( owner, modality );
		setTitle( "Java-Risk" );
		
		this.playerCount = playerCount;
		
		setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
		setResizable( false );
		
		mainLayout = new GridLayout( 1, 2, 5, 5 );
		setLayout( mainLayout );
				
		add( playerNamesPanel() );
		add( playerTeamsPanel() );
		
		setLocationRelativeTo( null );
		
		pack();
	}
	
	private JPanel playerNamesPanel()
	{
		playerNamesPanel = new JPanel();
		
		playerNamesPanel.setPreferredSize( new Dimension( 200, playerCount * 40 + 40) );
		
		playerNamesLayout = new GridLayout( playerCount + 1, 1, 5, 5 );
		playerNamesPanel.setLayout( playerNamesLayout );
		
		player1TextField = new JTextField();
		player2TextField = new JTextField();
		player3TextField = new JTextField();
		
		playerNamesPanel.add( player1TextField );
		playerNamesPanel.add( player2TextField );
		playerNamesPanel.add( player3TextField );
		
		if( playerCount > 3 )
		{
			player4TextField = new JTextField();
			playerNamesPanel.add( player4TextField );
		}
		if( playerCount > 4 )
		{
			player5TextField = new JTextField();
			playerNamesPanel.add( player5TextField );
		}
		if( playerCount > 5 )
		{
			player6TextField = new JTextField();
			playerNamesPanel.add( player6TextField );
		}
		
		backButton = new JButton ( "Back" );
		
		backButton.setActionCommand( backButtonName );
		
		playerNamesPanel.add( backButton );
		
		return playerNamesPanel;
	}

	private JPanel playerTeamsPanel()
	{

		playerTeamsPanel = new JPanel();
		
		playerTeamsPanel.setPreferredSize( new Dimension( 200, playerCount * 40 + 40) );
		
		playerTeamsLayout = new GridLayout( playerCount + 1, 1, 5, 5 );
		playerTeamsPanel.setLayout( playerTeamsLayout );
		
		player1ComboBox = new JComboBox( teams );
		player2ComboBox = new JComboBox( teams );
		player3ComboBox = new JComboBox( teams );
		
		playerTeamsPanel.add( player1ComboBox );
		playerTeamsPanel.add( player2ComboBox );
		playerTeamsPanel.add( player3ComboBox );
		
		if( playerCount > 3 )
		{
			player4ComboBox = new JComboBox( teams );
			playerTeamsPanel.add( player4ComboBox );
		}
		if( playerCount > 4 )
		{
			player5ComboBox = new JComboBox( teams );
			playerTeamsPanel.add( player5ComboBox );
		}
		if( playerCount > 5 )
		{
			player6ComboBox = new JComboBox( teams );
			playerTeamsPanel.add( player6ComboBox );
		}
		
		startButton = new JButton ( "Start Game" );
		
		startButton.setActionCommand( startButtonName );
		
		playerTeamsPanel.add( startButton );

		return playerTeamsPanel;
	}
	
	protected void playerSettingsActionListeners( ActionListener event )
	{
		startButton.addActionListener( event );
		backButton.addActionListener( event );
	}
	
	//Get methods for text fields
	
	protected String getPlayer1TextField()
	{
		return player1TextField.getText();
	}
	
	protected String getPlayer2TextField()
	{
		return player2TextField.getText();
	}
	
	protected String getPlayer3TextField()
	{
		return player3TextField.getText();
	}
	
	protected String getPlayer4TextField()
	{
		return player4TextField.getText();
	}
	
	protected String getPlayer5TextField()
	{
		return player5TextField.getText();
	}
	
	protected String getPlayer6TextField()
	{
		return player6TextField.getText();
	}
	
	//Get methods for combo boxes
	
	protected String getPlayer1ComboBox()
	{
		return player1ComboBox.getSelectedItem().toString();
	}
	
	protected String getPlayer2ComboBox()
	{
		return player2ComboBox.getSelectedItem().toString();
	}
	
	protected String getPlayer3ComboBox()
	{
		return player3ComboBox.getSelectedItem().toString();
	}
	
	protected String getPlayer4ComboBox()
	{
		return player4ComboBox.getSelectedItem().toString();
	}
	
	protected String getPlayer5ComboBox()
	{
		return player5ComboBox.getSelectedItem().toString();
	}
	
	protected String getPlayer6ComboBox()
	{
		return player6ComboBox.getSelectedItem().toString();
	}
}