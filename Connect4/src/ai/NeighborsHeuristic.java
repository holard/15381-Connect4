package ai;

import gameDefs.Board;

/**
 * Basic heuristic that computes the number of adjacencies among the player's
 * 		pieces. Always takes a winning move if it exists.
 */
public class NeighborsHeuristic implements Heuristic {

	@Override
	public int evaluate(Board b, int player) {
		int status = b.checkStatus();
		// Winning configuration check!
		if (status == player)
			return 100000;
		
		int sum = 0;
		int rows = b.getRows();
		int cols = b.getCols();
		for (int row = 0; row < rows; row++)
		{
			for (int col = 0; col < cols; col++)
			{
				int color = b.getPiece(row, col);
				if (color == player)
				{
					int[][] vec = {{0,1},{1,0},{1,1},{-1,1}};
					for (int[] vector : vec)
					{
						int r = row + vector[0];
						int c = col + vector[1];
						if (r >= 0 && r < rows && c >= 0 && c < cols &&
								b.getPiece(r,c) == player)
							sum += 1;
					}
				}
			}
		}
		
		return sum;
	}

	@Override
	public String getName() {
		
		return "Nieghbors Heuristic";
	}

}
