package shapePackage;

import java.awt.Color;
import java.util.ArrayList;

import main.Board;
import main.NextPiece;

/**
 * orientation 1:		orientation 2:
 * 	   __				 __ __
 * 	  |N |				|SW|E |
 * 	__|__|				|__|__|__
 * |W |C |				   |C |N |
 * |__|__|				   |__|__|
 * |SW|
 * |__|	
 * @author Tanawat
 *
 */
public class SShape extends Shape {

	private Point center, N, W, SW;
	private int orientation = 1;
	private Color c;

	public SShape() {
		center = new Point();
		N = new Point(center.getX(), center.getY() + 1);
		W = new Point(center.getX() - 1, center.getY());
		SW = new Point(center.getX() - 1, center.getY() - 1);
		c = Shape.colorLookup[(int) (Math.random() * 4)];
		showNext();
	}

	public SShape(Point center2) {
		center = center2;
		N = new Point(center.getX(), center.getY() + 1);
		W = new Point(center.getX() - 1, center.getY());
		SW = new Point(center.getX() - 1, center.getY() - 1);
		c = Shape.colorLookup[(int) (Math.random() * 4)];
	}

	@Override
	public void drop() {
		if (canDrop()) {
			clear();
			move(Direction.DOWN);
			show();
		}
	}

	@Override
	public void rotate() {
		clear();
		Point nc;
		if (orientation == 1) {
			if (center.getX() == Board.BOARDWIDTH-1) {
				nc = new Point(center.getX()-1, center.getY());
			}
			else {
				nc = center;
			}
			if (center.isSafe() && Point.isSafe(nc.getX()+1, nc.getY()) &&
					Point.isSafe(nc.getX(), nc.getY()+1) &&
					Point.isSafe(nc.getX()-1, nc.getY()+1)) {
				center = nc;
				N.set(nc.getX()+1, nc.getY());
				W.set(nc.getX(), nc.getY()+1);
				SW.set(nc.getX()-1, nc.getY()+1);
				orientation = 2;
			}	
		}
		else if (orientation == 2) {
			if (Point.isSafe(center.getX(), center.getY()+1) &&
					Point.isSafe(center.getX()-1, center.getY()) &&
					Point.isSafe(center.getX()-1, center.getY()-1)) {
				N.set(center.getX(), center.getY()+1);
				W.set(center.getX()-1, center.getY());
				SW.set(center.getX()-1, center.getY()-1);
				orientation = 1;
			}
		}
		show();

	}

	@Override
	public void moveLeft() {
		Direction d = Direction.LEFT;
		clear();
		if (orientation == 1) {
			if (N.canMove(d) && center.canMove(d) && SW.canMove(d))
				move(d);
		}
		if (orientation == 2) {
			if (N.canMove(d) && W.canMove(d) && SW.canMove(d))
				move(d);
		}
		show();

	}

	@Override
	public void moveRight() {
		Direction d = Direction.RIGHT;
		clear();
		if (orientation == 1) {
			if (N.canMove(d) && center.canMove(d) && SW.canMove(d))
				move(d);
		}
		if (orientation == 2) {
			if (N.canMove(d) && W.canMove(d) && SW.canMove(d))
				move(d);
		}
		show();

	}

	@Override
	public void show() {
		N.show(c);
		center.show(c);
		W.show(c);
		SW.show(c);
	}

	@Override
	public boolean canDrop() {
		if (orientation == 1) {
			return SW.canDrop() && center.canDrop();
		} else {
			return SW.canDrop() && center.canDrop() && N.canDrop();
		}
	}

	@Override
	public boolean checkLine() {
		int count = 0;
		if (N.checkLine())
			++count;
		if (center.checkLine())
			++count;
		if (W.checkLine())
			++count;
		if (SW.checkLine())
			++count;
		Board.incrementScore(count);
		return true;
	}

	@Override
	public void move(Direction d) {
		N.move(d);
		center.move(d);
		W.move(d);
		SW.move(d);
	}

	public void clear() {
		N.clear();
		center.clear();
		W.clear();
		SW.clear();
	}

	@Override
	public void showNext() {
		NextPiece.clear();
		Point C = new Point(1,1);
		Point W = new Point(C.getX()-1, C.getY());
		Point N = new Point(C.getX(), C.getY()+1);
		Point SW = new Point(C.getX()-1, C.getY()-1);
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(C);
		points.add(W);
		points.add(N);
		points.add(SW);
		NextPiece.showPiece(c, points);
		
	}

}
