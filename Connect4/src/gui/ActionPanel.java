package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gameDefs.Board;
import gameDefs.ConsoleHumanPlayer;
import gameDefs.Game;

/**
 * panel for the action buttons
 * 
 * @author annie
 *
 */
public class ActionPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * height
	 */
	private static final int HEIGHT = 80;
	
	/**
	 * buttons
	 */
	private final JButton[] buttons;
	
	/**
	 * the panel
	 * 
	 * @param g
	 *            game
	 * @param gui
	 *            ui
	 */
	public ActionPanel(ControlFrame f, GuiHumanPlayer p, int width, int columns) {
				
		int columnWidth = width / columns;

		setLayout(new GridLayout(1, columns));
		setPreferredSize(new Dimension(width, HEIGHT));

		buttons = new JButton[columns];
		
		for (int c = 0; c < columns; c ++) {
			buttons[c] = new JButton();
			buttons[c].setText(Integer.toString(c+1));
			buttons[c].addActionListener(new MoveListener(f, p, c));
			buttons[c].setPreferredSize(new Dimension(columnWidth, HEIGHT));
			add(buttons[c]);
		}		
	}
}
