import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * A Hero is an Entity that is the player, can have force powers
 * 
 * @author Christian Wance 012306864
 *
 */
public class Hero extends Entity implements Force {
	private ArrayList<Item> items;
	public Map map;
	public Point location;

	/**
	 * Generates the Hero Entity
	 * 
	 * @param n String name of the Hero
	 * @param m Map The map used by the hero
	 */
	Hero(String n, Map m) {
		super(n, 1, 15);
		items = new ArrayList<Item>();
		map = m;
		map.loadMap(getLevel());
		location = map.findStart();
	}

	/**
	 * Override the attack function found in Entity
	 * 
	 * @param e Entity The Entity being attacked
	 */
	@Override
	void attack(Entity e) {
		Random damage = new Random();
		if (hasHolocron()) {
			System.out.println("1. Use Blaster\n2. Use Force");
			switch (CheckInput.getIntRange(1, 2)) {
			case 1: {
				int dam = damage.nextInt(getLevel() + 1 * damage.nextInt(getLevel())) + 1;
				e.takeDamage(dam);
				System.out.println(getName() + " attacks " + e.getName() + " for " + dam + " damage.");
				break;
			}
			case 2: {
				String forceMenu = FORCE_MENU;
				System.out.println(forceMenu);
				switch (CheckInput.getIntRange(1, 3)) {
				case 1: {
					int dam = forcePush();
					e.takeDamage(dam * e.getLevel());
					System.out.println(
							getName() + " hits " + e.getName() + "  with a Force Push for " + dam + " damage.");
					break;
				}
				case 2: {
					int dam = forceChoke();
					e.takeDamage(dam * e.getLevel());
					System.out.println(
							getName() + " hits " + e.getName() + "  with a Force Choke for " + dam + " damage.");
					break;
				}
				case 3: {
					int dam = forceSlam();
					e.takeDamage(dam * e.getLevel());
					System.out.println(
							getName() + " hits " + e.getName() + "  with a Force Slam for " + dam + " damage.");
					break;
				}
				}
				break;
			}

			}
		} else {
			int dam = damage.nextInt(getLevel() + 1 * damage.nextInt(getLevel())) + 1;
			e.takeDamage(dam);
			System.out.println(getName() + " attacks " + e.getName() + " for " + dam + " damage.");

		}

	}

	/**
	 * Used to use forcePush Attack
	 */
	@Override
	public int forcePush() {
		Random att = new Random();
		return att.nextInt(5) + 1;
	}

	/**
	 * Used to use forceChoke Attack
	 */
	@Override
	public int forceChoke() {
		Random att = new Random();
		return att.nextInt(10) + 1;
	}

	/**
	 * Used to use forceSlam Attack
	 */
	@Override
	public int forceSlam() {
		Random att = new Random();
		return att.nextInt(15) + 1;
	}

	/**
	 * Used to display The Hero and the Map
	 */
	@Override
	public void display() {
		super.display();
		displayItems();
		map.displayMap(location);
	}

	/**
	 * Displays all of the items held by the Hero
	 */
	public void displayItems() {
		for (int i = 0; i < getNumItems(); i++) {
			System.out.println(i + 1 + ". " + items.get(i).getName());
		}
	}

	/**
	 * Gets the number of Items held by the Hero
	 * 
	 * @return items.size()
	 */
	public int getNumItems() {
		return items.size();
	}

	/**
	 * Picks up an Item
	 * 
	 * @param i Item
	 * @return Boolean
	 */
	public boolean pickUpItem(Item i) {
		if (getNumItems() >= 5) {
			System.out.println(
					"Your inventory is full.\n Do you want to get rid of an another item for " + i.getName() + "?");
			System.out.println("1. Yes\n2. No");
			switch (CheckInput.getIntRange(1, 2)) {
			case 1: {
				System.out.println("What do you want to get rid of?");
				displayItems();
				int choose = CheckInput.getIntRange(1, 5);
				System.out.println("You drop a " + items.get(choose).getName());
				System.out.println("You pick up a " + i.getName());
				items.set(choose - 1, i);

				break;
			}
			case 2: {
				System.out.println("You drop a " + i.getName());
				return false;
			}
			}

		} else {
			System.out.println("You pick up a " + i.getName());

			items.add(i);
		}
		return true;
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public Item removeItem(Item i) {
		Item e = items.get(0);
		for (int j = 0; j < getNumItems(); j++) {
			if (i.getName().equals(items.get(j).getName())) {
				e = items.get(j);
				items.remove(i);
			}
		}
		return e;
	}

	/**
	 * Removes an item using a String n
	 * 
	 * @param n String name of the item
	 * @return Item
	 */
	public Item removeItem(String n) {
		Item i = items.get(0);
		for (int j = 0; j < getNumItems(); j++) {
			if (n.equals(items.get(j).getName())) {
				items.remove(j);
			}
		}
		return i;
	}

	/**
	 * Removes a piece of armor
	 * 
	 * @return String armor
	 */
	public String removeFirstArmorItem() {
		boolean found = true;
		int i = 0;
		String armor = " ";
		while (i < items.size() && found) {
			if (items.get(i).getName().equals("Helmet") || items.get(i).getName().equals("Shield")
					|| items.get(i).getName().equals("Chestplate")) {
				armor = items.get(i).getName();
				items.remove(i);
				found = false;
			}
			i++;
		}
		return armor;
	}

	/**
	 * Checks if the Hero has a med kit
	 * 
	 * @return boolean
	 */
	public boolean hasMedKit() {
		boolean medKit = false;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equals("Med Kit")) {
				medKit = true;
			}
		}
		return medKit;
	}

	/**
	 * Checks if the Hero has a holocron
	 * 
	 * @return boolean
	 */
	public boolean hasHolocron() {
		boolean holocron = false;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equals("Holocron")) {
				holocron = true;
			}
		}
		return holocron;
	}

	/**
	 * Checks if the Hero has a key
	 * 
	 * @return boolean
	 */
	public boolean hasKey() {
		boolean key = false;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equals("Key")) {
				key = true;
			}
		}
		return key;
	}

	/**
	 * Checks if the Hero has any armor
	 * 
	 * @return boolean
	 */
	public boolean hasArmor() {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equals("Helmet")) {
				return true;
			} else if (items.get(i).getName().equals("Shield")) {
				return true;
			} else if (items.get(i).getName().equals("Chestplate")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sends your Hero north on the map
	 * 
	 * @return char at point
	 */
	public char goNorth() {
		char there = ' ';
		if (location.x == 0) {
			System.out.println("Cannot go North");
		} else {
			there = map.getCharAtLoc(new Point(location.x - 1, location.y));
			map.reveal(location);
			location.setLocation(location.x - 1, location.y);

		}

		return there;

	}

	/**
	 * Sends your Hero south on the map
	 * 
	 * @return char at point
	 */
	public char goSouth() {
		char there = ' ';
		if (location.x == 4) {
			System.out.println("Cannot go South");
		} else {
			there = map.getCharAtLoc(new Point(location.x + 1, location.y));
			map.reveal(location);
			location.setLocation(location.x + 1, location.y);
		}

		return there;
	}

	/**
	 * Sends your Hero west on the map
	 * 
	 * @return char at point
	 */
	public char goWest() {
		char there = ' ';
		if (location.y == 0) {
			System.out.println("Cannot go West");
		} else {
			there = map.getCharAtLoc(new Point(location.x, location.y - 1));
			map.reveal(location);
			location.setLocation(location.x, location.y - 1);
		}

		return there;
	}

	/**
	 * Sends your Hero east on the map
	 * 
	 * @return char at point
	 */
	public char goEast() {
		char there = ' ';
		if (location.y == 4) {
			System.out.println("Cannot go East");
		} else {
			there = map.getCharAtLoc(new Point(location.x, location.y + 1));
			map.reveal(location);
			location.setLocation(location.x, location.y + 1);
		}

		return there;
	}
}
