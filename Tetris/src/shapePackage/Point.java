package shapePackage;
import java.awt.Color;

import main.Board;
import main.UserInterface;
import shapePackage.Shape.Direction;

public class Point extends Shape {

	private int x;
	private int y;

	public Point() {
		// initial point
		x = Board.BOARDWIDTH / 2;
		y = Board.BOARDHEIGHT-1;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void drop() {
		--y;
	}

	@Override
	public void rotate() {
		return;
	}

	@Override
	public void moveLeft() {
		--x;
	}

	@Override
	public void moveRight() {
		++x;
	}

	public void show(Color c) {
		if (canShow())
			Board.show(y, x, c);
	}

	public boolean canMove(Direction d) {
		if (inBoard()) {
			if (d == Direction.LEFT)
				return x - 1 >= 0 && Board.isSafe(y, x - 1);
			if (d == Direction.RIGHT)
				return x + 1 < Board.BOARDWIDTH && Board.isSafe(y, x + 1);
			return y - 1 >= 0 && Board.isSafe(y - 1, x);
		}
		return true;
	}

	private boolean canShow() {
		return x >= 0 && x < Board.BOARDWIDTH && y >= 0
				&& y < Board.BOARDHEIGHT;
	}

	@Override
	public void show() {
		show(Color.RED);
	}

	@Override
	public boolean canDrop() {
		return canMove(Direction.DOWN);
	}

	public boolean inBoard() {
		return x >= 0 && x < Board.BOARDWIDTH && y >= 0;
	}

	public boolean isSafe() {
		return inBoard() && Board.isSafe(y, x);
	}

	public static boolean isSafe(int x, int y) {
		Point p = new Point(x, y);
		return p.isSafe();
	}

	public void clear() {
		if (inBoard()) {
			Board.clear(y, x);
		}
	}

	@Override
	public boolean checkLine() {
		if (y == Board.BOARDHEIGHT) {
			Board.dead();
			return false;
		}
		for (int i = 0; i < Board.BOARDWIDTH; ++i) {
			if (Board.isSafe(y, i)) 
				return false;
		}
		clearLine(y);
		return true;
	}

	private void clearLine(int y) {
		for (int j = 0; j < Board.BOARDWIDTH; ++j) {
			Board.clear(y, j);
		}
		for (int i = y; i <= Board.BOARDHEIGHT; ++i) {
			for (int j = 0; j < Board.BOARDWIDTH; ++j) {
				Board.set(i, j, i+1, j);
			}
		}
	}

	@Override
	public void move(Direction d) {
		if (d == Direction.LEFT)
			moveLeft();
		if (d == Direction.RIGHT)
			moveRight();
		if (d == Direction.DOWN)
			drop();
		
	}

	@Override
	public void showNext() {
		return;
		
	}

}
