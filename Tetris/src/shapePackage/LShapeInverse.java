package shapePackage;

import java.awt.Color;
import java.util.ArrayList;

import main.Board;
import main.NextPiece;

/**
 *    orientation 1  orientation 2       Orientation 3   orientation 4
 *         ____             				____ ____
 *        | NN |						   | C  | W  |    
 *        |____| 	   ____				   |____|____|	  ____ ____ ____	
 *        | N  |	  | W  |			   | N  |		 | NN | N  | C  |
 *    ____|____| 	  |____|____ ____	   |____|		 |____|____|____|
 *   | W  | C  |	  | C  | N  | NN |	   | NN |				   | W  |
 *   |____|____| 	  |____|____|____|	   |____|				   |____|
 * @author Tanawat
 *
 */
public class LShapeInverse extends Shape{

	private Point center, W, N, NN;
	private static Color c = Shape.randomColor();
	private int orientation = 1;

	public LShapeInverse() {
		center = new Point();
		N = new Point(center.getX(), center.getY()+1);
		NN = new Point(center.getX(), center.getY()+2);
		W = new Point(center.getX()-1, center.getY());
		showNext();
	}
	
	public LShapeInverse(boolean sn) {
		center = new Point();
		N = new Point(center.getX(), center.getY()+1);
		NN = new Point(center.getX(), center.getY()+2);
		W = new Point(center.getX()-1, center.getY());
	}

	@Override
	public Shape clone(){
		LShapeInverse n = new LShapeInverse(true);
		n.center = (Point) center.clone();
		n.N = (Point) N.clone();
		n.NN = (Point) NN.clone();
		n.W = (Point) W.clone();
		n.orientation = orientation;
		return n;
	}
	@Override
	public void rotate() {
		clear();
		Point nc = center;
		int x;
		int y;
		if (orientation == 1) {
			if (center.getX() >= Board.BOARDWIDTH-2) 
				nc = new Point(Board.BOARDWIDTH-3, center.getY());
			x = nc.getX();
			y = nc.getY();
			if (nc.isSafe() && Point.isSafe(x+1, y) &&
					Point.isSafe(x, y+1) && Point.isSafe(x+2, y)) {
				center = nc;
				W.set(x, y+1);
				N.set(x+1, y);
				NN.set(x+2, y);
				orientation = 2;
			}
		}
		else if (orientation == 2) {
			x = center.getX();
			y = center.getY();
			if (Point.isSafe(x+1, y) && Point.isSafe(x, y-1) &&
					Point.isSafe(x, y-2)) {
				W.set(x+1, y);
				N.set(x, y-1);
				NN.set(x, y-2);
				orientation = 3;
			}
		}
		else if (orientation == 3) {
			if (center.getX() <= 1)
				nc = new Point(2, center.getY());
			x = nc.getX();
			y = nc.getY();
			if (Point.isSafe(x-1, y) && Point.isSafe(x-2, y) &&
					Point.isSafe(x,  y-1) && nc.isSafe()) {
				center = nc;
				N.set(x-1,  y);
				NN.set(x-2, y);
				W.set(x,  y-1);
				orientation = 4;
			}
		}
		else { // orientation == 4
			x = center.getX();
			y = center.getY();
			if (Point.isSafe(x-1, y) && Point.isSafe(x, y+1) && 
					Point.isSafe(x,  y+2)) {
				NN.set(x, y+2);
				N.set(x,  y+1);
				W.set(x-1, y);
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
			if (W.canMove(d) && N.canMove(d) && NN.canMove(d)) 
				move(d);
		}
		if (orientation == 2) {
			if (W.canMove(d) && center.canMove(d))
				move(d);
		}
		if (orientation == 3) {
			if (center.canMove(d) && N.canMove(d) && NN.canMove(d))
				move(d);
		}
		if (orientation == 4) {
			if(NN.canMove(d) && W.canMove(d))
				move(d);
		}
		show();
	}

	@Override
	public void moveRight() {
		Direction d = Direction.RIGHT;
		clear();
		if (orientation == 1) {
			if (center.canMove(d) && N.canMove(d) && NN.canMove(d)) 
				move(d);
		}
		if (orientation == 2) {
			if (W.canMove(d) && NN.canMove(d))
				move(d);
		}
		if (orientation == 3) {
			if (W.canMove(d) && N.canMove(d) && NN.canMove(d))
				move(d);
		}
		if (orientation == 4) {
			if(center.canMove(d) && W.canMove(d))
				move(d);
		}
		show();
	}

	@Override
	public void show() {
		NN.show(c);
		N.show(c);
		center.show(c);
		W.show(c);
	}

	@Override
	public boolean canDrop() {
		if (orientation == 1)
			return W.canDrop() && center.canDrop();
		if (orientation == 2) 
			return center.canDrop() && N.canDrop() && NN.canDrop();
		if (orientation == 3) 
			return NN.canDrop() && W.canDrop();
		if (orientation == 4)
			return NN.canDrop() && N.canDrop() && W.canDrop();
		return false;
	}

	@Override
	public boolean checkLine() {
		int count = 0;
		if (orientation == 1) {
			if (NN.checkLine())
				++count;
			if (N.checkLine())
				++count;
			if (center.checkLine()) 
				++count;
		}
		else if (orientation == 2) {
			if (W.checkLine()) 
				++count;
			if (center.checkLine())
				++count;
		}
		else if (orientation == 3) {
			if (center.checkLine())
				++count;
			if (N.checkLine())
				++count;
			if (NN.checkLine())
				++count;
		}
		else {
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
		NN.move(d);
		N.move(d);
		center.move(d);
		W.move(d);
	}

	@Override
	public void clear() {
		NN.clear();
		N.clear();
		center.clear();
		W.clear();
	}

	@Override
	public void showNext() {
		NextPiece.clear();
		Point C = new Point(1,1);
		Point NN = new Point(C.getX(), C.getY()+2);
		Point N = new Point(C.getX(), C.getY()+1);
		Point W = new Point(C.getX()-1, C.getY());
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(C);
		points.add(NN);
		points.add(N);
		points.add(W);
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
		N.setSafe();
		NN.setSafe();
	}

	@Override
	public void showWithoutSetState() {
		// TODO Auto-generated method stub
		center.showWithoutSetState();
		W.showWithoutSetState();
		N.showWithoutSetState();
		NN.showWithoutSetState();
	}
	
	

}
