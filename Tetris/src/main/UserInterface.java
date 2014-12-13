package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class UserInterface extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 500;
	private final int HEIGHT = 800;
	public static JLabel scoreLabel = new JLabel(Integer.toString(Board.score));
	public static JLabel levelLabel = new JLabel(Integer.toString(Board.level));
	public static JLabel linesLabel = new JLabel(Integer.toString(Board.lines));
	public static JPanel nextPiece = new NextPiece();
	public static JLabel highScore = new JLabel("(" + Integer.toString(Board.highScore) + ")");
	public static JLabel highLevel = new JLabel("(" + Integer.toString(Board.highLevel) + ")");
	public static JLabel highLines = new JLabel("(" + Integer.toString(Board.highLines) + ")");
	

	public UserInterface() {
		setTitle("Tetris");
		setSize(WIDTH, HEIGHT);
		createContents();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		//setResizable(false);
	}

	public void createContents() {
		JPanel main = new Board();
		
		JPanel side = new JPanel(new GridLayout(6,1));
		JPanel level = new JPanel(new GridLayout(1,2));
		level.add(new JLabel("Level:"));
		level.add(levelLabel);
		level.add(highLevel);
		
		JPanel score = new JPanel(new GridLayout(1,2));
		score.add(new JLabel("Score:"));
		score.add(scoreLabel);
		score.add(highScore);
		
		JPanel lines = new JPanel(new GridLayout(1,2));
		lines.add(new JLabel("Line:"));
		lines.add(linesLabel);
		lines.add(highLines);
		
		JPanel test = new JPanel(new FlowLayout());
		JButton newGame = new JButton("New Game");
		newGame.addActionListener(new newGameListener());
		test.add(newGame);

		side.add(score);
		side.add(level);
		side.add(lines);
		side.add(test);
		side.add(new JPanel());
		side.add(nextPiece);
		
		setLayout(new BorderLayout());
		add(main);
		add(side, BorderLayout.EAST);
	}
	
	private class newGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Board.dead();
			Board.tDrop.start();
		}
	}
	

	public static void main(String[] args) {
		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());
		} 
		catch (Exception e) {
			// handle exception
		}

		new UserInterface();
	}
	

}
