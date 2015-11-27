package gui;

import gameDefs.Board;
import gameDefs.Game;

import java.util.Scanner;

import ai.RandomAI;

public class TextGui {
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		Game g = new Game();
		g.registerPlayer(new RandomAI(), 0);
		g.registerPlayer(new RandomAI(), 1);
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
			
			if (g.gameStatus() > -1)
			{
				System.out.println("Player " + g.gameStatus() + " is the winner!");
				g.startOver();
			}
			
			String in = scan.nextLine();
		}
	}
}
