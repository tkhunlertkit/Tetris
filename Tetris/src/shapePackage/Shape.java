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
	private Shape ghost = null;
	public void drop() {
		if (canDrop()) {
			clear();
			move(Direction.DOWN);
			show();
		}
	}
	
	/**
	 * get the copy instance of the shape with the exact same coordinate
	 * return another instance of the shape
	 */
	@Override
	public abstract Shape clone();
	
	/**
	 * create a ghost copy of the shape and display it on the board
	 */
	public void showGhost() {
		// TODO Auto-generated method stub
		if (ghost != null) ghost.clear();
		ghost = this.clone();
		//ghost.setColor(Color.WHITE);
		while(ghost.canDrop())
			ghost.move(Direction.DOWN);
		ghost.showWithoutSetState();
	}

	/**
	 * show components with a safe state
	 */
	public abstract void showWithoutSetState();

	/**
	 * rotate the shape
	 */
	public abstract void rotate();

	/**
	 * move the shape left by one unit
	 */
	public abstract void moveLeft();

	/**
	 * move the shape right by one unit
	 */
	public abstract void moveRight();

	/**
	 * show the shape on the board
	 */
	public abstract void show();

	/**
	 * check if the shape could be drop
	 * @return true of the shape could be dropped
	 */
	public abstract boolean canDrop();

	/**
	 * check if the shape complete any lines possible
	 * This will also increment the score of the board if at least one line
	 * is cleared.
	 * @return true if at least one line could be cleared
	 */
	public abstract boolean checkLine();

	/**
	 * move the shape one unit in the direction <code>d</code>
	 * @param d direction of the movement (DOWN, LEFT, or RIGHT)
	 */
	public abstract void move(Direction d);

	/**
	 * clear the shape off the board
	 */
	public abstract void clear();
	
	/**
	 * Show the next shape on the display
	 */
	public abstract void showNext();

	/**
	 * random a new shape
	 * @return randomized shape
	 */
	public static Shape getNewShape() {
		@SuppressWarnings("unused")
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

	/**
	 * get a random color from a list of (red, green blue, yellow, 
	 * magenta, pink cyan orange) 
	 * @return randomized color
	 */
	public static Color randomColor() {
		return colorLookup[(int) (Math.random() * colorLookup.length)];
	}
	
	public abstract void setColor(Color c);
	
	public abstract void setSafe();


	/**
	 * clear ghost shape if it exists
	 */
	public void clearGhost() {
		ghost.clear();
		
	}

	

}
