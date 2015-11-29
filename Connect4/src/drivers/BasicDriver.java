package drivers;

import ai.GreedyAI;
import ai.NeighborsHeuristic;
import ai.RandomAI;
import ai.RandomizedGreedyAI;
import gameDefs.Game;
import gameDefs.Player;

/**
 * Test driver for two given AI players, writes to System.out
 */
public class BasicDriver {

	
	public static void main(String[] args)
	{
		Player p0 = new GreedyAI(new NeighborsHeuristic());
		Player p1 = new RandomizedGreedyAI(new NeighborsHeuristic());
		int trials = 5000;
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
