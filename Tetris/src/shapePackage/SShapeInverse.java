package shapePackage;

import java.awt.Color;
import java.util.ArrayList;

import main.Board;
import main.NextPiece;

/**
 *  orientation 1	Orientation 2
 *   ____			      ____ ____
 *  | N  |		 	     | E  | SE |
 *  |____|____	     ____|____|____|
 *  |/C//| E  |		| N  |/C//|
 *  |////|____|		|____|////|
 *       | SE |
 *       |____|
 * @author Tanawat
 *
 */

public class SShapeInverse extends Shape{
	private Point center, N, E, SE;
	private Color c = Shape.randomColor();
	private int orientation = 1;
	
	public SShapeInverse() {
		center = new Point();
		N = new Point(center.getX(), center.getY()+1);
		E = new Point(center.getX()+1, center.getY());
		SE = new Point(center.getX()+1, center.getY()-1);
		showNext();
	}

	@Override
	public void rotate() {
		clear();
		
		Point nc = center;
		int x,y;
		if (orientation == 1) {
			if (center.getX() == 0) {
				nc = new Point(center.getX()+1, center.getY());
			}
			x = nc.getX();
			y = nc.getY();
			if (nc.isSafe() && Point.isSafe(x-1, y) &&
					Point.isSafe(x, y+1) && Point.isSafe(x+1, y+1)) {
				center = nc;
				N.set(x-1, y);
				E.set(x, y+1);
				SE.set(x+1, y+1);
				orientation = 2;
			}
		}
		else {  // orientation == 4
			x = nc.getX();
			y = nc.getY();
			if (Point.isSafe(x,  y+1) && Point.isSafe(x+1, y) && Point.isSafe(x+1,  y+1)) {
				N.set(x, y+1);
				E.set(x+1, y);
				SE.set(x+1, y-1);
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
			if(N.canMove(d) && center.canMove(d) && SE.canMove(d)) {
				move(d);
			}
		}
		else {
			if (N.canMove(d) && E.canMove(d)) {
				move(d);
			}
		}
		show();
		
	}

	@Override
	public void moveRight() {
		Direction d = Direction.RIGHT;
		clear();
		if (orientation == 1) {
			if(N.canMove(d) && E.canMove(d) && SE.canMove(d)) {
				move(d);
			}
		}
		else {
			if (SE.canMove(d) && center.canMove(d)) {
				move(d);
			}
		}
		show();
	}

	@Override
	public void show() {
		N.show(c);
		center.show(c);
		E.show(c);
		SE.show(c);
	}

	@Override
	public boolean canDrop() {
		if (orientation == 1) {
			return center.canDrop() && SE.canDrop();
		}
		else {
			return N.canDrop() && center.canDrop() && SE.canDrop();
		}
	}

	@Override
	public boolean checkLine() {
		int count = 0;
		if (orientation == 1) {
			if (N.checkLine())
				++count;
			if (center.checkLine())
				++count;
			if (SE.checkLine())
				++count;
		}
		else {
			if (E.checkLine())
				++count;
			if (center.checkLine())
				++count;
		}
		Board.incrementScore(count);
		return true;
	}

	@Override
	public void move(Direction d) {
		N.move(d);
		center.move(d);
		E.move(d);
		SE.move(d);
	}

	@Override
	public void clear() {
		N.clear();
		center.clear();
		E.clear();
		SE.clear();
	}

	@Override
	public void showNext() {
		NextPiece.clear();
		Point C = new Point(1,1);
		Point E = new Point(C.getX()+1, C.getY());
		Point N = new Point(C.getX(), C.getY()+1);
		Point SE = new Point(C.getX()+1, C.getY()-1);
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(C);
		points.add(E);
		points.add(N);
		points.add(SE);
		NextPiece.showPiece(c, points);
		
	}

}
