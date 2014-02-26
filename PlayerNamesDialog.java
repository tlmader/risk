import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JDialog;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.lang.StringBuilder;

public class PlayerNamesDialog extends JDialog
{
	private JPanel mainPanel;
	
	private JButton startBtn;
	private JButton backBtn;
	
	private String startBtnName = "startBtn";
	private String backBtnName = "backBtn";
	
	private JTextField player1TextField;
	private JTextField player2TextField;
	private JTextField player3TextField;
	private JTextField player4TextField;
	private JTextField player5TextField;
	private JTextField player6TextField;
	
	private int playerCount;
	
	public PlayerNamesDialog( RiskView owner, boolean modality, int newPlayerCount )
	{
		super( owner, modality );
		setTitle( "Show Text" );
		
		setPreferredSize( new Dimension( 320, 150 ) );
		setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
		setResizable( false );
		
		mainPanel = new JPanel();
		
		startBtn = new JButton ( "Start Game" );
		backBtn = new JButton ( "Back" );
		
		startBtn.setActionCommand( startBtnName );
		backBtn.setActionCommand( backBtnName );
		
		player1TextField = new JTextField();
		player2TextField = new JTextField();
		mainPanel.add( player1TextField );
		mainPanel.add( player2TextField );
		
		playerCount = newPlayerCount;
		
		if( playerCount > 2 )
		{
			JTextField player3TextField = newJTextField();
			mainPanel.add( player3TextField );
		}
		if( playerCount > 3 )
		{
			JTextField player4TextField = newJTextField();
			mainPanel.add( player4TextField );
		}
		if( playerCount > 4 )
		{
			JTextField player5TextField = newJTextField();
			mainPanel.add( player5TextField );
		}
		if( playerCount > 5 )
		{
			JTextField player6TextField = newJTextField();
			mainPanel.add( player6TextField );
		}
	}
	
	protected void playerNamesActionListener( ActionListener event )
	{
		startBtn.addActionListener( event );
		backBtn.addActionListener( event );
	}
	
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
}