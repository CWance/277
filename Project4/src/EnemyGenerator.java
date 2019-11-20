import java.util.ArrayList;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Generates Enemy from a list, pulled from a file
 * 
 * @author Christian Wance 012306864
 *
 */
public class EnemyGenerator {
	/**
	 * The ArrayList of Enemy objects from a file
	 */
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	
	/**
	 * An ItemGenerator that gives an enemy an item
	 */
	private ItemGenerator ig;

	/**
	 * Creates the EnemyGenerator object, populates the enemyList with data from
	 * EnemyList.txt
	 */
	EnemyGenerator(ItemGenerator ig) {
		this.ig = ig;
		try {
			Scanner read = new Scanner(new File("EnemyList.txt"));
			do {
				String line = read.nextLine();
				String[] enemy = line.split(",");
				if (enemy[2].equals("n")) {
					enemyList.add(new Enemy(enemy[0], 1, (int) (enemy[1].charAt(0)) - 48, this.ig.generateItem()));
				} else {
					enemyList.add(new ForceEnemy(enemy[0], 1, (int) (enemy[1].charAt(0)) - 48, this.ig.generateItem()));
				}
			} while (read.hasNext());
			read.close();
		} catch (FileNotFoundException fnf) {
			System.out.println("File was not found");
		}

	}

	/**
	 * Picks an Enemy randomly from the enemyList and returns it
	 * 
	 * @return enemy
	 */
	Enemy generateEnemy() {
		Random randomEnemy = new Random();
		int index = randomEnemy.nextInt(enemyList.size());
		String eName = enemyList.get(index).getName();
		int eLevel = enemyList.get(index).getLevel();
		int eMaxHP = enemyList.get(index).getMaxHP();
		
		Enemy enemy = new Enemy(eName,eLevel,eMaxHP, ig.generateItem());
		return enemy;
	}
}
