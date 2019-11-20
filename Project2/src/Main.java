import java.io.File;
import java.util.Random;

/**
 * 
 * @author Christian Wance 012306864 This is the main of the progrem. It loops
 *         through the options, allow saving loading of a file.
 */
public class Main {
	public static void main(String[] args) {
		// counts the wins
		int win = 0;
		// counts the losses
		int loss = 0;
		// counts the ties
		int tie = 0;
		Computer com = new Computer();
		File f = new File("Patterns.txt");
		char[] p = new char[3];
		char play = ' ';
		boolean cont = true;
		System.out.println("Load file?");
		System.out.println("Yes\nNo");
		if (CheckInput.getYesNo()) {
			if (f.length() != 0) {
				String[] pop = com.readFile(f).split(",");
				for (int i = 0; i < pop.length-1; i++) {
					p[i] = pop[i].charAt(0);
				}
			}
		}
		char comPlay = ' ';
		String playerChoice = "";
		String comChoice = "";
		do {
			System.out.println("1. Play\n2. Quit");

			if (CheckInput.getIntRange(1, 2) == 1) {
				System.out.println("Rock, Paper, or Scissors?");
				System.out.println("1. Rock\n2. Paper\n3. Scissors");

				switch (CheckInput.getIntRange(1, 3)) {
				case 1: {
					play = 'r';
					playerChoice = "Rock";
					break;
				}
				case 2: {
					play = 'p';
					playerChoice = "Paper";
					break;
				}
				case 3: {
					play = 's';
					playerChoice = "Scisors";
					break;
				}
				}
				String makePred = "" + p[0];
				for (int j = 1; j < p.length; j++) {
					if (p[j] != 0) {
						makePred = makePred + "," + p[j];
					}
				}
				comPlay = com.makePrediction(makePred);
				System.out.println("You threw a " + playerChoice);

				switch (comPlay) {
				case 'r':
					comChoice = "Rock";
					break;
				case 'p':
					comChoice = "Paper";
					break;
				case 's':
					comChoice = "Scisors";
					break;
				}
				System.out.println("and the Computer threw a " + comChoice);

				if ((comPlay == 'r' && play == 'p') || (comPlay == 'p' && play == 's')
						|| (comPlay == 's' && play == 'r')) {
					System.out.println("You Win");
					win++;
				} else if ((comPlay == 'r' && play == 's') || (comPlay == 'p' && play == 'r')
						|| (comPlay == 's' && play == 'p')) {
					System.out.println("You Lose");
					loss++;
				} else {
					tie++;
					System.out.println("You Tied");
				}
				for (int j = p.length - 1; j > 0; j--) {
					p[j] = p[j - 1];
				}
				p[0] = play;
				if (makePred.length() >= 3) {
					makePred = makePred + "," + play;
					com.storePattern(makePred);
				}

				System.out.println("Wins:\nPlayer: " + win + " || Losses: " + loss + "|| Ties: " + tie);
				System.out.println("Win %: " + (double) ((double) win / (win + loss + tie)) * 100 + "%");
			} else {
				cont = false;
			}
		} while (cont);
		System.out.println("Save file?");
		System.out.println("Yes\nNo");
		if (CheckInput.getYesNo()) {
			com.saveMapToFile(f);
		}
	}
}
