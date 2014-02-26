/*	RiskView.java
*
*  Description: This class sets up the main GUI screen of the
*				Risk program.
*
*  Author: Ted Mader, 2/25/2014
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
	private JButton newGameBtn;
	private JButton loadGameBtn;
	private JButton quitBtn;
	
	private String newGameBtnName = "newGameBtn";
	private String loadGameBtnName = "loadGameBtn";
	private String quitBtnName = "quitBtn";
	
	protected RiskView()
	{
		setTitle( "Java-Risk" );
		setPreferredSize( new Dimension( 400, 200 ) );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo( null );
		setResizable( false );
		
		add(mainMenu);
		
		pack();
		setVisible( true );
		toFront();
	}
	
	private JPanel mainMenu()
	{
		JPanel mainPanel = new JPanel();
		
		newGameBtn = new JButton( "New Game" );
		loadGameBtn = new JButton( "Load Game" );
		quitBtn = new JButton( "Quit" );
		
		newGameBtn.setActionCommand( newGameBtnName );
		loadGameBtn.setActionCommand( loadGameBtnName );
		quitBtn.setActionCommand( cancelBtnName );
		
		mainPanel.add( newGameBtn );
		mainPanel.add( loadGameBtn );
		mainPanel.add( quitBtn );
		
		return mainPanel;
	}
	
	protected void riskViewActionListener( ActionListener event )
	{
		newGameBtn.addActionListener( event );
		loadGameBtn.addActionListener( event );
		cancelBtn.addActionListener( event );
	}
}