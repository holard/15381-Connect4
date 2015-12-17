package gui;


import gameDefs.Board;
import gameDefs.Game;

import java.util.Scanner;

import ai.CenterHeuristic;
import ai.LearningAI;
import ai.MinimaxAI;
import ai.NeighborsHeuristic;
import ai.RandomAI;
import ai.RandomizedGreedyAI;
import ai.SquaredLinesHeuristic;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;


public class Gui {
	
	/** title */
	private static final String TITLE = "Connect4";
	
	/** rows */
	private static final int ROWS = 6;
	
	/** columns */
	private static final int COLUMNS = 7;
	
	/** width */
	private static final int WIDTH = 560;

	/** height */
	private static final int HEIGHT = 480;
	
	/** human player exists */
	private static int HUMAN = 0;
	
	/** ai step frame */
	private static AIOnlyFrame stepFrame;
	
	/** lock */
	private static Object lock;

	
	public synchronized static void main(String[] args)
	{
		Game g = new Game();
		Board b = g.getBoard();
		
		// Set players here
		//g.registerPlayer(new RandomAI(),0);
		g.registerPlayer(new GuiHumanPlayer(), 0);
		g.registerPlayer(new LearningAI(new SquaredLinesHeuristic(), new CenterHeuristic(), 3), 1);
		
		// Set to 1 if we have any human players, 0 otherwise
		HUMAN = 1;
		
		if (HUMAN == 0) {
			lock = new Object();
			stepFrame = new AIOnlyFrame(lock);
		} 
		
		BoardPanel boardPanel = new BoardPanel(b, ROWS, COLUMNS, WIDTH, HEIGHT);
		
		JFrame frame = new JFrame(TITLE);
		frame.setResizable(true);

		Container container = frame.getContentPane();
		
		container.setLayout(new BorderLayout());

		container.add(boardPanel);
		
		frame.pack();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		while (true)
		{
			b = g.getBoard();
			
			/* refresh board */
			boardPanel.refresh(b);
			frame.repaint();
			frame.revalidate();
			
			int gstat = g.gameStatus();
			if (gstat > -1)
			{
				if (gstat == 2)
					System.out.println("The game ended in a draw!");
				else
					System.out.println("Player " + g.gameStatus() + " is the winner!");
				g.startOver();
			}
			
			// if no human players, then wait for proceed button from AIOnlyFrame
			if (HUMAN == 0) {
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			g.step();
			
		}
	}
	
}
