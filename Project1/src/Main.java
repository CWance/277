import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Christian Wance 012306864
 *
 */
public class Main {

	/**
	 * Gets a fight between an Enemy and Hero by generating an Enemy
	 * 
	 * @param h  Hero
	 * @param m  current Map
	 * @param eg EnemyGenerator
	 * @return Boolean
	 */
	public static boolean enemyRoom(Hero h, Map m, EnemyGenerator eg) {
		Enemy e = eg.generateEnemy();
		int index = 1;
		while(index < h.getLevel()) {
			index++;
			e.increaseLevel();
		}
		System.out.println("You've encountered a " + e.getName() + "\n" + e.getName() + " Lvl:" + e.getLevel() + " HP: " + e.getHP());
		if (fight(h, e)) {
			return false;
		} else if (e.getHP() <= 0) {
			System.out.println("You received a " + e.getItem().getName() + " from the enemy.");
			h.pickUpItem(e.getItem());
			m.removeCharAtLoc(h.location);
			h.map.removeCharAtLoc(h.location);
			return true;
		} else {
			return true;
		}
	}

	/**
	 * Fight between a Hero and An Enemy
	 * 
	 * @param h Hero
	 * @param e Enemy
	 * @return boolean win
	 */
	public static boolean fight(Hero h, Enemy e) {
		boolean fighting = true;
		do {
			if (h.hasMedKit()) {
				System.out.println("1. Attack\n2. Run Away\n3. Use Med Kit");
				switch (CheckInput.getIntRange(1, 3)) {
				case 1: {
					h.attack(e);
					if (e.getHP() > 0) {
						if (h.hasArmor()) {
							System.out.println(e.getName() + " attacks, but your " + h.removeFirstArmorItem()
									+ " stopped the damage and was destroyed");
						} else {
							e.attack(h);
						}
					}
					break;
				}
				case 2:
					fighting = false;
					break;
				case 3: {
					h.heal(25);
					h.removeItem("Med Kit");
					if (h.hasArmor()) {
						System.out.println(e.getName() + " attacks, but your " + h.removeFirstArmorItem()
								+ " stopped the damage and was destroyed");
					} else {
						e.attack(h);
					}
					break;
				}
				}
			} else {
				System.out.println("1. Attack\n2. Run Away");
				switch (CheckInput.getIntRange(1, 2)) {
				case 1: {
					h.attack(e);
					if (e.getHP() > 0) {
						if (h.hasArmor()) {
							System.out.println(e.getName() + " attacks, but your " + h.removeFirstArmorItem()
									+ " stopped the damage and was destroyed");
						} else {
							e.attack(h);
						}
					}
					break;
				}
				case 2:
					fighting = false;
					break;
				}
			}
		} while (h.getHP() > 0 && e.getHP() > 0 && fighting);
		return h.getHP() == 0;
	}

	/**
	 * Generates an item for a room
	 * 
	 * @param h  Hero
	 * @param m  current Map
	 * @param ig ItemGenerator
	 * @return boolean
	 */
	public static boolean itemRoom(Hero h, Map m, ItemGenerator ig) {
		Item i = ig.generateItem();
		h.map.removeCharAtLoc(h.location);
		return h.pickUpItem(i);

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("What is your name?");
		Map map = new Map();
		Hero hero = new Hero(in.nextLine(), map);
		hero.increaseLevel();
		hero.increaseLevel();
		hero.increaseLevel();
		hero.increaseLevel();
		map.loadMap(hero.getLevel());
		ItemGenerator ig = new ItemGenerator();
		boolean cont = true;
		char there = ' ';
		do {
			EnemyGenerator eg = new EnemyGenerator(ig);
			hero.display();
			System.out.println("1. Go North\n2. Go South\n3. Go East\n4. Go West\n5. Quit");
			switch (CheckInput.getIntRange(1, 5)) {
			case 1:
				there = hero.goNorth();
				break;
			case 2:
				there = hero.goSouth();
				break;
			case 3:
				there = hero.goEast();
				break;
			case 4:
				there = hero.goWest();
				break;
			case 5:
				cont = false;
				break;
			}
			if (cont) {
				boolean win = true;
				if (there == 'e') {
					win = enemyRoom(hero, map, eg);
				} else if (there == 'i') {
					itemRoom(hero, map, ig);
				} else if (there == 'f') {
					System.out.println("You have found the exit door.");
					if (hero.hasKey()) {
						System.out.println("You use your key.");
						hero.removeItem("Key");
						System.out.println("... and you go to the next room");
						hero.increaseLevel();
						System.out.println(hero.getName() + " leveled up! \nYou are now level " + hero.getLevel());
						hero.map.loadMap(hero.getLevel());
						map.loadMap(hero.getLevel());
					} else if (hero.hasHolocron()) {
						System.out.println("You use the Force");
						Random force = new Random();
						if (force.nextInt(20) + hero.getLevel() > 20) {

							System.out.println("... and you go to the next room");
							hero.increaseLevel();
							hero.map.loadMap(hero.getLevel());
						} else {

							System.out.println("... But you failed to open the door");
						}
					}
				} else if (there == 'n') {
					System.out.println("There was nothing here.");
				}

				if (win == false && hero.getHP() == 0) {
					System.out.println("You Died!");
					cont = false;
				}
			}
		} while (cont);
		in.close();
	}
}