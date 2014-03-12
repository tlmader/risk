/* PlayerCountDialog.java
*
*  Description: This class creates a dialog that prompts 
*				the user for the number of Risk players.
*
*  Author: Ted Mader, 3/10/2014
*/

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.lang.StringBuilder;

public class PlayerCountDialog extends JDialog
{
	private JPanel playerCountPanel;
	
	private GridLayout playerCountLayout;
	
	private JLabel playerCountLabel;

	private JButton threePlayersButton;
	private JButton fourPlayersButton;
	private JButton fivePlayersButton;
	private JButton sixPlayersButton;
	private JButton backButton;
	
	private String threePlayersButtonName = "threePlayersButton";
	private String fourPlayersButtonName = "fourPlayersButton";
	private String fivePlayersButtonName = "fivePlayersButton";
	private String sixPlayersButtonName = "sixPlayersButton";
	private String backButtonName = "backButton";
	
	protected PlayerCountDialog( RiskView owner, boolean modality )
	{
		super( owner, modality );
		setTitle( "Java-Risk" );
		
		setPreferredSize( new Dimension( 150, 280 ) );
		setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
		setResizable( false );
		
		add( playerCountPanel() );
		
		setLocationRelativeTo( null );
		
		pack();
	}
	
	private JPanel playerCountPanel()
	{
		playerCountPanel = new JPanel();
		
		playerCountLayout = new GridLayout( 6, 1, 5, 5 );
		playerCountPanel.setLayout( playerCountLayout );
		
		playerCountLabel = new JLabel( "Number of Leaders:" );
		
		threePlayersButton = new JButton( "Three" );
		fourPlayersButton = new JButton( "Four" );
		fivePlayersButton = new JButton( "Five" );
		sixPlayersButton = new JButton( "Six" );
		backButton = new JButton ( "Back" );
		
		threePlayersButton.setActionCommand( threePlayersButtonName );
		fourPlayersButton.setActionCommand( fourPlayersButtonName );
		fivePlayersButton.setActionCommand( fivePlayersButtonName );
		sixPlayersButton.setActionCommand( sixPlayersButtonName );
		backButton.setActionCommand( backButtonName );
		
		playerCountPanel.add( playerCountLabel );
		playerCountPanel.add( threePlayersButton );
		playerCountPanel.add( fourPlayersButton );
		playerCountPanel.add( fivePlayersButton );
		playerCountPanel.add( sixPlayersButton );
		playerCountPanel.add( backButton );
		
		return playerCountPanel;
	}	

	protected void playerCountActionListeners( ActionListener event )
	{
		threePlayersButton.addActionListener( event );
		fourPlayersButton.addActionListener( event );
		fivePlayersButton.addActionListener( event );
		sixPlayersButton.addActionListener( event );
		backButton.addActionListener( event );
	}
}