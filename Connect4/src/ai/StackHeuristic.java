package ai;

import gameDefs.Board;

public class StackHeuristic implements Heuristic {

	@Override
	public int evaluate(Board b, int player) {
		int tallest = 0;
		
		for (int col = 0; col < b.getCols(); col++)
		{
			int sum = 0;
			int row = b.getRows() - 1;
			while (row >= 0 && b.getPiece(row, col) == player)
			{
				sum += 1;
				row -= 1;
			}
			if (sum > tallest)
				tallest = sum;
		}
		
		return tallest;
	}

	@Override
	public String getName() {
		return "Stack Heuristic";
	}

}
