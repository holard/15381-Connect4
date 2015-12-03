package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class AIOnlyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static String TITLE = "Click to step";
	
	private static JPanel panel;
	
	private static JButton button;
	
	private final static int WIDTH = 250;
	
	private final static int HEIGHT = 50;
	
	
	public AIOnlyFrame(Object lock) {
		
		setTitle(TITLE);
		
		button = new JButton();
		button.setSize(new Dimension(WIDTH, HEIGHT));
		button.addActionListener(new ActionListener(){
			@Override
			public synchronized void actionPerformed(ActionEvent e) {
				synchronized (lock) {
					lock.notify();
				}
			}
		});
		
		panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.add(button);
		
		setResizable(true);

		Container container = getContentPane();
		
		container.add(panel);
		
		pack();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setVisible(true);
		
	}
	
}