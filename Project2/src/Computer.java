
/**
 * The Computer oponent. It guesses your next move based on you previous choices
 * @author Christian Wance 012306864
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Computer {
	/**
	 * Holds the patterns
	 */
	private HashMap<Pattern, Integer> patterns;

	/**
	 * Constructor for Computer class
	 */
	public Computer() {
		patterns = new HashMap<>();
	}

	/**
	 * Makes a prediction based on previous choices, and if no suitable choices
	 * exist, then makes a random guess
	 * 
	 * @param p
	 * @return Character next
	 */
	public char makePrediction(String p) {
		char next = ' ';
		int high = 0;
		for (int i = 5; i <= p.length(); i += 2) {
			Pattern p1 = new Pattern(p.substring(0, i) + ",r");
			Pattern p2 = new Pattern(p.substring(0, i) + ",p");
			Pattern p3 = new Pattern(p.substring(0, i) + ",s");
			if (patterns.containsKey(p1)) {
				if (high < patterns.get(p1)) {
					high = patterns.get(p1);
					next = 'p';
				}
			}
			if (patterns.containsKey(p2)) {
				if (high < patterns.get(p2)) {
					high = patterns.get(p2);
					next = 's';
				}
			}
			if (patterns.containsKey(p3)) {
				if (high < patterns.get(p3)) {
					high = patterns.get(p3);
					next = 'r';
				}
			}

		}
		if (high == 0) {
			Random rand = new Random();
			switch (rand.nextInt(3)) {
			case 0:
				next = 'r';
				break;
			case 1:
				next = 'p';
				break;
			case 2:
				next = 's';
				break;
			}
		}
		return next;
	}

	/**
	 * Stores a pattern into the HashMap
	 * 
	 * @param p A String pattern to place into the map, and if already there then
	 *          increase amount by one
	 */
	public void storePattern(String p) {
		for (int i = 7; i <= p.length(); i += 2) {

			Pattern pattern = new Pattern(p.substring(0, i));
			if (!patterns.containsKey(pattern)) {
				patterns.put(pattern, 1);
			} else {
				patterns.replace(pattern, patterns.get(pattern) + 1);

			}
		}
	}

	/**
	 * Saves the HashMap to a file
	 * 
	 * @param f File f
	 */
	public void saveMapToFile(File f) {
		try {
			PrintWriter writer = new PrintWriter(f);
			for (Pattern p : patterns.keySet()) {
				writer.println(p.getPattern() + ":" + patterns.get(p));
			}
			writer.close();
		} catch (FileNotFoundException fnf) {
			System.out.println("File was not found");
		}
	}

	/**
	 * Reads the file and fills the HashMap with its contents
	 * 
	 * @param f
	 * @return
	 */
	public String readFile(File f) {
		String response = "";
		int high = 0;
		try {
			Scanner read = new Scanner(f);
			do {
				String line = read.nextLine();
				String[] lines = line.split(":");
				patterns.put(new Pattern(lines[0]), Integer.parseInt(lines[1]));
				if (Integer.parseInt(lines[1]) > high) {
					response = lines[0];
				}
			} while (read.hasNext());
			read.close();
		} catch (FileNotFoundException fnf) {
			response = "File was not found";
		}

		return response;

	}
}
