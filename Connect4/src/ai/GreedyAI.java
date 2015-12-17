package ai;

import java.util.ArrayList;
import java.util.List;

import gameDefs.Board;
import gameDefs.Player;

/**
 * Looks at all adjacent states and picks the best one according to the
 * 		given heuristic.
 */
public class GreedyAI implements Player{

	Heuristic heuristic;
	
	public GreedyAI(Heuristic h)
	{
		heuristic = h;
	}
	
	@Override
	public int getMove(Board b, int color) {
		List<Integer> moves = b.getLegalMoves();
		int best = -1;
		int bestmove = -1;
		for (Integer i : moves)
		{
			Board ni = b.makeMove(i, color);
			if (bestmove == -1)
			{
				bestmove = i;
				best = heuristic.evaluate(ni, color);
				continue;
			}
			int val = heuristic.evaluate(ni, color);
			if (val > best)
			{
				best = val;
				bestmove = i;
			}
			
		}
		
		return bestmove;
	}

	@Override
	public String getName() {
		return "Greedy AI Player using the " + heuristic.getName();
	}
	
	@Override
	public void gameOver(int status) {
		return;
	}
}
