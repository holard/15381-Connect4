package gameDefs;

/**
 * Controller object of the Connect Four game.
 * Manages a Board object, and calls getMove on the two players
 */
public class Game {
	private Player p0;
	private Player p1;
	private Board board;
	private int turn;
	private int status;
	
	/**
	 * Constructor for a Game with no players.
	 */
	public Game() {
		startOver();
	}
	
	/**
	 * Constructor for a Game with given players.
	 * @param p0
	 * @param p1
	 */
	public Game(Player p0, Player p1) {
		this.p0 = p0;
		this.p1 = p1;
		startOver();
	}
	
	/**
	 * Registers a given player as p0 or p1 based on spot
	 * @param p
	 * @param spot
	 */
	public void registerPlayer(Player p, int spot)
	{
		if (spot == 0)
			p0 = p;
		else
			p1 = p;
	}

	/**
	 * @return   the underlying board
	 */
	public Board getBoard()
	{
		return board;
	}
	
	/**
	 * Performs a single turn and updates the game status
	 */
	public void step()
	{
		if (status > -1)
			return;
		
		if (turn == 0)
		{
			int move = p0.getMove(board, 0);
			board.makeMove(move, 0);
		} else
		{
			int move = p1.getMove(board, 1);
			board.makeMove(move, 1);
		}
		
		turn = 1-turn;
		
		status = board.checkStatus();
		return;
	}
	
	/**
	 * @return   -1 if the game is over, the player # otherwise.
	 */
	public int gameStatus()
	{
		return status;
	}
	
	public void startOver()
	{
		board = new Board();
		turn = 0;
		status = -1;
	}
}
