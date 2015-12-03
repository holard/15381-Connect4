package drivers;

import ai.CenterHeuristic;
import ai.CompositeHeuristic;
import ai.GreedyAI;
import ai.Heuristic;
import ai.MinimaxAI;
import ai.NeighborsHeuristic;
import ai.RandomAI;
import ai.RandomizedGreedyAI;
import ai.SquaredLinesHeuristic;
import gameDefs.Game;
import gameDefs.Player;

/**
 * Test driver for two given AI players, writes to System.out
 */
public class BasicDriver {

	
	public static void main(String[] args)
	{
		Heuristic comp = new CompositeHeuristic(new NeighborsHeuristic(),new CenterHeuristic());
		Heuristic comp2 = new CompositeHeuristic(new SquaredLinesHeuristic(), new CenterHeuristic());
		Player p0 = new RandomizedGreedyAI(comp2);
		Player p1 = new MinimaxAI(comp2,3);
		int trials = 100;
		int p0_wins = 0;
		int p1_wins = 0;
		Game g = new Game(p0,p1);
		for (int i = 0; i < trials; i++)
		{
			while (g.gameStatus() < 0)
			{
				g.step();
			}
			if (g.gameStatus() == 0)
				p0_wins += 1;
			else
				p1_wins += 1;
			g.startOver();
		}
		
		// swap sides
		g = new Game(p1,p0);
		for (int i = 0; i < trials; i++)
		{
			while (g.gameStatus() < 0)
			{
				g.step();
			}
			if (g.gameStatus() == 0)
				p1_wins += 1;
			else
				p0_wins += 1;
			g.startOver();
		}
		
		System.out.println("Results:");
		System.out.println("Player 0 [" + p0.getName() + "] won " + p0_wins + " times!");
		System.out.println("Player 1 [" + p1.getName() + "] won " + p1_wins + " times!");
	}
}
