package gameDefs;

/**
 * Represents a player of the game, can be AI or human.
 */
public interface Player {
	/**
	 * Gets the next move for the player, given a Board and the 
	 *     player's color.
	 * @param b
	 * @param color
	 * @return   the column to play in.
	 */
	int getMove(Board b, int color);
	
	/**
	 * @return  the Player's name
	 */
	String getName();
}
