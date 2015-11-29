package ai;

import gameDefs.Board;

public interface Heuristic {
	/**
	 * @param b
	 * 		The board to evaluate
	 * @param player
	 * 		The player's number
	 * @return		An integer denoting the value of this state.
	 */
	int evaluate(Board b, int player);
	
	/**
	 * @return  The name of the heuristic
	 */
	String getName();
}
