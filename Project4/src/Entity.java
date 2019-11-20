/**
 * The Entity class holds the basic data for all Enemy and Hero classes
 * 
 * @author Christian Wance 012306864
 *
 */
abstract class Entity {
	/**
	 * A String that holds the name of the Entity
	 */
	private String name;
	
	/**
	 * An Integer that holds the Entity's level
	 */
	private int level;
	
	/**
	 * An Integer that holds the Entity's Max Hp
	 */
	private int maxHp;
	
	/**
	 * An Integer that holds the Entity's current Hp
	 */
	private int hp;

	/**
	 * Creates the Entity object
	 * 
	 * @param n the name of the Entity
	 * @param l the level of the Entity
	 * @param m the max hit points of the Entity
	 */
	Entity(String n, int l, int m) {
		name = n;
		level = l;
		maxHp = m;
		hp = m;
	}

	/**
	 * Entity attacks another Entity e
	 * 
	 * @param e Entity
	 */
	abstract void attack(Entity e);

	/**
	 * Returns the variable name
	 * 
	 * @return name
	 */
	String getName() {
		return name;
	}

	/**
	 * Returns the variable level
	 * 
	 * @return level
	 */
	int getLevel() {
		return level;
	}

	/**
	 * Returns the variable hp
	 * 
	 * @return hp
	 */
	int getHP() {
		return hp;
	}

	/**
	 * Returns the variable maxHp
	 * 
	 * @return maxHp
	 */
	int getMaxHP() {
		return maxHp;
	}

	/**
	 * Increases the variable level by one
	 */
	void increaseLevel() {
		level++;
	}

	/**
	 * Increases the variable hp by parameter h, up to maxHp
	 * 
	 * @param h The number of hp points to increase by
	 */
	void heal(int h) {
		if (hp + h > maxHp) {
			hp = maxHp;
		} else {
			hp += h;
		}
	}

	/**
	 * Decreases the variable hp by parameter h, down to 0
	 * 
	 * @param h The number of hp points to decrease by
	 */
	void takeDamage(int h) {
		if (hp - h < 0) {
			hp = 0;
		} else {
			hp -= h;
		}
	}

	/**
	 * Increases the maxHp by parameter h
	 * 
	 * @param h The number of maxHp points to increase by
	 */
	void increaseMaxHP(int h) {
		maxHp += h;
	}

	/**
	 * Decrease the maxHp by parameter h
	 * 
	 * @param h The number of maxHp points to decrease by
	 */
	void decreaseMaxHP(int h) {
		if (maxHp - h < 0) {
			maxHp = 0;
		} else {
			maxHp -= h;
		}
	}

	/**
	 * Display the information of the Entity object
	 */
	void display() {
		System.out.println(name + " Lvl:" + level);
		System.out.println("HP: " + hp + "/" + maxHp);
	}
}
