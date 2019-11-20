import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Makes a Map class that holds 25 different characters and boolean values
 * 
 * @author Christian Wance 012306864
 *
 */
public class Map {

	/**
	 * A Char 2D array that holds the map
	 */
	private char[][] map;

	/**
	 * A Boolean 2D array that shows if the Hero moved to this spaace yet
	 */
	private boolean[][] revealed;

	/**
	 * Constructs a Map object
	 */
	Map() {
		map = new char[5][5];
		revealed = new boolean[5][5];

	}

	/**
	 * Loads a map from one of the map files
	 * 
	 * @param mapNum
	 */
	void loadMap(int mapNum) {
		int index = 0;
		String mapName = "";
		switch ((mapNum % 3)) {
		case 1:
			mapName = "Map1.txt";
			break;
		case 2:
			mapName = "Map2.txt";
			break;
		case 0:
			mapName = "Map3.txt";
			break;
		}
		try {
			Scanner read = new Scanner(new File(mapName));
			do {
				String line = read.nextLine();
				for (int j = 0; j < 5; j++) {
					map[index][j] = line.charAt(2 * j);
					revealed[index][j] = false;
				}
				index++;

			} while (read.hasNext());
			read.close();
		} catch (FileNotFoundException fnf) {
			System.out.println("File was not found");
		}
	}

	/**
	 * Gets the Character at Point p
	 * 
	 * @param p Point
	 * @return char at map[p.x][p.y]
	 */
	char getCharAtLoc(Point p) {
		return map[p.x][p.y];
	}

	/**
	 * Displays the Map
	 * 
	 * @param p Point where Hero is
	 */
	void displayMap(Point p) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == p.x && j == p.y) {
					System.out.print("*");
				} else if (revealed[i][j]) {
					System.out.print(map[i][j]);
				} else {
					System.out.print("X");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	/**
	 * Finds the start of the Map
	 * 
	 * @return Point p
	 */
	Point findStart() {
		Point p = new Point(-1, -1);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (map[i][j] == 's') {
					p.setLocation(i, j);
					revealed[i][j] = true;
				}
			}
		}
		return p;
	}

	/**
	 * Reveals the point on the map, changing the boolean value to true
	 * 
	 * @param p Point
	 */
	void reveal(Point p) {
		revealed[p.x][p.y] = true;

	}

	/**
	 * Remove the CHaracter at Point p, turning it into 'n'
	 * 
	 * @param p Point
	 */
	void removeCharAtLoc(Point p) {
		map[p.x][p.y] = 'n';
	}
}