/* MenuDialog.java
*
*  Description: This class creates a menu dialog to be used
*				during a Risk game session.
*
*  Author: Ted Mader, 3/10/2014
*/

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class MenuDialog extends JDialog
{
	private JPanel menuPanel;
	
	private GridLayout menuLayout;
	
	private JButton returnButton;
	private JButton saveButton;
	private JButton quitButton;
	
	private String returnButtonName = "returnButton";
	private String saveButtonName = "saveButton";
	private String quitButtonName = "quitButton";
	
	public MenuDialog( RiskBoard owner, boolean modality )
	{
		super( owner, modality );
		setTitle( "Java-Risk" );
		
		setPreferredSize( new Dimension( 200, 120 ) );
		setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
		setResizable( false );
		
		add( menuPanel() );
		
		setLocationRelativeTo( owner );
		
		pack();
	}
	
	private JPanel menuPanel()
	{
		menuPanel = new JPanel();
		
		menuLayout = new GridLayout( 3, 1, 5, 5 );
		menuPanel.setLayout( menuLayout );
		
		returnButton = new JButton( "Return to Game" );
		saveButton = new JButton( "Save Game" );
		quitButton = new JButton( "Quit" );
		
		returnButton.setActionCommand( returnButtonName );
		saveButton.setActionCommand( saveButtonName );
		quitButton.setActionCommand( quitButtonName );
		
		menuPanel.add( returnButton );
		menuPanel.add( saveButton );
		menuPanel.add( quitButton );
		
		return menuPanel;
	}
	
	protected void menuDialogActionListeners( ActionListener event )
	{
		returnButton.addActionListener( event );
		saveButton.addActionListener( event );
		quitButton.addActionListener( event );
	}
}