package shapePackage;

import java.awt.Color;

public abstract class Shape {

	public enum Direction {
		DOWN, LEFT, RIGHT
	};

	public static Color[] colorLookup = { Color.RED, Color.GREEN, Color.BLUE,
			Color.YELLOW, Color.MAGENTA, Color.PINK, Color.CYAN, Color.ORANGE };
	// new LShape(), new SShape(), new TShape(), new LongShape(),
	// new SquareShape(), new LShapeInverse(), new SShapeInverse()};
	private static final int numShape = 7;
	private final static boolean test = false;

	public void drop() {
		if (canDrop()) {
			clear();
			move(Direction.DOWN);
			show();
		}
	}

	public abstract void rotate();

	public abstract void moveLeft();

	public abstract void moveRight();

	public abstract void show();

	public abstract boolean canDrop();

	public abstract boolean checkLine();

	public abstract void move(Direction d);

	public abstract void clear();
	
	public abstract void showNext();

	public static Shape getNewShape() {
		int rand = (test==true)? numShape-1: (int) (Math.random() * numShape);
		if (rand == 1)
			return new SShape();
		else if (rand == 2)
			return new TShape();
		else if (rand == 3)
			return new LongShape();
		else if (rand == 4)
			return new SquareShape();
		else if (rand == 5)
			return new LShapeInverse();
		else if (rand == 6) 
			return new SShapeInverse();
		else
			return new LShape();
	}

	public static Color randomColor() {
		return colorLookup[(int) (Math.random() * colorLookup.length)];
	}

}
