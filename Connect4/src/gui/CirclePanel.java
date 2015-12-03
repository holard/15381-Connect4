package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CirclePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** color */
	private int c;
	
	public CirclePanel(int color) {
		c = color;
	}
	
	public void setColor(int color) {
		c = color;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color color = Color.LIGHT_GRAY;
		if (c == 0) {
			color = Color.RED; 
		} else if (c == 1) {
			color = Color.BLACK;
		}
		g.setColor(color);
		g.fillOval(0, 0, getWidth(), getHeight());
		repaint();
		revalidate();
    }
}
