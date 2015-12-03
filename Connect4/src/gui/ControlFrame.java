package gui;

import gameDefs.Player;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ControlFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * title
	 */
	private static final String TITLE = "Control Panel";
	
	/**
	 * instruction label
	 */
	private JLabel instruction;
	
	/**
	 * action panel
	 */
	private static ActionPanel actionPanel;
	
	/**
	 * player
	 */
	private static GuiHumanPlayer player;
	
	/**
	 * width
	 */
	private static final int WIDTH = 560;
	
	/**
	 * height
	 */
	private static final int HEIGHT = 25;
	
	
	/**
	 * columns
	 */
	private static final int COLUMNS = 7;
	
	/**
	 * selected column
	 */
	private int selected = -1;
	
	
	public ControlFrame(GuiHumanPlayer p) {
		
		player = p;
		
		setTitle(TITLE);
		
		actionPanel = new ActionPanel(this, p, WIDTH, COLUMNS);
		
		setResizable(true);

		Container container = getContentPane();
		
		container.setLayout(new BorderLayout());
		
		JPanel instructionPanel = new JPanel();
		instructionPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		instruction = new JLabel();
		instructionPanel.add(instruction);
		
		container.add(instructionPanel, BorderLayout.NORTH);
		container.add(actionPanel, BorderLayout.SOUTH);
		
		pack();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setVisible(true);
		
	}
	
	public void setInstruction(String s) {
		instruction.setText(s);
		repaint();
		revalidate();
	}
	
	public int getNumber() {
		return selected;
	}
	
	public void setNumber(int s) {
		selected = s;
	}
}
