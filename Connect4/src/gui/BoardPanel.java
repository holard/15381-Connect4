package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.logging.ConsoleHandler;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import gameDefs.Board;
import gameDefs.ConsoleHumanPlayer;
import gameDefs.Game;

/**
 * board panel for ui
 * 
 * @author annie
 *
 */
public class BoardPanel extends JPanel {

	/**
	 * it required me to do this
	 */
	private static final long serialVersionUID = 7924581859948038624L;

	/** board */
	private Board b;
	
	/** rows */
	private final int rows;
	
	/** columns */
	private final int columns;
	
	/** squares */
	private final JPanel[][] squares;

	/**
	 * constructor
	 * @param height 
	 * @param width 
	 * 
	 * @param g
	 *            game
	 * @param gui
	 *            ui
	 */
	public BoardPanel(Board board, int rows, int cols, int width, int height) {
		
		this.b = board;
		this.rows = rows;
		this.columns = cols;
		
		squares = new JPanel[rows][columns];
		setLayout(new GridLayout(rows, columns));
		setPreferredSize(new Dimension(width, height));

		for (int r = 0; r < rows; ++r) {
			for (int c = 0; c < columns; ++c) {
				squares[r][c] = new CirclePanel(b.getPiece(r, c));
				squares[r][c].paintComponents(squares[r][c].getGraphics());
				add(squares[r][c]);
			}
		}
	}
	
	/** update the board */
	public void refresh(Board board) {
		this.b = board;
		for (int r = 0; r < rows; ++r) {
			for (int c = 0; c < columns; ++c) {
				((CirclePanel) squares[r][c]).setColor(b.getPiece(r, c));
				squares[r][c].paintComponents(squares[r][c].getGraphics());
			}
		}
		repaint();
		revalidate();
	}
}