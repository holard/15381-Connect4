package ai;

import java.util.List;

import gameDefs.Board;

/**
 * Basic heuristic that computes the sume of line-lengths (roughly squared).
 */
public class SquaredLinesHeuristic implements Heuristic {

	@Override
	public int evaluate(Board b, int player) {
		int status = b.checkStatus();
		// Winning configuration check!
		if (status == player)
			return 100000;
		List<Board> neighbors = b.neighbors(1-player);
		for (Board nb : neighbors)
		{
			if (nb.checkStatus() == 1-player)
				return -100000;
		}
		
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
						int r = row;
						int c = col;
						while (r >= 0 && r < rows && c >= 0 && c < cols &&
								b.getPiece(r,c) == player)
						{
							sum += 1;
							r = r + vector[0];
							c = c + vector[1];
						}
					}
				}
			}
		}
		
		return sum;
	}

	@Override
	public String getName() {
		
		return "Squared Lines Heuristic";
	}

}
