package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gameDefs.Board;
import gameDefs.Player;

/**
 * Looks at all adjacent states and picks the best one according to the
 * 		given heuristic.
 * Selects randomly from equally good choices.
 */
public class RandomizedGreedyAI implements Player{

	Heuristic heuristic;
	
	public RandomizedGreedyAI(Heuristic h)
	{
		heuristic = h;
	}
	
	@Override
	public int getMove(Board b, int color) {
		List<Integer> moves = b.getLegalMoves();
		List<Integer> bestmoves = new ArrayList<Integer>();
		int best = -1;
		for (Integer i : moves)
		{
			Board ni = b.makeMove(i, color);
			if (bestmoves.size() == 0)
			{
				bestmoves.add(i);
				best = heuristic.evaluate(ni, color);
				continue;
			}
			int val = heuristic.evaluate(ni, color);
			if (val > best)
			{
				best = val;
				bestmoves.clear();
				bestmoves.add(i);
			} else if (val == best)
			{
				bestmoves.add(i);
			}	
		}
		Random rand = new Random();
		int bestmove = bestmoves.get(rand.nextInt(bestmoves.size()));
		
		return bestmove;
	}

	@Override
	public String getName() {
		return "Randomized Greedy AI Player using the " + heuristic.getName();
	}

}
