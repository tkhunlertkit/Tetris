package shapePackage;

import java.awt.Color;
import java.util.ArrayList;

import main.Board;
import main.NextPiece;

/**
 * orientation 1:     orientation 2:   orientation 3:  orientation 4:
 * 		 __								 __ __				   __
 * 		|NN|			 __ __ __		|E |C |				  |E | 
 * 		|__|			|C |N |NN|		|__|__|			 __ __|__|
 * 		|N |			|__|__|__|		   |N |			|NN|N |C |
 * 		|__|__			|E |			   |__|			|__|__|__|
 * 		|C |E |			|__|			   |NN|	
 * 		|__|__|							   |__|
 * 
 * @author Tanawat
 *
 */
public class LShape extends Shape {

	private Point center;
	private Point NN;
	private Point N;
	private Point E;
	private int orientation = 1;
	private static Color c = colorLookup[(int) (Math.random() * 4)];

	public LShape() {
		center = new Point();
		NN = new Point(center.getX(), center.getY() + 2);
		N = new Point(center.getX(), center.getY() + 1);
		E = new Point(center.getX() + 1, center.getY());
		showNext();
	}
	
	public LShape(boolean sn) {
		center = new Point();
		NN = new Point(center.getX(), center.getY() + 2);
		N = new Point(center.getX(), center.getY() + 1);
		E = new Point(center.getX() + 1, center.getY());
	}

	@Override
	public Shape clone() {
		LShape l = new LShape(true);
		l.center = (Point) center.clone();
		l.NN = (Point) NN.clone();
		l.N = (Point) N.clone();
		l.E = (Point) E.clone();
		l.orientation = orientation;
		return l;
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
		if (orientation == 1) {
			if (center.getX() == Board.BOARDWIDTH - 2) {
				Point nc = new Point(center.getX() - 1, center.getY());
				if (nc.isSafe() && Point.isSafe(nc.getX(), nc.getY() - 1)
						&& Point.isSafe(nc.getX() + 1, nc.getY())
						&& Point.isSafe(nc.getX(), nc.getY() + 2)) {
					center = nc;
					NN.set(center.getX() + 2, center.getY());
					N.set(center.getX() + 1, center.getY());
					E.set(center.getX(), center.getY() - 1);
					orientation = 2;
				}
			} else {
				Point nc = center;
				if (nc.isSafe() && Point.isSafe(nc.getX(), nc.getY() - 1)
						&& Point.isSafe(nc.getX() + 1, nc.getY())
						&& Point.isSafe(nc.getX(), nc.getY() + 2)) {
					NN.set(center.getX() + 2, center.getY());
					N.set(center.getX() + 1, center.getY());
					E.set(center.getX(), center.getY() - 1);
					orientation = 2;
				}
			}
		} else if (orientation == 2) {
			if (center.getX() == 0) {
				Point nc = new Point(center.getX() + 1, center.getY());
				if (nc.isSafe() && Point.isSafe(nc.getX() - 1, nc.getY())
						&& Point.isSafe(nc.getX(), nc.getY() - 1)
						&& Point.isSafe(nc.getX(), nc.getY() - 2)) {
					center = nc;
					NN.set(center.getX(), center.getY() - 2);
					N.set(center.getX(), center.getY() - 1);
					E.set(center.getX() - 1, center.getY());
					orientation = 3;
				}
			} else {
				Point nc = new Point(center.getX(), center.getY());
				if (nc.isSafe() && Point.isSafe(nc.getX() - 1, nc.getY())
						&& Point.isSafe(nc.getX(), nc.getY() - 1)
						&& Point.isSafe(nc.getX(), nc.getY() - 2)) {
					NN.set(center.getX(), center.getY() - 2);
					N.set(center.getX(), center.getY() - 1);
					E.set(center.getX() - 1, center.getY());
					orientation = 3;
				}
			}
		} else if (orientation == 3) {
			if (center.getX() <= 1) {
				Point nc = new Point(center.getX() + 1, center.getY());
				if (nc.isSafe() && Point.isSafe(nc.getX() - 1, nc.getY())
						&& Point.isSafe(nc.getX() - 2, nc.getY())
						&& Point.isSafe(nc.getX(), nc.getY() + 1)) {
					center = nc;
					NN.set(center.getX() - 2, center.getY());
					N.set(center.getX() - 1, center.getY());
					E.set(center.getX(), center.getY() + 1);
					orientation = 4;
				}
			} else {
				Point nc = new Point(center.getX(), center.getY());
				if (nc.isSafe() && Point.isSafe(nc.getX() - 1, nc.getY())
						&& Point.isSafe(nc.getX() - 2, nc.getY())
						&& Point.isSafe(nc.getX(), nc.getY() + 1)) {
					center = nc;
					NN.set(center.getX() - 2, center.getY());
					N.set(center.getX() - 1, center.getY());
					E.set(center.getX(), center.getY() + 1);
					orientation = 4;
				}
			}
		} else if (orientation == 4) {
			if (center.getX() >= Board.BOARDWIDTH - 2) {
				Point nc = new Point(center.getX() - 1, center.getY());
				if (nc.isSafe() && Point.isSafe(nc.getX(), nc.getY() + 2)
						&& Point.isSafe(nc.getX(), nc.getY() + 1)
						&& Point.isSafe(nc.getX() + 1, nc.getY())) {
					center = nc;
					NN.set(center.getX(), center.getY() + 2);
					N.set(center.getX(), center.getY() + 1);
					E.set(center.getX() + 1, center.getY());
					orientation = 1;
				}
			} else {
				Point nc = new Point(center.getX(), center.getY());
				if (nc.isSafe() && Point.isSafe(nc.getX(), nc.getY() + 2)
						&& Point.isSafe(nc.getX(), nc.getY() + 1)
						&& Point.isSafe(nc.getX() + 1, nc.getY())) {
					center = nc;
					NN.set(center.getX(), center.getY() + 2);
					N.set(center.getX(), center.getY() + 1);
					E.set(center.getX() + 1, center.getY());
					orientation = 1;
				}
			}
		}

		show();
	}

	@Override
	public void moveLeft() {
		clear();
		if (orientation == 1) {
			if (NN.canMove(Direction.LEFT) && N.canMove(Direction.LEFT)
					&& center.canMove(Direction.LEFT))
				move(Direction.LEFT);
		}
		if (orientation == 2) {
			if (center.canMove(Direction.LEFT) && E.canMove(Direction.LEFT))
				move(Direction.LEFT);
		}
		if (orientation == 3) {
			if (E.canMove(Direction.LEFT) && N.canMove(Direction.LEFT)
					&& NN.canMove(Direction.LEFT))
				move(Direction.LEFT);
		}
		if (orientation == 4) {
			if (NN.canMove(Direction.LEFT) && E.canMove(Direction.LEFT))
				move(Direction.LEFT);
		}
		show();
	}

	@Override
	public void moveRight() {
		clear();
		if (orientation == 1) {
			if (NN.canMove(Direction.RIGHT) && N.canMove(Direction.RIGHT)
					&& E.canMove(Direction.RIGHT))
				move(Direction.RIGHT);
		}
		if (orientation == 2) {
			if (NN.canMove(Direction.RIGHT) && E.canMove(Direction.RIGHT))
				move(Direction.RIGHT);
		}
		if (orientation == 3) {
			if (NN.canMove(Direction.RIGHT) && N.canMove(Direction.RIGHT)
					&& center.canMove(Direction.RIGHT))
				move(Direction.RIGHT);
		}
		if (orientation == 4) {
			if (center.canMove(Direction.RIGHT) && E.canMove(Direction.RIGHT))
				move(Direction.RIGHT);
		}
		show();
	}

	@Override
	public void show() {
		if (NN.isSafe())
			NN.show(c);
		if (N.isSafe())
			N.show(c);
		if (center.isSafe())
			center.show(c);
		if (E.isSafe())
			E.show(c);
	}

	public void move(Direction d) {
		if (d == Direction.DOWN) {
			NN.drop();
			N.drop();
			center.drop();
			E.drop();
			return;
		}
		if (d == Direction.LEFT) {
			NN.moveLeft();
			N.moveLeft();
			center.moveLeft();
			E.moveLeft();
			return;
		}
		if (d == Direction.RIGHT) {
			NN.moveRight();
			N.moveRight();
			center.moveRight();
			E.moveRight();
			return;
		}
	}

	public boolean canDrop() {
		if (orientation == 1) {
			if (center.canMove(Direction.DOWN) && E.canMove(Direction.DOWN))
				return true;
		}
		if (orientation == 2) {
			if (E.canMove(Direction.DOWN) && N.canMove(Direction.DOWN)
					&& NN.canMove(Direction.DOWN))
				return true;
		}
		if (orientation == 3) {
			if (NN.canMove(Direction.DOWN) && E.canMove(Direction.DOWN))
				return true;
		}
		if (orientation == 4) {
			if (NN.canMove(Direction.DOWN) && N.canMove(Direction.DOWN)
					&& center.canMove(Direction.DOWN))
				return true;
		}
		return false;
	}

	public void clear() {
		center.clear();
		NN.clear();
		N.clear();
		E.clear();
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
			if (center.checkLine())
				++count;
			if (E.checkLine())
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
			if (E.checkLine())
				++count;
			if (center.checkLine())
				++count;
		}
		Board.incrementScore(count);
		return true;
	}

	@Override
	public void showNext() {
		NextPiece.clear();
		Point C = new Point(1,1);
		Point NN = new Point(C.getX(), C.getY()+2);
		Point N = new Point(C.getX(), C.getY()+1);
		Point E = new Point(C.getX()+1, C.getY());
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(C);
		points.add(NN);
		points.add(N);
		points.add(E);
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
		N.setSafe();
		NN.setSafe();
		E.setSafe();
	}

	@Override
	public void showWithoutSetState() {
		// TODO Auto-generated method stub
		center.showWithoutSetState();
		N.showWithoutSetState();
		NN.showWithoutSetState();
		E.showWithoutSetState();
	}

}
