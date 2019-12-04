import java.util.ArrayList;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Generates items from a list, which was gathered froma file
 * 
 * @author Christian Wance 012306864
 *
 */
public class ItemGenerator{
	/**
	 * An ArrayList of Items that come from a file
	 */
	
	private ArrayList<Item> itemList = new ArrayList<Item>();
	//An instance of Item 
	private static ItemGenerator instance = null;
	
	/**
	 * Creates the ItemGenerator object, populates the itemList with data from
	 * ItemList.txt
	 */
	private ItemGenerator() {
		try {
			Scanner read = new Scanner(new File("ItemList.txt"));
			do {
				String line = read.nextLine();
				itemList.add(new Item(line));
			} while (read.hasNext());
			read.close();
		} catch (FileNotFoundException fnf) {
			System.out.println("File was not found");
		}
	}
	public static ItemGenerator getInstance( ) {
		if( instance == null ) {
			instance = new ItemGenerator( );
		}
		return instance;
	}
		

	/**
	 * Picks an Item randomly from the itemList and returns it
	 * 
	 * @return Item
	 */
	Item generateItem() {
		Random randomItem = new Random();
		Item item = itemList.get(randomItem.nextInt(itemList.size())).clone();
		return item;
	}
	
}
