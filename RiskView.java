/* RiskView.java
*
*  Description: This class sets up the main GUI screen of the
*				Risk program.
*
*  Author: Ted Mader, 3/10/2014
*/

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.lang.StringBuilder;

public class RiskView extends JFrame
{	
	private JPanel mainPanel;

	private GridLayout mainLayout;
	
	private JButton newGameButton;
	private JButton loadGameButton;
	private JButton quitButton;
	
	private String newGameButtonName = "newGameButton";
	private String loadGameButtonName = "loadGameButton";
	private String quitButtonName = "quitButton";
	
	protected RiskView()
	{
		setTitle( "Java-Risk" );
		setPreferredSize( new Dimension( 300, 300 ) );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocationRelativeTo( null );
		setResizable( false );
		
		add( mainMenu() );
		
		pack();
		setVisible( true );
		toFront();
	}
	
	private JPanel mainMenu()
	{
		//Creates the panel
		mainPanel = new JPanel();
		
		//Sets Layout
		mainLayout = new GridLayout( 3, 1, 5, 5 );
		mainPanel.setLayout( mainLayout );
		
		//Creates buttons
		newGameButton = new JButton( "New Game" );
		loadGameButton = new JButton( "Load Game" );
		quitButton = new JButton( "Quit" );
		
		//Sets button commands
		newGameButton.setActionCommand( newGameButtonName );
		loadGameButton.setActionCommand( loadGameButtonName );
		quitButton.setActionCommand( quitButtonName );
		
		//Adds buttons to mainPanel
		mainPanel.add( newGameButton );
		mainPanel.add( loadGameButton );
		mainPanel.add( quitButton );
		
		return mainPanel;
	}
	
	//Action listeners for riskView
	protected void riskViewActionListeners( ActionListener event )
	{
		newGameButton.addActionListener( event );
		loadGameButton.addActionListener( event );
		quitButton.addActionListener( event );
	}
}