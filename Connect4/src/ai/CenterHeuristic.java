package ai;

import gameDefs.Board;

/**
 * Heuristic that plays towards the center column as much as possible
 */
public class CenterHeuristic implements Heuristic{

	@Override
	public int evaluate(Board b, int player) {
		int sum = 0;
		int rows = b.getRows();
		int cols = b.getCols();
		
		for (int row = 0; row < rows; row++)
		{
			for (int col = 0; col < cols; col++)
			{
				if (b.getPiece(row, col) == player)
				{
					sum += (cols/2+1)-Math.abs(cols/2.0 - col);
				}
			}
		}
		
		return sum;
	}

	@Override
	public String getName() {
		
		return "Center Heuristic";
	}

}
