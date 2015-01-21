package shapePackage;

import java.awt.Color;
import java.util.ArrayList;

import main.Board;
import main.NextPiece;
import main.Square;

/**
 *    ____ ____
 *   |NW  |N   |
 *   |____|____|
 *   |W   |C   |
 *   |____|____|
 * @author Tanawat
 *
 */
public class SquareShape extends Shape{

	private Point center, N, W, NW;
	private static Color c = Shape.randomColor();
	
	public SquareShape() {
		center = new Point();
		N = new Point(center.getX(), center.getY()+1);
		W = new Point(center.getX()-1, center.getY());
		NW = new Point(center.getX()-1, center.getY()+1);
		showNext();
	}
	
	public SquareShape(boolean sn) {
		center = new Point();
		N = new Point(center.getX(), center.getY()+1);
		W = new Point(center.getX()-1, center.getY());
		NW = new Point(center.getX()-1, center.getY()+1);
	}
	
	@Override
	public Shape clone() {
		SquareShape s = new SquareShape(true);
		s.center = (Point) center.clone();
		s.N = (Point) N.clone();
		s.W = (Point) W.clone();
		s.NW = (Point) NW.clone();
		return s;
	}
	@Override
	public void rotate() {	}

	@Override
	public void moveLeft() {
		Direction d = Direction.LEFT;
		clear();
		if (W.canMove(d) && NW.canMove(d))
			move(d);
		show();
	}

	@Override
	public void moveRight() {
		Direction d = Direction.RIGHT;
		clear();
		if (center.canMove(d) && N.canMove(d))
			move(d);
		show();
		
	}

	@Override
	public void show() {
		center.show(c);
		N.show(c);
		W.show(c);
		NW.show(c);
	}

	@Override
	public boolean canDrop() {
		return center.canDrop() && W.canDrop();
	}

	@Override
	public boolean checkLine() {
		int count = 0;
		if (N.checkLine())
			++count;
		if (center.checkLine())
			++count;
		Board.incrementScore(count);
		return true;
	}

	@Override
	public void move(Direction d) {
		center.move(d);
		N.move(d);
		W.move(d);
		NW.move(d);
	}

	@Override
	public void clear() {
		center.clear();
		N.clear();
		W.clear();
		NW.clear();
	}

	@Override
	public void showNext() {
		NextPiece.clear();
		Point C = new Point(1,1);
		Point NW = new Point(C.getX()-1, C.getY()+1);
		Point N = new Point(C.getX(), C.getY()+1);
		Point W = new Point(C.getX()-1, C.getY());
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(C);
		points.add(NW);
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
		N.setSafe();
		W.setSafe();
		NW.setSafe();
	}

	@Override
	public void showWithoutSetState() {
		// TODO Auto-generated method stub
		center.showWithoutSetState();
		N.showWithoutSetState();
		W.showWithoutSetState();
		NW.showWithoutSetState();
	}
	
}
