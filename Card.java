/**
 * Allows the creation of Risk Card objects.
 * @author Ted Mader
 * @version Alpha
 * @date 5/02/14
 **/
public final class Card {

    private final String type;
    private final Country country;

    public Card( String type, Country country ) {
		this.type = type;
		this.country = country;
    }
	
	public String getName() {
		return country.getName() + ", " + type;
	}

    public String getType() {
		return type;
    }

    public Country getCountry() {
		return country;
    }
}
