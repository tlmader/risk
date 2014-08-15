import java.util.ArrayList;

import java.lang.Integer;

import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class maps the user's actions in the RiskView to the data and methods in the model.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
public class RiskController implements ActionListener {

	private RiskModel model;
	private RiskModel loadedModel;
	private RiskView view;
	
	private JFileChooser fileChooser;
	private ObjectInputStream objectReader;
	
	private PlayerCountDialog playerCountDialog;
	private BoardView boardView;	
	//Constructor
	public RiskController(RiskModel model, RiskView view) {
	
		System.out.println("Loaded Risk!");
		
		this.model = model;
		this.view = view;
		//Add this class' actionListener to riskView's buttons
		view.riskViewActionListeners(this);
	}
	
	//RiskView's controller
	public void actionPerformed(ActionEvent evt) {
	
		String actionEvent = evt.getActionCommand();
		
		if (actionEvent.equals("newGameBtn")) {
		
			System.out.println("Loading PlayerCountDialog...");
			//Opens the playerCountDialog
			playerCountDialog = new PlayerCountDialog(view, true);
			playerCountDialog.addActionListeners(new PlayerCountController(model, playerCountDialog));
			playerCountDialog.setVisible(true);
			
		} else if (actionEvent.equals("loadGameBtn")) {
			fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Java-Risk Save Files", "jrs");
			fileChooser.setFileFilter(filter);
			
			if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
			
				try {
				objectReader = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()));
				loadedModel = (RiskModel)objectReader.readObject();
				objectReader.close();
				boardView = new BoardView(view, true, loadedModel);
				boardView.addActionListeners(new BoardViewController(loadedModel, boardView), new RiskListController(loadedModel, boardView));
				
				} catch (IOException e) {
				System.out.println(e.getMessage());
				
				} catch (ClassNotFoundException e)	{
					System.out.println(e.getMessage());
				}
			}
		} else if (actionEvent.equals("quitBtn")) {
				model.quitGame();
				
		} else {
				System.out.println("Error: " + actionEvent + " actionEvent not found!");
		}
	}
}

/**
 * This class maps the user's actions in the PlayerCountDialog to the data and methods in 
 * the model.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
class PlayerCountController implements ActionListener {

	private RiskModel model;
	private PlayerCountDialog view;
	
	private PlayerSettingsDialog playerSettingsDialog;
	//Constructor
	public PlayerCountController(RiskModel model, PlayerCountDialog view)
	{
		System.out.println("Loaded PlayerCountController!");
		
		this.model = model;
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent evt) {
	
		String actionEvent = evt.getActionCommand();
		
		if (actionEvent.equals("threePlayersBtn")) {
		
			model.setPlayerCount(3);
			
			System.out.println("Loading PlayerSettingsDialog...");
			
			playerSettingsDialog = new PlayerSettingsDialog(view, true, model.getPlayerCount());
			playerSettingsDialog.addActionListeners(new PlayerSettingsController(model, playerSettingsDialog));
			playerSettingsDialog.setVisible(true);
		}
		
		else if (actionEvent.equals("fourPlayersBtn")) {
		
			model.setPlayerCount(4);
			
			System.out.println("Loading PlayerSettingsDialog...");
			
			playerSettingsDialog = new PlayerSettingsDialog(view, true, model.getPlayerCount());
			playerSettingsDialog.addActionListeners(new PlayerSettingsController(model, playerSettingsDialog));
			playerSettingsDialog.setVisible(true);
		}
		
		else if (actionEvent.equals("fivePlayersBtn"))
		{
			model.setPlayerCount(5);
			
			System.out.println("Loading PlayerSettingsDialog...");
			
			playerSettingsDialog = new PlayerSettingsDialog(view, true, model.getPlayerCount());
			playerSettingsDialog.addActionListeners(new PlayerSettingsController(model, playerSettingsDialog));
			playerSettingsDialog.setVisible(true);
		}
		
		else if (actionEvent.equals("sixPlayersBtn"))
		{
			model.setPlayerCount(6);
			
			System.out.println("Loading PlayerSettingsDialog...");
			
			playerSettingsDialog = new PlayerSettingsDialog(view, true, model.getPlayerCount());
			playerSettingsDialog.addActionListeners(new PlayerSettingsController(model, playerSettingsDialog));
			playerSettingsDialog.setVisible(true);
		}
		
		else if (actionEvent.equals("backBtn"))
		{
			view.dispose();
		}
		
		else
		{
			System.out.println("Error: " + actionEvent + " actionEvent not found!");
		}
	}
}

/**
 * This class maps the user's actions in the PlayerSettingsDialog to the data and methods in 
 * the model.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
class PlayerSettingsController implements ActionListener {
	private boolean isLoaded;
	
	private ArrayList<String> playerNames;
	private ArrayList<String> playerTypes;
	
	private RiskModel model;
	private PlayerSettingsDialog view;

	private BoardView boardView;
	
	public PlayerSettingsController(RiskModel model, PlayerSettingsDialog view) {
		System.out.println("Loaded PlayerSettingsController!");
		this.model = model;
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent evt) {
		isLoaded = false;
		String actionEvent = evt.getActionCommand();
		playerNames = new ArrayList<String>();
		playerTypes = new ArrayList<String>();
		
		if (actionEvent.equals("startBtn")) {
		
			System.out.println("Setting up player names and teams...");
			
			playerNames.add(view.getPlayerTextField(1));
			playerNames.add(view.getPlayerTextField(2));
			playerNames.add(view.getPlayerTextField(3));
			playerTypes.add(view.getPlayerComboBox(1));
			playerTypes.add(view.getPlayerComboBox(2));
			playerTypes.add(view.getPlayerComboBox(3));
			
			//Gets player names based on playerCount
			if (model.getPlayerCount() > 3) {
				playerNames.add(view.getPlayerTextField(4));
				playerTypes.add(view.getPlayerComboBox(4));
			}
			if (model.getPlayerCount() > 4) {
				playerNames.add(view.getPlayerTextField(5));
				playerTypes.add(view.getPlayerComboBox(5));
			}
			if (model.getPlayerCount() > 5) {
				playerNames.add(view.getPlayerTextField(6));
				playerTypes.add(view.getPlayerComboBox(6));
			}
	
			System.out.println("Initializing game...");
			
			//Initializes values for a new game
			try {
				isLoaded = model.initializeGame(playerNames, playerTypes);
			} catch (FileNotFoundException error) {
				System.out.println(error.getMessage());
			}
			
			//Opens the Risk game board if properly loaded
			if (isLoaded == true) {
				System.out.println("Game setup complete!\nLoading BoardView...");
				
				boardView = new BoardView(view, true, model);
				boardView.addActionListeners(new BoardViewController(model, boardView), new RiskListController(model, boardView));
				
				// boardView.addListSelectionListeners(new BoardViewListSelectionController(model, boardView));
				boardView.setVisible(true);
			}
		} else if (actionEvent.equals("backBtn")) {
			view.dispose();
		
		} else {
			System.out.println("Error: " + actionEvent + " actionEvent not found!");
		}
	}
}

/**
 * This class maps the user's actions in the BoardView to the data and methods in the model.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
class BoardViewController implements ActionListener {

	private RiskModel model;
	private BoardView view;
	private MenuDialog menuDialog;
	
	public BoardViewController(RiskModel model, BoardView view) {		
		this.model = model;
		this.view = view;
		model.startGame();
	}
	
	public void actionPerformed(ActionEvent evt) {
	
		String actionEvent = evt.getActionCommand();
		
		if (actionEvent.equals("menuBtn")) {
			//System.out.println("User pressed menuButton.");
			menuDialog = new MenuDialog(view, true);
			menuDialog.addActionListeners(new MenuController(model, menuDialog));
			menuDialog.setVisible(true);
			
		} else if (actionEvent.equals("turnInBtn")) {
			//System.out.println("User pressed turnInButton.");
			model.turnInCards(view.getCardsToRemove());
			
		} else if (actionEvent.equals("reinforceBtn")) {
			//System.out.println("User pressed reinforceButton.");view.getSelectedComboBox();
			model.reinforce(view.getCountryA().replaceAll("[0-9]", "").replaceAll("\\-", ""));
			
		} else if (actionEvent.equals("attackBtn")) {
			//System.out.println("User pressed attackButton.");
			model.attack(view.getCountryA().replaceAll("[0-9]", "").replaceAll("\\-", ""), view.getCountryB().replaceAll("[0-9]", "").replaceAll("\\-", ""));
			
		} else if (actionEvent.equals("fortifyBtn")) {
			//System.out.println("User pressed fortifyButton.");
			model.fortify(view.getCountryA().replaceAll("[0-9]", "").replaceAll("\\-", ""), view.getCountryB().replaceAll("[0-9]", "").replaceAll("\\-", ""));
			
		} else if (actionEvent.equals("endTurnBtn")) {
			model.nextPlayer();
		
		} else {
			System.out.println("actionEvent not found: " + actionEvent);
		}
	}
}

/**
 * This class maps the user's actions in RiskList objects to the data and methods in the 
 * model.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
class RiskListController implements ListSelectionListener {
	
	private RiskModel model;
	private BoardView view;
	
	public RiskListController(RiskModel model, BoardView view) {
		this.model = model;
		this.view = view;
	}

	public void valueChanged(ListSelectionEvent evt) {
	
		if (evt.getValueIsAdjusting() == false) {
			
			if (view.getCountryAIndex() == -1) {
			
			} else {
				model.setCountryASelection(view.getCountryA().replaceAll("[0-9]", "").replaceAll("\\-", ""));
			}
		}
	}
}

/**
 * This class maps the user's actions in the MenuDialog to the data and methods in the model.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
class MenuController implements ActionListener {

	private RiskModel model;
	private MenuDialog view;
	
	private JFileChooser fileChooser;
	private ObjectOutputStream objectWriter;
	private ObjectInputStream objectReader;
	private BufferedReader reader;
	
	public MenuController(RiskModel model, MenuDialog view) {
	
		this.model = model;
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent evt) {
	
		String actionEvent = evt.getActionCommand();
	
		if (actionEvent.equals("returnBtn")) {
			view.dispose();
			
		} else if (actionEvent.equals("saveBtn")) {
		
			fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Java-Risk Save Files", "jrs");
			fileChooser.setFileFilter(filter);
			
			if (fileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
			
				try {
					objectWriter = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile()));
					objectWriter.writeObject(model);
					objectWriter.close();
					
				} catch (IOException e) {
					System.out.println(e.getMessage()); 
				}
			}
		} else if (actionEvent.equals("quitBtn")) {
			model.quitGame();
			
		} else {
			System.out.println("actionEvent not found: " + actionEvent);
		}
	}
}