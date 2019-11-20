import java.util.Random;

/**
 * A Enemy is an Entity that attacks the player
 * 
 * @author Christian Wance 012306864
 *
 */
class Enemy extends Entity {

	/**
	 * An Item held by the Enemy
	 */
	private Item item;

	/**
	 * Generates an Enemy Entity
	 * 
	 * @param n The Enemy name
	 * @param l The Enemy level
	 * @param m The Enemy maxHp
	 * @param i The Enemy held item
	 */
	Enemy(String n, int l, int m, Item i) {
		super(n, l, m);
		item = i;
	}

	/**
	 * Override the attack function found in Entity
	 * 
	 * @param e Entity The Entity being attacked
	 */
	@Override
	void attack(Entity e) {
		Random damage = new Random();
		int dam = damage.nextInt(getLevel() + 1 * damage.nextInt(getLevel())) + 1;
		e.takeDamage(dam);
		System.out.println(getName() + " attacks " + e.getName() + " for " + dam + " damage.");
	}

	/**
	 * Gets the item held by the enemy
	 * 
	 * @return Item
	 */
	Item getItem() {
		return item;
	}
}
