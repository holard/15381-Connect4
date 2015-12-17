package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gameDefs.Board;
import gameDefs.Player;

/**
 * Looks at all states within a given depth and picks the best 
 * 		one according to the minimax algorithm using the given heuristic.
 * Assumes the opponent moves by the same heuristic too.
 * Selects randomly from equally good choices.
 */
public class MinimaxAI implements Player{

	Heuristic heuristic;
	int depth;
	
	public MinimaxAI(Heuristic h, int depth)
	{
		heuristic = h;
		this.depth = depth;
	}
	
	public static void main(String[] args)
	{
		MinimaxAI me = new MinimaxAI(new NeighborsHeuristic(),4);
		Board b = new Board();
		b = b.makeMove(1, 0);
		b = b.makeMove(2, 0);
		b = b.makeMove(4, 0);
		b = b.makeMove(1, 1);
		b = b.makeMove(2, 1);
		b = b.makeMove(4, 1);
		
		System.out.println(me.getMove(b, 0));
	}
	
	private int search(Board b, int color, int mycolor, int depth)
	{
		if (depth <= 0)
		{
			return heuristic.evaluate(b, mycolor);
		}
		int status = b.checkStatus();
		if (status > -1)
			return heuristic.evaluate(b, mycolor);
		List<Integer> neighbors = b.getLegalMoves();
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (Integer i : neighbors)
		{
			Board nb = b.makeMove(i, color);

			int val = (search(nb,1-color,mycolor,depth-1));
			if (val > max)
			{
				max = val;
			}
			if (val < min)
			{
				min = val;
			}
		}
		if (color == mycolor)
			return max;
		return min;
	}
	
	@Override
	public int getMove(Board b, int color) {
		List<Integer> moves = b.getLegalMoves();
		return getMoveFromList(b, color, moves);
	}
	
	public int getMoveFromList(Board b, int color, List<Integer> moves) {
		List<Integer> bestmoves = new ArrayList<Integer>();
		int best = -1;
		for (Integer i : moves)
		{
			Board ni = b.makeMove(i, color);
			if (bestmoves.size() == 0)
			{
				bestmoves.add(i);
				
				best = search(ni, 1-color, color, depth-1);
				continue;
			}
			int val = search(ni, 1-color, color, depth-1);
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
		//System.out.println("best I can do is " + best);
		return bestmove;
	}
	
	@Override
	public String getName() {
		return "Randomized Minimax AI Player using the " + heuristic.getName();
	}
	
	@Override
	public void gameOver(int status) {
		return;
	}

}
