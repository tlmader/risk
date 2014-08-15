import java.util.Observer;
import java.util.Observable;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

/**
 * Allows the creation of JLists with updating strings from the RiskModel.
 * This class allows the creation of lists that stay updated to the lists of current 
 * player's hand, occupied countries, and countries adjacent to the selected, occupied 
 * country.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
public class RiskListModel extends DefaultListModel implements Observer {

	private int i;
	private String type;
	private String display;
	private ArrayList<String> stringList;
    private RiskModel model;
	
    public RiskListModel (RiskModel model, String type) {
	
        super();
        this.model = model;
		this.type = type;
    }
	
	// Updates the state of the RiskList
	public void update(Observable obs, Object obj) {
		
		// System.out.println("Refreshing...");
		
		display = (String)obj;
		
		if (type == "cards" && type == display) {
		
			// System.out.println("Notified cards list.");
			
			removeAllElements();
			
			for (i = 0; i < model.getCardsList().size(); i++) {
			
				addElement(model.getCardsList().get(i));
			}
			
		} else if (type == "countryA" && type == display) {
		
			// System.out.println("Notified country A list.");
			
			removeAllElements();
			
			for (i = 0; i < model.getCountryAList().size(); i++) {
			
				addElement(model.getCountryAList().get(i));
			}
			
		} else if (type == "countryB" && type == display) {
		
			// System.out.println("Notified country B list.");
			
			removeAllElements();
			
			for (i = 0; i < model.getCountryBList().size(); i++) {
			
				addElement(model.getCountryBList().get(i));
			}
		} else {
		
		}
	}
}