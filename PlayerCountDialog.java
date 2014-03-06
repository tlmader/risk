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
	private JPanel mainPanel;
	
	private JLabel playerCountLabel;

	private JButton threePlayersBtn;
	private JButton fourPlayersBtn;
	private JButton fivePlayersBtn;
	private JButton sixPlayersBtn;
	private JButton backBtn;
	
	private String threePlayersBtnName = "threePlayersBtn";
	private String fourPlayersBtnName = "fourPlayersBtn";
	private String fivePlayersBtnName = "fivePlayersBtn";
	private String sixPlayersBtnName = "sixPlayersBtn";
	private String backBtnName = "backBtn";
	
	protected PlayerCountDialog( RiskView owner, boolean modality )
	{
		super( owner, modality );
		setTitle( "Java-Risk" );
		
		setPreferredSize( new Dimension( 150, 280 ) );
		setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
		setResizable( false );
		
		mainPanel = new JPanel();
		
		GridLayout playerCountLayout = new GridLayout( 6, 2, 5, 5 );
		mainPanel.setLayout( playerCountLayout );
		
		playerCountLabel = new JLabel( "Number of Leaders:" );
		
		threePlayersBtn = new JButton( "Three" );
		fourPlayersBtn = new JButton( "Four" );
		fivePlayersBtn = new JButton( "Five" );
		sixPlayersBtn = new JButton( "Six" );
		backBtn = new JButton ( "Back" );
		
		threePlayersBtn.setActionCommand( threePlayersBtnName );
		fourPlayersBtn.setActionCommand( fourPlayersBtnName );
		fivePlayersBtn.setActionCommand( fivePlayersBtnName );
		sixPlayersBtn.setActionCommand( sixPlayersBtnName );
		backBtn.setActionCommand( backBtnName );
		
		mainPanel.add( playerCountLabel );
		mainPanel.add( threePlayersBtn );
		mainPanel.add( fourPlayersBtn );
		mainPanel.add( fivePlayersBtn );
		mainPanel.add( sixPlayersBtn );
		mainPanel.add( backBtn );
		
		add( mainPanel );
		
		setLocationRelativeTo( owner );
		
		pack();
	}	

	protected void playerCountActionListeners( ActionListener event )
	{
		threePlayersBtn.addActionListener( event );
		fourPlayersBtn.addActionListener( event );
		fivePlayersBtn.addActionListener( event );
		sixPlayersBtn.addActionListener( event );
		backBtn.addActionListener( event );
	}
}