package shapePackage;

import java.awt.Color;
import java.util.ArrayList;

import main.Board;
import main.NextPiece;

/**
 *   orientation 1:    orientation 2:   orientation 3:   orientation 4:
 *     __ __ __				  __			 __				 __
 *    |W |C |E |			 |W |			|S |			|E |
 *    |__|__|__|		   __|__|		  __|__|__			|__|__
 *    	 |S |			  |S |C |		 |E |C |W |			|C |S |
 *    	 |__|			  |__|__|		 |__|__|__|			|__|__|
 *    						 |E |							|W |
 *    						 |__|							|__|
 * @author Tanawat
 *
 */
public class TShape extends Shape{
	
	private Point center, W, E, S;
	private static Color c = Shape.randomColor();
	private int orientation = 1;

	public TShape() {
		center = new Point();
		W = new Point(center.getX()-1, center.getY());
		E = new Point(center.getX()+1, center.getY());
		S = new Point(center.getX(), center.getY()-1);
		showNext();
	}

	public TShape(boolean sn) {
		center = new Point();
		W = new Point(center.getX()-1, center.getY());
		E = new Point(center.getX()+1, center.getY());
		S = new Point(center.getX(), center.getY()-1);
	}
	
	@Override
	public Shape clone() {
		TShape t = new TShape(true);
		t.center = (Point) center.clone();
		t.W = (Point) W.clone();
		t.E = (Point) E.clone();
		t.S = (Point) S.clone();
		t.orientation = orientation;
		return t;
	}

	@Override
	public void rotate() {
		clear();
		if (orientation == 1) {
			if (Point.isSafe(center.getX(), center.getY()-1) &&
					Point.isSafe(center.getX(), center.getY()+1) &&
					Point.isSafe(center.getX()-1, center.getY())) {
				E.set(center.getX(), center.getY()-1);
				W.set(center.getX(), center.getY()+1);
				S.set(center.getX()-1, center.getY());
				orientation = 2;
			}
		}
		else if (orientation == 2) {
			Point nc;
			if (center.getX() == Board.BOARDWIDTH-1) 
				nc = new Point(center.getX()-1, center.getY()); 
			else
				nc = center;
			if (nc.isSafe() && Point.isSafe(nc.getX()+1, nc.getY()) &&
					Point.isSafe(nc.getX()-1, nc.getY()) &&
					Point.isSafe(nc.getX(), nc.getY()-1)) {
				center = nc;
				E.set(center.getX()-1, center.getY());
				S.set(center.getX(), center.getY()+1);
				W.set(center.getX()+1, center.getY());
				orientation = 3;
			}
		}
		else if (orientation == 3) {
			if (Point.isSafe(center.getX()+1, center.getY()) &&
					Point.isSafe(center.getX(), center.getY()-1) &&
					Point.isSafe(center.getX(), center.getY()+1)) {
				E.set(center.getX(), center.getY()+1);
				W.set(center.getX(), center.getY()-1);
				S.set(center.getX()+1, center.getY());
				orientation = 4;
			}
		}
		else {
			Point nc;
			if (center.getX() == 0) nc = new Point(center.getX()+1, center.getY());
			else nc = center;
			if (nc.isSafe() && Point.isSafe(nc.getX()-1, nc.getY()) &&
					Point.isSafe(nc.getX()+1, nc.getY()) &&
					Point.isSafe(nc.getX(), center.getY()-1)) {
				center = nc;
				E.set(center.getX()+1, center.getY());
				W.set(center.getX()-1, center.getY());
				S.set(center.getX(), center.getY()-1);
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
			if (W.canMove(d) && S.canMove(d))
				move(d);
		}
		else if (orientation == 2) {
			if (W.canMove(d) && S.canMove(d) && E.canMove(d))
				move(d);
		}
		else if (orientation == 3) {
			if (S.canMove(d) && E.canMove(d))
				move(d);
		}
		else {
			if (E.canMove(d) && center.canMove(d) && W.canMove(d)) 
				move(d);
		}
		show();
		
	}

	@Override
	public void moveRight() {
		Direction d = Direction.RIGHT;
		clear();
		if (center.canMove(d) && E.canMove(d) && S.canMove(d) && W.canMove(d))
			move(d);
		show();
		
	}

	@Override
	public void show() {
		center.show(c);
		W.show(c);
		E.show(c);
		S.show(c);
	}

	@Override
	public boolean canDrop() {
		if (orientation == 1) {
			return W.canDrop() && S.canDrop() && E.canDrop();
		}
		if (orientation == 2) {
			return S.canDrop() && E.canDrop();
		}
		if (orientation == 3) {
			return W.canDrop() && center.canDrop() && E.canDrop();
		}
		if (orientation == 4) {
			return W.canDrop() && S.canDrop();
		}
		return false;
	}

	@Override
	public boolean checkLine() {
		int count = 0;
		if (orientation == 1) {
			if (center.checkLine())
				++count;
			if (S.checkLine())
				++count;
		}
		else if (orientation == 2) {
			if (W.checkLine())
				++count;
			if (center.checkLine())
				++count;
			if (E.checkLine())
				++count;
		}
		else if (orientation == 3) {
			if (S.checkLine())
				++count;
			if (center.checkLine())
				++count;
		}
		else {
			if (E.checkLine())
				++count;
			if (center.checkLine())
				++count;
			if (W.checkLine())
				++count;
		}
		
		Board.incrementScore(count);
		return true;
	}

	@Override
	public void move(Direction d) {
		center.move(d);
		W.move(d);
		E.move(d);
		S.move(d);
	}

	@Override
	public void clear() {
		center.clear();
		E.clear();
		W.clear();
		S.clear();
	}

	@Override
	public void showNext() {
		NextPiece.clear();
		Point C = new Point(1,1);
		Point W = new Point(C.getX()-1, C.getY());
		Point E = new Point(C.getX()+1, C.getY());
		Point S = new Point(C.getX(), C.getY()-1);
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(C);
		points.add(W);
		points.add(E);
		points.add(S);
		NextPiece.showPiece(c, points);
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		this.c = c;
	}

	@Override
	public void setSafe() {
		// TODO Auto-generated method stub
		center.setSafe();
		W.setSafe();
		E.setSafe();
		S.setSafe();
	}

	@Override
	public void showWithoutSetState() {
		// TODO Auto-generated method stub
		center.showWithoutSetState();
		W.showWithoutSetState();
		E.showWithoutSetState();
		S.showWithoutSetState();
	}

}
