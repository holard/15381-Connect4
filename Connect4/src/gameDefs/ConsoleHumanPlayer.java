package gameDefs;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Simple human-input player that prompts users to make the next move on the
 * console (System.in).
 */
public class ConsoleHumanPlayer implements Player {

	@Override
	public int getMove(Board b, int color) {
		Scanner scan = new Scanner(System.in);
		int cols = b.getCols();
		System.out.println("Please enter a column 0 ~ " + cols + " for player "
				+ color + ": ");
		int move = 0;
		while (true) {
			try {
				move = scan.nextInt();
				return move;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid integer 0 ~ " + cols
						+ " for player " + color + ": ");
				continue;
			}
		}
	}

	@Override
	public String getName() {
		return "Console-based Human Player";
	}

}
