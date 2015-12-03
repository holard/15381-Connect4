package gui;

import gameDefs.Player;
import gameDefs.Board;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Simple human-input player that prompts users to make the next move on the gui.
 */
public class GuiHumanPlayer implements Player {
	
	/**
	 * frame for control gui
	 */
	private ControlFrame controlFrame;
	
	/**
	 * if something is selected, change to 1
	 */
	private int selected = 0;
	
	public GuiHumanPlayer() {
		controlFrame = new ControlFrame(this);
	}
	
	
	@Override
	public synchronized int getMove(Board b, int color) {
		int cols = b.getCols();
		controlFrame.setInstruction("Please enter a column 1 ~ " + cols + " for player "
				+ color + ": ");
		controlFrame.setNumber(-1);
		synchronized (this) {
			while (selected == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		setSelected(0);
		return controlFrame.getNumber();
	}

	/**
	 * 
	 * @param i
	 */
	public void setSelected(int i) {
		selected = i;
	}
	
	@Override
	public String getName() {
		return "Gui-based Human Player";
	}

}
