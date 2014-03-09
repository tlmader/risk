/* RiskBoard.java
*
*  Description: This class creates the Risk game board.
*
*  Author: Ted Mader, 3/10/2014
*/

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener; 	
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class RiskBoard extends JDialog implements MouseListener, MouseMotionListener
{
	private JPanel actionPanel;
	private JPanel handPanel;
	private JPanel mapPanel;
	
	private GridLayout actionLayout;
	private GridLayout mapLayout;
	
	private GridBagLayout mainLayout;
	private GridBagLayout handLayout;
	private GridBagConstraints c;
	
	private JLabel mapLabel;
	private JLabel selectedLabel;
	private JLabel targetLabel;
	
	
	private JButton menuButton;
	private JButton reinforceButton;
	private JButton attackButton;
	private JButton fortifyButton;
	private JButton turnInButton;
	
	private String menuButtonName = "menuButton";
	private String reinforceButtonName = "reinforceButton";
	private String attackButtonName = "attackButton";
	private String fortifyButtonName = "fortifyButton";
	private String turnInButtonName = "turnInButton";

	private JTextArea cardsTextArea;
	
	private JComboBox selectedComboBox;
	private JComboBox targetComboBox;
	
	private ImageIcon mapImageIcon;
	
	private JScrollPane mapScrollPane;
	
	private FileInputStream fileInputStream;
	private DataInputStream dataInputStream;
	private BufferedReader bufferedReader;
	
	private int i = 0;
	private String line;
	private String fileName = "Territories.txt";
	
	private String cardsString;
	
	private String[] territoriesArray;
	private String[] cardsArray = { "Example Card", "Example Card", "Example Card", "Example Card", "Example Card", "Example Card" };;

	private ArrayList<String> territoriesList;
	
	//Map drag stuff - currently not implemented; currently using JScrollPane
	private int mouseX = 200;
	private int mouseY = 100;
	
	private boolean drag = false;
	
	
	public RiskBoard( PlayerSettingsDialog owner, boolean modality )
	{
		super( owner, modality );
		setTitle( "Java-Risk" );
	
		setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
		setResizable( false );
		
		//GridBagLayout allows a flexible sizing of components
		
		mainLayout = new GridBagLayout();
		setLayout( mainLayout );
		
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets( 5, 5, 5, 5 );
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		add( handPanel() );
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets( 5, 5, 5, 5 );
		c.weightx = 8;
		c.weighty = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		add( mapPanel() );
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets( 5, 5, 5, 5 );
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		add( actionPanel() );
		
		setLocationRelativeTo( null );
		
		pack();
	}
	
	//Panel for the card display and Turn In button
	
	private JPanel handPanel()
	{
		handPanel = new JPanel();
		
		handPanel.setPreferredSize( new Dimension( 200, 320 ) );
		
		handLayout = new GridBagLayout();
		handPanel.setLayout( handLayout );
		
		c = new GridBagConstraints();
		
		cardsTextArea = new JTextArea();
		cardsTextArea.setFocusable(false);
		
		//Prints the player's hand to cardsTextArea
		for ( i = 0; i < cardsArray.length; i++ )
		{
			cardsTextArea.append( cardsArray[i] );
			cardsTextArea.append( "\n" );
		}
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets( 5, 5, 5, 5 );
		c.weightx = 0.5;
		c.weighty = 7;
		c.gridx = 0;
		c.gridy = 0;
		handPanel.add( cardsTextArea, c );

		turnInButton = new JButton( "Turn In Cards" );
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets( 5, 5, 5, 5 );
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 6;
		handPanel.add( turnInButton, c );
		
		turnInButton.setActionCommand( turnInButtonName );
		
		return handPanel;
	}
	
	//The Risk map
	
	public JPanel mapPanel()
	{
		mapPanel = new JPanel();
		
		mapLayout = new GridLayout( 1, 1, 5, 5 );
		mapPanel.setLayout( mapLayout );
		
		mapImageIcon = new ImageIcon( "Map.jpg" );
		
		mapLabel = new JLabel( mapImageIcon );
		
		mapScrollPane = new JScrollPane( mapLabel );
		mapScrollPane.setPreferredSize( new Dimension( 540, 320 ) );
		
		mapPanel.add( mapScrollPane );
		
		return mapPanel;
	}

	
	//Allows the user to perform various actions
	
	private JPanel actionPanel()
	{
		/*	ACTION PANEL
		*
		*	[Menu]
		*
		*	Selected Territory:
		*	[_______________________]
		*	[Place Reinforcements]
		*
		*	Target Territory:
		*	[_______________________]
		*	[Attack!]
		*	[Fortify]
		*/
	
		actionPanel = new JPanel();
		
		actionPanel.setPreferredSize( new Dimension( 200, 320 ) );
		
		actionLayout = new GridLayout( 8, 1, 5, 5 );
		actionPanel.setLayout( actionLayout );
		
		selectedLabel = new JLabel( "Selected Territory:" );
		targetLabel = new JLabel( "Target Territory:" );
		
		menuButton = new JButton( "Menu" );
		reinforceButton = new JButton( "Place Reinforcements" );
		attackButton = new JButton( "Attack!" );
		fortifyButton = new JButton( "Fortify" );
		
		menuButton.setActionCommand( menuButtonName );
		reinforceButton.setActionCommand( reinforceButtonName );
		attackButton.setActionCommand( attackButtonName );
		fortifyButton.setActionCommand( fortifyButtonName );

		//Reads Territories.txt to populate territories	array
		try
		{
			fileInputStream = new FileInputStream( fileName );
			dataInputStream = new DataInputStream( fileInputStream );
			bufferedReader = new BufferedReader( new InputStreamReader( dataInputStream ) );
			
			territoriesList = new ArrayList<String>();
			
			while( ( line = bufferedReader.readLine() ) != null )
			{
				line = line.trim();
				
				if( line.length() != 0 )
				{
					territoriesList.add( line );
				}
			}
			
			territoriesArray = ( String[] )territoriesList.toArray( new String[ territoriesList.size() ] );
			
			bufferedReader.close();
		}
		catch( Exception e )
		{
			System.out.println( "Error while reading " + fileName + " line-by-line: " + e.getMessage() );
		}
		
		selectedComboBox = new JComboBox( territoriesArray );
		targetComboBox = new JComboBox( territoriesArray );
		
		actionPanel.add( menuButton );
		actionPanel.add( selectedLabel );
		actionPanel.add( selectedComboBox );
		actionPanel.add( reinforceButton );
		actionPanel.add( targetLabel );
		actionPanel.add( targetComboBox );
		actionPanel.add( attackButton );
		actionPanel.add( fortifyButton );
		
		return actionPanel;
	}

	//Action listeners
	
	protected void riskBoardActionListeners( ActionListener event )
	{
		menuButton.addActionListener( event );
		reinforceButton.addActionListener( event );
		attackButton.addActionListener( event );
		fortifyButton.addActionListener( event );
		turnInButton.addActionListener( event );
	}
	
	//Get methods for combo boxes
	
	protected String getSelectedComboBox()
	{
		return selectedComboBox.getSelectedItem().toString();
	}
	
	protected String getTargetComboBox()
	{
		return targetComboBox.getSelectedItem().toString();
	}
	
	//Map drag stuff - currently not implemented; currently using JScrollPane
	//To Do: Find out how to place mouseListeners in RiskController.java and pass information back to mapLabel
	
	public void mousePressed( MouseEvent e )
	{
		if( e.getSource() == mapLabel )
		{
			drag = true;
		}
	}
	
	public void mouseReleased( MouseEvent e )
	{
		drag = false;
	}
	
	public void mouseDragged( MouseEvent e )
	{
		if( drag == true )
		{
			mapLabel = ( JLabel )e.getSource();
			mapLabel.setLocation( mapLabel.getX() + e.getX(), mapLabel.getY() + e.getY() );
		}
	}
	
	public void mouseMoved( MouseEvent e ) {}
	public void mouseEntered( MouseEvent e ) {}
	public void mouseExited( MouseEvent e ) {}
	public void mouseClicked( MouseEvent e ) {}
}