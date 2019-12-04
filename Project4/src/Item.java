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
	public Item(String n) {
		name = n;
	}
	/**
	 * Creates the Item object
	 * 
	 * @param n the name of the Item
	 */
	public Item(Item i) {
		name = i.getName();
	}
	/**
	 * Returns the variable name
	 * 
	 * @return name
	 */
	String getName() {
		return name;
	}
	
	public Item clone() {
		return new Item(this);
		
	};
}
