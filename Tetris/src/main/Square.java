package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class Square extends JButton {

	private enum STATE {
		FREE, TAKEN
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private STATE state = STATE.FREE;

	public final Color bgColor = Color.BLACK;

	public Square() {
		super.setContentAreaFilled(false);
		setBackground(bgColor);
		this.setBorder(null);

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

	public boolean isSafe() {
		return state == STATE.FREE;
	}

	public void show(Color c) {
		setBackground(c);
		state = STATE.TAKEN;
	}

	public void clear() {
		state = STATE.FREE;
		setBackground(bgColor);
	}

	public void setSquare(Square s) {
		state = s.state;
		setBackground(s.getBackground());
	}

}
