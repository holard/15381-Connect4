package gui;

import gameDefs.Board;
import gameDefs.ConsoleHumanPlayer;
import gameDefs.Game;

import java.util.Scanner;

import ai.GreedyAI;
import ai.NeighborsHeuristic;
import ai.RandomAI;

public class TextGui {
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		Game g = new Game();
		
		// Set players here
		// g.registerPlayer(new RandomAI(),0);
		g.registerPlayer(new ConsoleHumanPlayer(), 0);
		g.registerPlayer(new GreedyAI(new NeighborsHeuristic()), 1);
		
		while (true)
		{
			g.step();
			Board b = g.getBoard();
			
			int rows = b.getRows();
			int cols = b.getCols();
			
			for (int row = 0; row < rows; row++)
			{
				String s = "|";
				for (int col = 0; col < cols; col++)
				{
					int c = b.getPiece(row,col);
					switch (c)
					{
					case -1:
						s += "_|";
						break;
					case 0:
						s += "0|";
						break;
					case 1:
						s += "1|";
						break;
					}
				}
				System.out.println(s);
			}
			int gstat = g.gameStatus();
			if (gstat > -1)
			{
				if (gstat == 2)
					System.out.println("The game ended in a draw!");
				else
					System.out.println("Player " + g.gameStatus() + " is the winner!");
				g.startOver();
			}
			
			String in = scan.nextLine();
		}
	}
}
