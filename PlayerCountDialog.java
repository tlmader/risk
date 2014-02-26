import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JDialog;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.lang.StringBuilder;

public class PlayerCountDialog extends JDialog
{
	private JPanel mainPanel;
	
	private JButton twoPlayersBtn;
	private JButton threePlayersBtn;
	private JButton fourPlayersBtn;
	private JButton fivePlayersBtn;
	private JButton sixPlayersBtn;
	private JButton backBtn;
	
	private String twoPlayersBtnName = "twoPlayersBtn";
	private String threePlayersBtnName = "threePlayersBtn";
	private String fourPlayersBtnName = "fourPlayersBtn";
	private String fivePlayersBtnName = "fivePlayersBtn";
	private String sixPlayersBtnName = "sixPlayersBtn";
	private String backBtnName = "backBtn";
	
	public PlayerCountDialog( RiskView owner, boolean modality )
	{
		super( owner, modality );
		setTitle( "Java-Risk" );
		
		setPreferredSize( new Dimension( 300, 200 ) );
		setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );
		setResizable( false );
		
		mainPanel = new JPanel();
		
		twoPlayersBtn = new JButton( "Two" );
		threePlayersBtn = new JButton( "Three" );
		fourPlayersBtn = new JButton( "Four" );
		fivePlayersBtn = new JButton( "Five" );
		sixPlayersBtn = new JButton( "Six" );
		backBtn = new JButton ( "Back" );
		
		twoPlayers.setActionCommand( twoPlayersBtnName );
		threePlayers.setActionCommand( threePlayersBtnName );
		fourPlayers.setActionCommand( fourPlayersBtnName );
		fivePlayers.setActionCommand( fivePlayersBtnName );
		sixPlayers.setActionCommand( sixPlayersBtnName );
		backBtn.setActionCommand( backBtnName );
		
		mainPanel.add( twoPlayersBtn );
		mainPanel.add( threePlayersBtn );
		mainPanel.add( fourPlayersBtn );
		mainPanel.add( fivePlayersBtn );
		mainPanel.add( sixPlayersBtn );

		add( mainPanel );
		
		setLocationRelativeTo( owner );
		
		pack();
	}

	protected void playerCountActionListener( ActionListener event )
	{
		twoPlayersBtn.addActionListener( event );
		threePlayersBtn.addActionListener( event );
		fourPlayersBtn.addActionListener( event );
		fivePlayersBtn.addActionListener( event );
		sixPlayersBtn.addActionListener( event );
		backBtn.addActionListener( event );
	}
}