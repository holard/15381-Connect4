package gameDefs;

import java.util.ArrayList;
import java.util.List;

import ai.Pair;

/**
 * Represents the state of a Connect Four game.
 * The pieces are represented as integers (-1, 0, 1) where -1 corresponds
 * 		to an empty square.
 * The board's underlying array is immutable, and makeMove generates a
 * 		copy of the original, applies the move, then returns the result.
 */
public class Board {
	
	/**
	 * Default dimensions of the board
	 */
	public static final int DEFAULT_WIDTH = 7;
	public static final int DEFAULT_HEIGHT = 6;
	
	private int rows;
	private int cols;
	/**
	 * Array of pieces (ints), stored in row-major order.
	 */
	private int[][] pieces;
	
	/**
	 * Default constructor, instantiates the board to default dimensions.
	 */
	public Board()
	{
		cols = DEFAULT_WIDTH;
		rows = DEFAULT_HEIGHT;
		
		pieces = new int[rows][cols];
		if (pieces.length > 0)
		{
			for (int i = 0; i < pieces.length; i++)
				for (int j = 0; j < pieces[0].length; j++)
					pieces[i][j] = -1;
		}
	}
	
	/**
	 * A private copy constructor 
	 * @param array
	 */
	private Board(int[][] array)
	{
		cols = 0;
		rows = array.length;
		if (rows > 0)
			cols = array[0].length;
		
		pieces = new int[rows][cols];
		if (pieces.length > 0)
		{
			for (int i = 0; i < pieces.length; i++)
				for (int j = 0; j < pieces[0].length; j++)
					pieces[i][j] = array[i][j];
		}
	}
	
	/**
	 * Retrieves the piece at the given location.
	 * @param row
	 * @param col
	 * @return  the piece at [row][col].
	 */
	public int getPiece(int row, int col)
	{
		return pieces[row][col];
	}
	
	/**
	 * Returns whether or not [row][col] is empty
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean isEmpty(int row, int col)
	{
		return pieces[row][col] == -1;
	}
	
	/**
	 * @param col
	 * 		the column to play in
	 * @param player
	 * 		the player that is playing the move.
	 * @return
	 * 		The resulting (new) board, or null if illegal move.
	 */
	public Board makeMove(int col, int player)
	{
		if (!isEmpty(0,col))
			return null;
		
		Board copy = new Board(pieces);
		copy.addPiece(col,player);
		return copy;
	}
	
	/**
	 * Returns a list of Board objects that represent neighboring states
	 * @param player
	 * 		The player making the next move
	 * @return		The list of neighbors
	 */
	public List<Board> neighbors(int player)
	{
		List<Board> ret = new ArrayList<Board>();
		
		List<Integer> moves = getLegalMoves();
		for (int i : moves)
		{
			ret.add(makeMove(i,player));
		}
		
		return ret;
	}
	
	/**
	 * @param col
	 * 		the column to play in
	 * @param player
	 * 		the player making the move
	 */
	private void addPiece(int col, int player)
	{
		int row = 0;
		while (row < rows-1 && isEmpty(row+1,col))
			row += 1;
		pieces[row][col] = player;
	}
	
	/**
	 * Finds legal columns for making moves
	 * @return   a List object of legal columns
	 */
	public List<Integer> getLegalMoves()
	{
		ArrayList<Integer> moves = new ArrayList<Integer>();
		for (int col = 0; col < cols; col++)
		{
			if (isEmpty(0,col))
				moves.add(col);
		}
		return moves;
	}
	
	/**
	 * @return the status of the game: -1 for in-game, 0/1 for winners
	 */
	public int checkStatus()
	{
		int[][] vec = {{0,1},{1,0},{1,1},{-1,1}};
		
		for (int row = 0; row < rows; row++)
		{
			for (int col = 0; col < cols; col++)
			{
				if (pieces[row][col] == -1)
					continue;
				
				for (int[] vector : vec)
				{
					int r = row;
					int c = col;
					int x = vector[0];
					int y = vector[1];
					int count = 0;
					int color = pieces[r][c];
					while (r < rows && c >= 0 && c < cols && 
							color == pieces[r][c])
					{
							count += 1;
							r = r+y;
							c = c+x;
					}
					if (count >= 4)
					{
						return color;
					}
				}
			}
		}
		boolean tied = true;
		for (int i = 0; i < cols; i++)
		{
			tied = tied && (pieces[0][i] > -1);
		}
		if (tied)
			return 2;
		return -1;
	}
	
	public int getCols()
	{
		return cols;
	}
	public int getRows()
	{
		return rows;
	}
	
	@Override
	public int hashCode() {
		int sum = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				sum += pieces[i][j] + 1;
				sum *= 3;
				sum %= 500000000;
			}
		}
		
		return sum;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Board)) {
			return false;
		}
		Board oth = (Board) other;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (pieces[i][j] != oth.pieces[i][j])
					return false;
			}
		}
		return true;
	}
}
