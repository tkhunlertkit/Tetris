package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import shapePackage.Point;

public class NextPiece extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Square[][] board = new Square[4][3];
	
	public NextPiece() {
		setLayout(new GridLayout(4,3));
		for (int i=3; i>=0; --i) {
			for (int j=0; j<3; ++j) {
				board[i][j] = new Square();
				add(board[i][j]);
			}
		}
	}
	
	public static void showPiece(Color c, ArrayList<Point> points) {
		for (Point p: points) {
			board[p.getY()][p.getX()].show(c);
		}
	}
	
	public static void clear() {
		for (int i=3; i>=0; --i) {
			for (int j=0; j<3; ++j) {
				board[i][j].clear();
			}
		}
	}

}
