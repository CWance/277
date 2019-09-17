/**
 * This object holds A Item to be used
 * 
 * @author Christian Wance 012306864
 *
 */
public class Item {
	/**
	 * A String that holds the name of the item
	 */
	private String name;

	/**
	 * Creates the Item object
	 * 
	 * @param n the name of the Item
	 */
	Item(String n) {
		name = n;
	}

	/**
	 * Returns the variable name
	 * 
	 * @return name
	 */
	String getName() {
		return name;
	}
}
