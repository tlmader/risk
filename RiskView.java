import java.util.ArrayList;

import java.lang.StringBuilder;

import java.io.OutputStream;
import java.io.PrintStream;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.text.DefaultCaret;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * These classes set up the GUI of the Risk program.
 * The main menu, dialog for setting player count, dialog for name/color settings for each 
 * player, the Risk game board, and a menu used during a Risk game session are included.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
public class RiskView extends JFrame {	
	private JPanel mainPanel;

	private GridLayout mainLayout;
	
	private JButton newGameButton;
	private JButton loadGameButton;
	private JButton quitButton;
	
	private String newGameButtonName = "newGameBtn";
	private String loadGameButtonName = "loadGameBtn";
	private String quitButtonName = "quitBtn";
	
	/**
	 * Constructs the main menu.
	 **/
	protected RiskView()
	{
		setTitle("Java-Risk");
		setPreferredSize(new Dimension(300, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		add(mainMenu());
		
		pack();
		setVisible(true);
		toFront();
	}
	
	private JPanel mainMenu()
	{
		// Creates the panel
		mainPanel = new JPanel();
		
		// Sets Layout
		mainLayout = new GridLayout(3, 1, 5, 5);
		mainPanel.setLayout(mainLayout);
		
		// Creates buttons
		newGameButton = new JButton("New Game");
		loadGameButton = new JButton("Load Game");
		quitButton = new JButton("Quit");
		
		// Sets button commands
		newGameButton.setActionCommand(newGameButtonName);
		loadGameButton.setActionCommand(loadGameButtonName);
		quitButton.setActionCommand(quitButtonName);
		
		// Adds buttons to mainPanel
		mainPanel.add(newGameButton);
		mainPanel.add(loadGameButton);
		mainPanel.add(quitButton);
		
		return mainPanel;
	}
	
	// Action listeners for riskView
	protected void riskViewActionListeners(ActionListener evt)
	{
		newGameButton.addActionListener(evt);
		loadGameButton.addActionListener(evt);
		quitButton.addActionListener(evt);
	}
}

class PlayerCountDialog extends JDialog {
	private JPanel playerCountPanel;
	
	private GridLayout playerCountLayout;
	
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
	
	/**
	 * Constructs the dialog for player count selection.
	 **/
	protected PlayerCountDialog(RiskView owner, boolean modality)
	{
		super(owner, modality);
		setTitle("Java-Risk");
		
		setPreferredSize(new Dimension(150, 280));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		add(playerCountPanel());
		
		setLocationRelativeTo(null);
		
		pack();
	}
	
	/**
	 * The main panel.
	 **/
	private JPanel playerCountPanel()
	{
		playerCountPanel = new JPanel();
		
		playerCountLayout = new GridLayout(6, 1, 5, 5);
		playerCountPanel.setLayout(playerCountLayout);
		
		playerCountLabel = new JLabel("Number of Leaders:");
		
		threePlayersBtn = new JButton("Three");
		fourPlayersBtn = new JButton("Four");
		fivePlayersBtn = new JButton("Five");
		sixPlayersBtn = new JButton("Six");
		backBtn = new JButton ("Back");
		
		threePlayersBtn.setActionCommand(threePlayersBtnName);
		fourPlayersBtn.setActionCommand(fourPlayersBtnName);
		fivePlayersBtn.setActionCommand(fivePlayersBtnName);
		sixPlayersBtn.setActionCommand(sixPlayersBtnName);
		backBtn.setActionCommand(backBtnName);
		
		playerCountPanel.add(playerCountLabel);
		playerCountPanel.add(threePlayersBtn);
		playerCountPanel.add(fourPlayersBtn);
		playerCountPanel.add(fivePlayersBtn);
		playerCountPanel.add(sixPlayersBtn);
		playerCountPanel.add(backBtn);
		
		return playerCountPanel;
	}	

	/**
	 * Adds the action listeners.
	 **/
	protected void addActionListeners(ActionListener evt)
	{
		threePlayersBtn.addActionListener(evt);
		fourPlayersBtn.addActionListener(evt);
		fivePlayersBtn.addActionListener(evt);
		sixPlayersBtn.addActionListener(evt);
		backBtn.addActionListener(evt);
	}
}

class PlayerSettingsDialog extends JDialog {
	private JPanel playerNamesPanel;
	private JPanel playerTypesPanel;
	
	private GridLayout mainLayout;
	private GridLayout playerNamesLayout;
	private GridLayout playerTypesLayout;
	
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
	
	private JComboBox player1ComboBox;
	private JComboBox player2ComboBox;
	private JComboBox player3ComboBox;
	private JComboBox player4ComboBox;
	private JComboBox player5ComboBox;
	private JComboBox player6ComboBox;
	
	private int playerCount;
	
	private String[] types = { "Human", "AI" };
	
	public PlayerSettingsDialog(PlayerCountDialog owner, boolean modality, int playerCount)
	{		
		super(owner, modality);
		setTitle("Java-Risk");
		
		this.playerCount = playerCount;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		mainLayout = new GridLayout(1, 2, 5, 5); //  Make second parameter '2' if including playerTypesPanel
		setLayout(mainLayout);
				
		add(playerNamesPanel());
		add(playerTypesPanel());
		
		setLocationRelativeTo(null);
		
		pack();
	}
	
	private JPanel playerNamesPanel() {
	
		playerNamesPanel = new JPanel();
		
		playerNamesPanel.setPreferredSize(new Dimension(200, playerCount * 40 + 40));
		
		playerNamesLayout = new GridLayout(playerCount + 1, 1, 5, 5);
		playerNamesPanel.setLayout(playerNamesLayout);
		
		player1TextField = new JTextField("Theodoric");
		player2TextField = new JTextField("Napoleon");
		player3TextField = new JTextField("Alexander");
		
		playerNamesPanel.add(player1TextField);
		playerNamesPanel.add(player2TextField);
		playerNamesPanel.add(player3TextField);
		
		if (playerCount > 3) {
			player4TextField = new JTextField("William");
			playerNamesPanel.add(player4TextField);
		}
		if (playerCount > 4) {
			player5TextField = new JTextField("Georgy");
			playerNamesPanel.add(player5TextField);
		}
		if (playerCount > 5) {
			player6TextField = new JTextField("Cyrus");
			playerNamesPanel.add(player6TextField);
		}
		
		backBtn = new JButton ("Back");
		backBtn.setActionCommand(backBtnName);
		playerNamesPanel.add(backBtn);
		
		return playerNamesPanel;
	}

	private JPanel playerTypesPanel() {

		playerTypesPanel = new JPanel();
		
		playerTypesPanel.setPreferredSize(new Dimension(200, playerCount * 40 + 40));
		
		playerTypesLayout = new GridLayout(playerCount + 1, 1, 5, 5);
		playerTypesPanel.setLayout(playerTypesLayout);
		
		player1ComboBox = new JComboBox(types);
		player2ComboBox = new JComboBox(types);
		player3ComboBox = new JComboBox(types);
		
		playerTypesPanel.add(player1ComboBox);
		playerTypesPanel.add(player2ComboBox);
		playerTypesPanel.add(player3ComboBox);
		
		if (playerCount > 3) {
			player4ComboBox = new JComboBox(types);
			playerTypesPanel.add(player4ComboBox);
		}
		if (playerCount > 4) {
			player5ComboBox = new JComboBox(types);
			playerTypesPanel.add(player5ComboBox);
		}
		if (playerCount > 5) {
			player6ComboBox = new JComboBox(types);
			playerTypesPanel.add(player6ComboBox);
		}
		
		startBtn = new JButton ("Start Game");
		
		startBtn.setActionCommand(startBtnName);
		
		playerTypesPanel.add(startBtn);

		return playerTypesPanel;
	}
	
	public void addActionListeners(ActionListener evt)
	{
		startBtn.addActionListener(evt);
		backBtn.addActionListener(evt);
	}
	
	// Get methods for text fields
	
	protected String getPlayerTextField(int playerNum)
	{
		if (playerNum == 1)
		{
			return player1TextField.getText();
		}
		else if (playerNum == 2)
		{
			return player2TextField.getText();
		}
		else if (playerNum == 3)
		{
			return player3TextField.getText();
		}
		else if (playerNum == 4)
		{
			return player4TextField.getText();
		}
		else if (playerNum == 5)
		{
			return player5TextField.getText();
		}
		else
		{
			return player6TextField.getText();
		}
	}
	
	// Get methods for combo boxes
	
	protected String getPlayerComboBox(int playerNum)
	{
		if (playerNum == 1)
		{
			return player1ComboBox.getSelectedItem().toString();
		}
		else if (playerNum == 2)
		{
			return player2ComboBox.getSelectedItem().toString();
		}
		else if (playerNum == 3)
		{
			return player3ComboBox.getSelectedItem().toString();
		}
		else if (playerNum == 4)
		{
			return player4ComboBox.getSelectedItem().toString();
		}
		else if (playerNum == 5)
		{
			return player5ComboBox.getSelectedItem().toString();
		}
		else
		{
			return player6ComboBox.getSelectedItem().toString();
		}
	}
}

class BoardView extends JDialog  {
	
	private int i;
	
	private String[] countriesArray;
	
	private JPanel messagePanel;
	private JPanel mapPanel;
	private JPanel actionPanel;
	private JPanel countryInfoPanel;

	private JPanel northAmericaPanel;
	private JPanel southAmericaPanel;
	private JPanel europePanel;
	private JPanel africaPanel;
	private JPanel asiaPanel;
	private JPanel australiaPanel;
	
	private GridBagConstraints c;
	private GridBagLayout mainLayout;
	private GridBagLayout messageLayout;
	private GridBagLayout actionLayout;
	
	private JLabel selectedLabel;
	private JLabel targetLabel;
	private JLabel continentLabel;
	
	private String menuBtnName = "menuBtn";
	private String reinforceBtnName = "reinforceBtn";
	private String attackBtnName = "attackBtn";
	private String fortifyBtnName = "fortifyBtn";
	private String turnInBtnName = "turnInBtn";
	private String endTurnBtnName = "endTurnBtn";
	
	private JButton menuBtn;
	private JButton reinforceBtn;
	private JButton attackBtn;
	private JButton fortifyBtn;
	private JButton turnInBtn;
	private JButton endTurnBtn;
	
	private JTextArea printTextArea;
	
	private JComboBox selectedComboBox;
	private JComboBox targetComboBox;
	
	private JList cardsList;
	private JList countryAList;
	private JList countryBList;
	
	private JScrollPane mapScrollPane;
	private JScrollPane messageScrollPane;
	private JScrollPane cardsListScrollPane;
	private JScrollPane countryBScrollPane;
	private JScrollPane countryAScrollPane;
	
	private DefaultCaret caret;
	
	private ImageIcon mapImageIcon;
	
	private RiskModel model;
	
	private RiskListModel cardsListModel;
	private RiskListModel countryAListModel;
	private RiskListModel countryBListModel;
	
	private CountryLabel countryLabel;
	
	/**
	 * Constructs the Risk game board.
	 **/
	public BoardView(PlayerSettingsDialog owner, boolean modality, RiskModel model) {		
		
		super(owner, modality);
		setTitle("Java-Risk");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		this.model = model;
		
		//  GridBagLayout allows a flexible sizing of components
		mainLayout = new GridBagLayout();
		setLayout(mainLayout);
		
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		add(messagePanel());
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 8;
		c.weighty = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		add(mapPanel());
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		add(actionPanel());
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		add(countryInfoPanel(model.getBoard()));
		
		setLocationRelativeTo(null);
		
		pack();
	}
	
	/**
	 * Constructor for loading the game.
	 **/
	public BoardView(RiskView owner, boolean modality, RiskModel model) {		
		
		super(owner, modality);
		setTitle("Java-Risk");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		this.model = model;
		
		//  GridBagLayout allows a flexible sizing of components
		mainLayout = new GridBagLayout();
		setLayout(mainLayout);
		
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		add(messagePanel());
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 8;
		c.weighty = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		add(mapPanel());
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		add(actionPanel());
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_END;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		add(countryInfoPanel(model.getBoard()));
		
		setLocationRelativeTo(null);
		
		pack();
	}
	
	/**
	 * The panel for the card display and turn-in button.
	 **/
	private JPanel messagePanel() {
	
		messagePanel = new JPanel();
		messagePanel.setPreferredSize(new Dimension(300, 980));
		messageLayout = new GridBagLayout();
		messagePanel.setLayout(messageLayout);
		
		c = new GridBagConstraints();
		
		printTextArea = new JTextArea();
		System.setOut(new PrintStream(new TextAreaOutputStream(printTextArea)));
		printTextArea.setFocusable(false);
		printTextArea.setLineWrap(true);
		printTextArea.setWrapStyleWord(true);
		caret = (DefaultCaret)printTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		messageScrollPane = new JScrollPane(printTextArea);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 14;
		c.gridx = 0;
		c.gridy = 0;
		messagePanel.add(messageScrollPane, c);
		
		return messagePanel;
	}
	
	/**
	 * The panel containing the scrollable Risk map image.
	 **/
	private JPanel mapPanel() {
	
		mapPanel = new JPanel();
		mapPanel.setLayout(new GridLayout(1, 1, 5, 5));
		mapImageIcon = new ImageIcon("Map.jpg");
		mapScrollPane = new JScrollPane(new JLabel(mapImageIcon));
		mapScrollPane.setPreferredSize(new Dimension(1080, 980));
		mapPanel.add(mapScrollPane);
		return mapPanel;
	}
	
	/**
	 * The panel for the buttons which allow the user to perform actions.
	 **/
	private JPanel actionPanel() {
	
		actionPanel = new JPanel();
		
		actionPanel.setPreferredSize(new Dimension(200, 980));
		
		actionLayout = new GridBagLayout();
		actionPanel.setLayout(actionLayout);
		
		selectedLabel = new JLabel("Selected Territory:");
		targetLabel = new JLabel("Adjacent Territory:");
		
		menuBtn = new JButton("Menu");
		turnInBtn = new JButton("Turn In Cards");
		reinforceBtn = new JButton("Place Reinforcements");
		attackBtn = new JButton("Attack!");
		fortifyBtn = new JButton("Fortify");
		endTurnBtn = new JButton("End Turn");
		
		menuBtn.setActionCommand(menuBtnName);
		turnInBtn.setActionCommand(turnInBtnName);
		reinforceBtn.setActionCommand(reinforceBtnName);
		attackBtn.setActionCommand(attackBtnName);
		fortifyBtn.setActionCommand(fortifyBtnName);
		endTurnBtn.setActionCommand(endTurnBtnName);
		
		cardsListModel = new RiskListModel(model, "cards");
		countryAListModel = new RiskListModel(model, "countryA");
		countryBListModel = new RiskListModel(model, "countryB");
		
		model.addObserver((RiskListModel)cardsListModel);
		model.addObserver((RiskListModel)countryAListModel);
		model.addObserver((RiskListModel)countryBListModel);
		
		cardsList = new JList(cardsListModel);
		cardsList.setLayoutOrientation(JList.VERTICAL_WRAP);
		cardsList.setVisibleRowCount(6);
		
		countryAList = new JList(countryAListModel);
		countryAList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		countryAList.setLayoutOrientation(JList.VERTICAL_WRAP);
		countryAList.setVisibleRowCount(42);
		
		countryBList = new JList(countryBListModel);
		countryBList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		countryBList.setLayoutOrientation(JList.VERTICAL_WRAP);
		countryBList.setVisibleRowCount(6);
		
		countryAScrollPane = new JScrollPane(countryAList);
		countryBScrollPane = new JScrollPane(countryBList);
		
		c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		actionPanel.add(menuBtn, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 5;
		c.gridx = 0;
		c.gridy = 2;
		actionPanel.add(cardsList, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		actionPanel.add(turnInBtn, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 4;
		actionPanel.add(selectedLabel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 10;
		c.gridx = 0;
		c.gridy = 5;
		actionPanel.add(countryAScrollPane, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 6;
		actionPanel.add(reinforceBtn, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 7;
		actionPanel.add(targetLabel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 10;
		c.gridx = 0;
		c.gridy = 8;
		actionPanel.add(countryBScrollPane, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 9;
		actionPanel.add(attackBtn, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 10;
		actionPanel.add(fortifyBtn, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 11;
		actionPanel.add(endTurnBtn, c);
		
		return actionPanel;
	}
	
	private JPanel countryInfoPanel(Board board) {
		
		countryInfoPanel = new JPanel();
		
		countryInfoPanel.setPreferredSize(new Dimension(320, 980));
		countryInfoPanel.setLayout(new GridBagLayout());
	
		northAmericaPanel = new JPanel();
		northAmericaPanel.setPreferredSize(new Dimension(320, 100));
		northAmericaPanel.setLayout(new GridLayout(5, 2, 5, 5));
		
		for (i = 0; i < board.getMemberCountries("North America").size(); i++) {
			countryLabel = new CountryLabel(model, board.getMemberCountries("North America").get(i));
			countryLabel.setFont(new Font("Arial", Font.PLAIN, 10));
			northAmericaPanel.add(countryLabel);
			model.addObserver((CountryLabel) countryLabel);
		}
		southAmericaPanel = new JPanel();
		southAmericaPanel.setPreferredSize(new Dimension(320, 60));
		southAmericaPanel.setLayout(new GridLayout(3, 2, 5, 5));
		
		for (i = 0; i < board.getMemberCountries("South America").size(); i++) {
			countryLabel = new CountryLabel(model, board.getMemberCountries("South America").get(i));
			countryLabel.setFont(new Font("Arial", Font.PLAIN, 10));
			southAmericaPanel.add(countryLabel);
			model.addObserver((CountryLabel) countryLabel);
		}
		europePanel = new JPanel();
		europePanel.setPreferredSize(new Dimension(320, 80));
		europePanel.setLayout(new GridLayout(4, 2, 5, 5));
		
		for (i = 0; i < board.getMemberCountries("Europe").size(); i++) {
			countryLabel = new CountryLabel(model, board.getMemberCountries("Europe").get(i));;
			countryLabel.setFont(new Font("Arial", Font.PLAIN, 10));
			europePanel.add(countryLabel);
			model.addObserver((CountryLabel) countryLabel);
		}
		africaPanel = new JPanel();
		africaPanel.setPreferredSize(new Dimension(320, 60));
		africaPanel.setLayout(new GridLayout(3, 2, 5, 5));
		
		for (i = 0; i < board.getMemberCountries("Africa").size(); i++) {
			countryLabel = new CountryLabel(model, board.getMemberCountries("Africa").get(i));
			countryLabel.setFont(new Font("Arial", Font.PLAIN, 10));
			africaPanel.add(countryLabel);
			model.addObserver((CountryLabel) countryLabel);
		}
		asiaPanel = new JPanel();
		asiaPanel.setPreferredSize(new Dimension(320, 120));
		asiaPanel.setLayout(new GridLayout(6, 2, 5, 5));
		
		for (i = 0; i < board.getMemberCountries("Asia").size(); i++) {
			countryLabel = new CountryLabel(model, board.getMemberCountries("Asia").get(i));
			countryLabel.setFont(new Font("Arial", Font.PLAIN, 10));
			asiaPanel.add(countryLabel);
			model.addObserver((CountryLabel) countryLabel);
		}
		australiaPanel = new JPanel();
		australiaPanel.setPreferredSize(new Dimension(320, 60));
		australiaPanel.setLayout(new GridLayout(3, 2, 5, 5));
		
		for (i = 0; i < board.getMemberCountries("Australia").size(); i++) {
			countryLabel = new CountryLabel(model, board.getMemberCountries("Australia").get(i));
			countryLabel.setFont(new Font("Arial", Font.PLAIN, 10));
			australiaPanel.add(countryLabel);
			model.addObserver((CountryLabel) countryLabel);
		}
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		countryInfoPanel.add(new JLabel("North America"), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		countryInfoPanel.add(northAmericaPanel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		countryInfoPanel.add(new JLabel("South America"), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		countryInfoPanel.add(southAmericaPanel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 4;
		countryInfoPanel.add(new JLabel("Europe"), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 5;
		countryInfoPanel.add(europePanel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 6;
		countryInfoPanel.add(new JLabel("Africa"), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 7;
		countryInfoPanel.add(africaPanel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 8;
		countryInfoPanel.add(new JLabel("Asia"), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 9;
		countryInfoPanel.add(asiaPanel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 10;
		countryInfoPanel.add(new JLabel("Australia"), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 11;
		countryInfoPanel.add(australiaPanel, c);
		
		return countryInfoPanel;
	}
	
	/**
	 * Adds the action listeners for the buttons and lists.
	 **/
	protected void addActionListeners(ActionListener evt1, ListSelectionListener evt2) {
	
		menuBtn.addActionListener(evt1);
		turnInBtn.addActionListener(evt1);
		reinforceBtn.addActionListener(evt1);
		attackBtn.addActionListener(evt1);
		fortifyBtn.addActionListener(evt1);
		endTurnBtn.addActionListener(evt1);
		
		countryAList.addListSelectionListener(evt2); 
	}
	
	/**
	 * Used for adjusting the country B list according to the country A selecion.
	 * @return the integer of the selected index in the country A list.
	 **/
	protected int getCountryAIndex() {
		return countryAList.getSelectedIndex();
	}
	
	/**
	 * Passes the indices of the cards to remove from the current player's hand.
	 * @return the array of selected indices in the cards list.
	 **/
	protected int[] getCardsToRemove() {
		return cardsList.getSelectedIndices();
	}
	
	/**
	 * Passes countryA for the model.
	 * @return the String of the selected value in country A list.
	 **/
	protected String getCountryA() {
		return countryAList.getSelectedValue().toString();
	}
	
	/**
	 * Passes countryB for the model.
	 * @return the String of the selected value in country B list.
	 **/
	protected String getCountryB() {
		return countryBList.getSelectedValue().toString();
	}
}

class MenuDialog extends JDialog {
	private JPanel menuPanel;
	
	private GridLayout menuLayout;
	
	private JButton returnBtn;
	private JButton saveBtn;
	private JButton quitBtn;
	
	private String returnBtnName = "returnBtn";
	private String saveBtnName = "saveBtn";
	private String quitBtnName = "quitBtn";
	
	public MenuDialog(BoardView owner, boolean modality)
	{
		super(owner, modality);
		setTitle("Java-Risk");
		
		setPreferredSize(new Dimension(200, 120));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		add(menuPanel());
		
		setLocationRelativeTo(owner);
		
		pack();
	}
	
	private JPanel menuPanel()
	{
		menuPanel = new JPanel();
		
		menuLayout = new GridLayout(3, 1, 5, 5);
		menuPanel.setLayout(menuLayout);
		
		returnBtn = new JButton("Return to Game");
		saveBtn = new JButton("Save Game");
		quitBtn = new JButton("Quit");
		
		returnBtn.setActionCommand(returnBtnName);
		saveBtn.setActionCommand(saveBtnName);
		quitBtn.setActionCommand(quitBtnName);
		
		menuPanel.add(returnBtn);
		menuPanel.add(saveBtn);
		menuPanel.add(quitBtn);
		
		return menuPanel;
	}
	
	protected void addActionListeners(ActionListener evt)
	{
		returnBtn.addActionListener(evt);
		saveBtn.addActionListener(evt);
		quitBtn.addActionListener(evt);
	}
}