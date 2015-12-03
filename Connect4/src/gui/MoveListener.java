package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameDefs.Game;

/**
 * listener for tiles on rack panel
 * 
 * @author annie
 *
 */
public class MoveListener implements ActionListener {
	
	/** control frame */
	private final ControlFrame frame;
	
	/** column number selected */
	private final int number;
	
	/** gui player */
	private GuiHumanPlayer p;

	/**
	 * Creates a new rack listener to get tiles to place onto board
	 * 
	 * @param t
	 *            tile
	 * @param g
	 *            game
	 * @param gui
	 *            ui
	 * @param exchange
	 *            exchange mode
	 */
	public MoveListener(ControlFrame f, GuiHumanPlayer p, int column) {
		this.number = column;
		this.frame = f;
		this.p = p;
	}

	@Override
	public synchronized void actionPerformed(ActionEvent evt) {
		synchronized (p) {
			frame.setNumber(number);
			p.setSelected(1);
			p.notifyAll();
		}
	}

}
