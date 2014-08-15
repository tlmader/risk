import java.util.Observer;
import java.util.Observable;
import java.util.ArrayList;

import javax.swing.JLabel;

/**
 * Allows the creation of JLists with updating strings from the RiskModel.
 * This class allows the creation of lists that stay updated to the lists of current 
 * player's hand, occupied countries, and countries adjacent to the selected, occupied 
 * country.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
public class CountryLabel extends JLabel implements Observer {

	private int armies;
	private String name;
	private String display;
	private ArrayList<String> stringList;
    private RiskModel model;
	private Country country;
	
    public CountryLabel (RiskModel model, Country country) {
	
        super();
        this.model = model;
		this.country = country;
		name = country.getName();
		setText(name);
    }
	
	// Updates the state of the RiskList
	public void update(Observable obs, Object obj) {
		
		display = (String) obj;
		
		if (display.equals("countryA")) {
			if (country.getOccupant() == null) {
				setText(name);
			} else {
				setText(name + ": " + country.getArmies() + ", " + country.getOccupant().getName());
			}
		}
	}
}