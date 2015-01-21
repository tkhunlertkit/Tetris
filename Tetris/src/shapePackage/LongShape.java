package shapePackage;

import java.awt.Color;
import java.util.ArrayList;

import main.Board;
import main.NextPiece;

/** 
 * 
 *  orientation 1         orientation 2
 *    ____
 *   |NN  |                ____ ____ ____ ____
 *   |____|				  |S   | C  | N  | NN |
 *   |N   |				  |____|____|____|____|
 *   |____|
 *   |C   |
 *   |____|
 *   |S   |
 *   |____| 
 * @author Tanawat
 *
 */
public class LongShape extends Shape {

	private Point center, S, N, NN;
	private static Color c = Shape.randomColor();
	private int orientation = 1;

	public LongShape() {
		center = new Point();
		NN = new Point(center.getX(), center.getY() + 2);
		N = new Point(center.getX(), center.getY() + 1);
		S = new Point(center.getX(), center.getY() - 1);
		showNext();
	}
	
	public LongShape(boolean sn) {
		center = new Point();
		NN = new Point(center.getX(), center.getY() + 2);
		N = new Point(center.getX(), center.getY() + 1);
		S = new Point(center.getX(), center.getY() - 1);
	}
	
	@Override
	public Shape clone() {
		LongShape l = new LongShape(true);
		l.center = (Point) center.clone();
		l.NN = (Point) NN.clone();
		l.N = (Point) N.clone();
		l.S = (Point) S.clone();
		l.orientation = orientation;
		return l;
	}

	@Override
	public void rotate() {
		clear();
		Point nc;
		if (orientation == 1) {
			if (center.getX() >= Board.BOARDWIDTH - 2)
				nc = new Point(center.getX() - 3, center.getY());
			else if (center.getX() == 0) 
				nc = new Point(center.getX()+1, center.getY());
			else
				nc = center;
			int x = nc.getX();
			int y = nc.getY();
			if (nc.isSafe() && Point.isSafe(x - 1, y) && Point.isSafe(x + 1, y)
					&& Point.isSafe(x + 2, y)) {
				center = nc;
				NN.set(x + 2, y);
				N.set(x + 1, y);
				S.set(x - 1, y);
				orientation = 2;
			}
		}
		else if (orientation == 2) {
			int x = center.getX();
			int y = center.getY();
			if (Point.isSafe(x, y + 2) && Point.isSafe(x, y + 1)
					&& Point.isSafe(x, y - 1)) {
				NN.set(x, y+2);
				N.set(x, y+1);
				S.set(x, y-1);
				orientation = 1;
			}
		}
		show();

	}

	@Override
	public void moveLeft() {
		clear();
		Direction d = Direction.LEFT;
		if (orientation == 1) {
			if (NN.canMove(d) && N.canMove(d) && center.canMove(d)
					&& S.canMove(d)) {
				move(d);
			}
		} else {
			if (S.canMove(d))
				move(d);
		}
		show();

	}

	@Override
	public void moveRight() {
		clear();
		Direction d = Direction.RIGHT;
		if (orientation == 1) {
			if (NN.canMove(d) && N.canMove(d) && center.canMove(d)
					&& S.canMove(d)) {
				move(d);
			}
		} else {
			if (NN.canMove(d))
				move(d);
		}
		show();

	}

	@Override
	public void show() {
		NN.show(c);
		N.show(c);
		center.show(c);
		S.show(c);
	}

	@Override
	public boolean canDrop() {
		if (orientation == 1)
			return S.canDrop();
		else
			return NN.canDrop() && N.canDrop() && center.canDrop()
					&& S.canDrop();
	}

	@Override
	public boolean checkLine() {
		int count = 0;
		if (orientation == 1) {
			if(NN.checkLine())
				++count;
			if (N.checkLine())
				++count;
			if (center.checkLine())
				++count;
			if (S.checkLine())
				++count;
		} else
			if (center.checkLine())
				++count;
		Board.incrementScore(count);
		return true;
		
	}

	@Override
	public void move(Direction d) {
		NN.move(d);
		N.move(d);
		center.move(d);
		S.move(d);
	}

	@Override
	public void clear() {
		NN.clear();
		N.clear();
		center.clear();
		S.clear();
	}

	@Override
	public void showNext() {
		NextPiece.clear();
		Point C = new Point(1,1);
		Point NN = new Point(C.getX(), C.getY()+2);
		Point N = new Point(C.getX(), C.getY()+1);
		Point S = new Point(C.getX(), C.getY()-1);
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(C);
		points.add(NN);
		points.add(N);
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
		S.setSafe();
		N.setSafe(); 
		NN.setSafe();
	}

	@Override
	public void showWithoutSetState() {
		// TODO Auto-generated method stub
		center.showWithoutSetState();
		S.showWithoutSetState();
		N.showWithoutSetState();
		NN.showWithoutSetState();
	}

	
}
