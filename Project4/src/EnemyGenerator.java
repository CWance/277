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
	private static EnemyGenerator instance = null;
	/**
	 * An ItemGenerator that gives an enemy an item
	 */
	private ItemGenerator ig;

	/**
	 * Creates the EnemyGenerator object, populates the enemyList with data from
	 * EnemyList.txt
	 */
	private EnemyGenerator(ItemGenerator ig) {
		this.ig = ig;
	}

	public static EnemyGenerator getInstance(ItemGenerator ig) {
		if (instance == null) {
			instance = new EnemyGenerator(ig);
		}
		return instance;
	}

	/**
	 * Picks an Enemy randomly from the enemyList and returns it
	 * 
	 * @return enemy
	 */
	Enemy generateEnemy(int lvl) {
		Random randomEnemy = new Random();
		int index = randomEnemy.nextInt(8);
		Enemy enemy = null;
		switch (index) {
			case 0: {
				enemy = new Rodian(lvl, ig.generateItem());
				for(int i = 1; i < lvl; i++) {
					enemy = new Fighter(enemy);
				}
				break;
			}
			case 1: {
				enemy = new Rodian(lvl, ig.generateItem());

				for(int i = 1; i < lvl; i++) {

					enemy = new ForceUser(enemy);
				}
				break;
			}
			case 2: {
				enemy = new Geonosian(lvl, ig.generateItem());

				for(int i = 1; i < lvl; i++) {

					enemy = new Fighter(enemy);
				}
				break;
			}
			case 3: {
				enemy = new Geonosian(lvl, ig.generateItem());

				for(int i = 1; i < lvl; i++) {

					enemy = new ForceUser(enemy);
				}
				break;
			}
			case 4: {
				enemy = new Dathomiri(lvl, ig.generateItem());

				for(int i = 1; i < lvl; i++) {

					enemy = new Fighter(enemy);
				}
				break;
			}
			case 5: {
				enemy = new Dathomiri(lvl, ig.generateItem());

				for(int i = 1; i < lvl; i++) {

					enemy = new ForceUser(enemy);
				}
				break;
			}
			case 6: {
				enemy = new Twilek(lvl, ig.generateItem());

				for(int i = 1; i < lvl; i++) {

					enemy = new Fighter(enemy);
				}
				break;
			}
			case 7: {
				enemy = new Twilek(lvl, ig.generateItem());

				for(int i = 1; i < lvl; i++) {

					enemy = new ForceUser(enemy);
				}
				break;
			}
		}

		return enemy;
	}
}
