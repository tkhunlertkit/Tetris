package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

import shapePackage.Shape;

public class Board extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int BOARDHEIGHT = 22;
	public static final int BOARDWIDTH = 10;
	private final int REPEAT = 150;
	private static final int DROPTIME = 700;
	public static int score = 0;
	public static int level = 0;
	public static int lines = 0;
	public static int highLines = 0;
	public static int highScore = 0;
	public static int highLevel = 0;
	public static int[] scoreRefer = { 0, 40, 100, 300, 1200 };

	private static Square[][] board = new Square[BOARDHEIGHT + 2][BOARDWIDTH];

	private boolean optionReady = false;
	private static Shape current;

	private static Shape next;
	private Timer tLeft = new Timer(REPEAT, new TimerLeft());
	private Timer tRight = new Timer(REPEAT, new TimerRight());
	private Timer tManualDrop = new Timer(50, new TimerManualDrop());
	public static Timer tDrop;

	public Board() {
		setLayout(new GridLayout(BOARDHEIGHT, BOARDWIDTH, 0, 0));
		for (int i = BOARDHEIGHT + 1; i >= 0; --i) {
			for (int j = 0; j < BOARDWIDTH; ++j) {
				board[i][j] = new Square();
				board[i][j].addKeyListener(new MyKeyListener());
				if (i < BOARDHEIGHT)
					add(board[i][j]);
			}
		}

		current = Shape.getNewShape();
		next = Shape.getNewShape();
		tDrop = new Timer(DROPTIME - (level * 100), new TimerDrop());
		tDrop.start();
		setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	}

	public static Square[][] getInstance() {
		return board;
	}

	public static void show(int row, int col, Color c) {
		board[row][col].show(c);
	}

	public static boolean isSafe(int x, int y) {
		return board[x][y].isSafe();
	}

	public static void clear(int row, int col) {
		board[row][col].clear();
	}

	public static void set(int original_x, int original_y, int new_x, int new_y) {
		board[original_x][original_y].setSquare(board[new_x][new_y]);
	}

	public static void incrementScore(int numLines) {
		lines += numLines;
		UserInterface.linesLabel.setText(Integer.toString(lines));
		score += scoreRefer[numLines] * (level + 1);
		UserInterface.scoreLabel.setText(Integer.toString(score));
		if (level < 9) {
			level = lines / 20;
			UserInterface.levelLabel.setText(Integer.toString(level));
			int temp = DROPTIME - (level * 100);
			if (temp <= 50)
				tDrop.setDelay(50);
			else
				tDrop.setDelay(temp);
		}

	}

	public static void dead() {
		if (score > highScore) {
			highScore = score;
			UserInterface.highScore.setText("(" + Integer.toString(Board.highScore) + ")");
		}
		if (level > highLevel) {
			highLevel = level;
			UserInterface.highLevel.setText("(" + Integer.toString(Board.highLevel) + ")");
		}
		if (lines > highLines) {
			highLines = lines;
			UserInterface.highLines.setText("(" + Integer.toString(Board.highLines) + ")");
		}
		clearBoard();
		score = 0;
		lines = 0;
		level = 0;
	}

	public static void clearBoard() {
		tDrop.stop();
		for (Square[] sRow : board)
			for (Square s : sRow) {
				s.clear();
			}
		next = Shape.getNewShape();
		current = Shape.getNewShape();

	}

	public void drop() {
		if (current.canDrop()) {
			current.drop();

		} else {
			current.checkLine();
			current = next;
			next = Shape.getNewShape();
			current.show();
		}
	}

	private class TimerLeft implements ActionListener {
		public void actionPerformed(ActionEvent E) {
			current.moveLeft();
			tLeft.setDelay(50);
		}
	}

	private class TimerRight implements ActionListener {
		public void actionPerformed(ActionEvent E) {
			current.moveRight();
			tRight.setDelay(50);
		}
	}

	private class TimerManualDrop implements ActionListener {
		public void actionPerformed(ActionEvent E) {
			drop();
		}
	}

	private class TimerDrop implements ActionListener {
		public void actionPerformed(ActionEvent E) {
			if (!tManualDrop.isRunning())
				drop();
		}
	}

	private class MyKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println(arg0.getKeyCode());

			// down arrow
			if (arg0.getKeyCode() == 40) {
				if (!tManualDrop.isRunning()) {
					drop();
					tManualDrop.start();
				}
			}

			// spacebar
			if (arg0.getKeyCode() == 32) {
				current.rotate();
			}

			// left arrow
			if (arg0.getKeyCode() == 37) {
				if (!tLeft.isRunning()) {
					current.moveLeft();
					clearTimer();
					tLeft.start();
				}
			}
			// right arrow
			if (arg0.getKeyCode() == 39) {
				if (!tRight.isRunning()) {
					current.moveRight();
					clearTimer();
					tRight.start();
				}
			}

			// control is pressed
			if (arg0.getKeyCode() == 17) {
				optionReady = true;
			}

			// quit with ctrl+w
			if (arg0.getKeyCode() == 87 && optionReady) {
				System.exit(0);
			}
			
			// pause with ctrl+p
			if (arg0.getKeyCode() == 80 && optionReady) {
				if (tDrop.isRunning())
					tDrop.stop();
				else
					tDrop.start();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("releasing: " + arg0.getKeyCode());
			if (arg0.getKeyCode() == 17) {
				optionReady = false;
			}
			// left arrow
			if (arg0.getKeyCode() == 37) {
				tLeft.restart();
				tLeft.stop();
			}
			// right arrow
			if (arg0.getKeyCode() == 39) {
				tRight.restart();
				tRight.stop();
			}

			// down arrow
			if (arg0.getKeyCode() == 40) {
				tManualDrop.restart();
				tManualDrop.stop();
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
		}

		private void clearTimer() {
			tLeft.stop();
			tRight.stop();
		}

	}

}
