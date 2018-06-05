import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import processing.core.PApplet;

public class UsingProcessing extends PApplet {
	int count = 0;
	int[] point1 = { 0, 0 };
	int[] point2 = { 0, 100 };
	Sector sector1 = new Sector(point1, point2, 100, 1);
	int[] point3 = { 0, 200 };
	Sector sector4 = new Sector(point2, point3, 100, 4);
	int[] point4 = { 0, 300 };
	Sector sector7 = new Sector(point3, point4, 100, 7);
	int[] point5 = { 100, 0 };
	int[] point6 = { 100, 100 };
	Sector sector2 = new Sector(point5, point6, 100, 2);
	int[] point7 = { 100, 200 };
	Sector sector5 = new Sector(point6, point7, 100, 5);
	int[] point8 = { 100, 300 };
	Sector sector8 = new Sector(point7, point8, 100, 8);
	int[] point9 = { 200, 0 };
	int[] point10 = { 200, 100 };
	Sector sector3 = new Sector(point9, point10, 100, 3);
	int[] point11 = { 200, 200 };
	Sector sector6 = new Sector(point10, point11, 100, 6);
	int[] point12 = { 200, 300 };
	Sector sector9 = new Sector(point11, point12, 100, 9);
	Sector[][] sectors = { { sector1, sector2, sector3 }, { sector4, sector5, sector6 },
			{ sector7, sector8, sector9 } };
	boolean moveOk;
	Point compMove;
	int counter;
	public static void main(String[] args) {
		PApplet.main("UsingProcessing");
	}

	public void settings() {
		size(300, 300);
	}

	public void setup() {
		background(135);
	}

	public void draw() {
		strokeWeight(10);
		stroke(0, 255, 0);
		line(0, 0, 300, 0);
		line(0, 0, 0, 300);
		line(300, 300, 300, 0);
		line(300, 300, 0, 300);
		for (int i = 0; i <= 300; i += 100) {
			line(0, i, 300, i);
			line(i, 0, i, 300);

		}
		
		if (!mousePressed) {
			moveOk = true;
			int[][] board = getBoard();
			ArrayList<Point> availPoints = getAvailableSpaces(board);
			if(!isFinished(board, 2) && !isFinished(board, 1) && availPoints.isEmpty() && counter == 0){
				JFrame Frame  = new JFrame("Result");
				JLabel label = new JLabel("Draw :|", JLabel.CENTER);
				Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Frame.setSize(500, 500);
				Frame.add(label);
				Frame.setVisible(true);
				counter ++;
			}
		}
		if (mousePressed) {
			playGame();
			moveOk = false;
		}
	}

	public void playGame() {
		if(moveOk) {
			printItems();
			playerTurn();
			printItems();
		}
	}
	
	public void computerTurn() {
		if(moveOk) {
			int[][] board = getBoard();
			int result = minimax(board, 1, 0);
			int x = compMove.x;
			int y = compMove.y;
			Sector newSec = sectors[x][y];
			newSec.setXorO(1);
			System.out.println(result);
			board = getBoard();
			printItems();
			if(isFinished(board, 1)) {
				JFrame Frame  = new JFrame("Result");
				JLabel label = new JLabel("I Won :>", JLabel.CENTER);
				Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Frame.setSize(500, 500);
				Frame.add(label);
				Frame.setVisible(true);
			}
		}
	}

	public void playerTurn() {
		int[] coord = { mouseX, mouseY };
		Sector newSec = getSector(coord);
		newSec.setXorO(2);
		int[][] board = getBoard();
		System.out.println(Arrays.deepToString(board));
		System.out.println(isFinished(board,2));
		if(isFinished(board, 2)) {
			JFrame Frame  = new JFrame("Result");
			JLabel label = new JLabel("You Won :<", JLabel.CENTER);
			Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Frame.setSize(500, 500);
			Frame.add(label);
			Frame.setVisible(true);
		}
		computerTurn();	
	}

	public void printItems() {
		for (int i = 0; i < sectors.length; i++) {
			for (int j = 0; j < sectors[0].length; j++) {
				if (sectors[i][j].getXorO() == 2) {
					int[] point = sectors[i][j].getMidPoint();
					fill(135);
					ellipse(point[0], point[1], 100, 100);
				}

				else if (sectors[i][j].getXorO() == 1) {
					int[] point1 = sectors[i][j].getDrawingPoint1();
					int[] point2 = sectors[i][j].getDrawingPoint2();
					int[] point3 = sectors[i][j].getDrawingPoint3();
					int[] point4 = sectors[i][j].getDrawingPoint4();

					line(point1[0], point1[1], point4[0], point4[1]);
					line(point2[0], point2[1], point3[0], point3[1]);
				}
			}
		}
	}

	public boolean isFinished(int [][] b,int player) { //code commented out can be put back in if the code needs to be modified to return
		//who won
		// horizontal/vertical win checking code

		// checks if there is a winning horizontal or vertical pattern and returns who
		// won (1 or 2), or if it was a
		// draw (returns -1) or if there is no win or no draw (0)
		int horiz;
		for (int row = 0; row < b.length; row++) {
			horiz = 0; // resets to 0 for each new row
			for (int col = 0; col < b[row].length; col++) {
				if (b[row][0] == b[row][col] && b[row][0] != 0) {
					horiz++;
				}
				if (horiz == 3) {
					if (b[row][0] == player) {
						return true;
					}

					/*else {
						return 2;
					}
					*/
					// if the first number(which represents either an x or o, matches
					// with the rest of the numbers in the row (aka if the entire row has
					// the same number), return the number which won using horizontal pattern
				}

				if (row == 0) {// goes through this only with the first row
					
					if (b[0][col] == b[1][col] && b[0][col] == b[2][col] && b[0][col] != 0) {
						if (b[0][col] == player) {
							return true;
						} 
						/*else {
							return 2;
						}
						*/
						// if a number in the first row is the same as the two below it,
						// return the number/ x or o that won with a vertical pattern
					}
				}
			}
		}

		// diagonal win checking code

		// if the middle number equals the top right and bottom left numbers or equals
		// the
		// top left and bottom right numbers, return true to show a winning diagonal
		// pattern
		int mid = b[1][1];
		if (mid == b[0][0] && mid == b[2][2] && mid != 0) { // top left and bottom right
			if (mid == player) {
				return true;
			} 
			/*else {
				return 2;
			}
			*/
		} else if (mid == b[0][2] && mid == b[2][0] && mid != 0) { // top right and bottom left
			if (mid == player) {
				return true;
			} 
			/*else {
				return 2;
			}
			*/
		}

		// draw checking code

		// checks if there are no remaining spaces but no win (a draw) and returns -1 if
		// so
		int zeros = 9;
		for (int row= 0; row<b.length; row++) {
			int [] arr= b [row];
			for (int col=0; col<arr.length;col++) {
				int num= b[row][col];
				if (num == 0) { 
					break;
				} else {
					zeros--;
				}
			}
		}

		if (zeros == 0) {
			return false;
			//return -1;
		}

		// no win code
		//return 0;// if there are no winning patterns found, return 0
		return false;
	}

	public ArrayList<Point> getAvailableSpaces(int[][] board) {
		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				Point temp = new Point(i, j);
				if (board[i][j] == 0) {
					points.add(temp);
				}
			}
		}
		return points;
	}

	public int minimax(int[][] board, int player, int depth) {
		ArrayList<Point> availPoints = getAvailableSpaces(board);
		if (isFinished(board, 1)) {
			return 10 - depth;
		} else if (isFinished(board, 2)) {
			return -10 + depth;
		} else if (availPoints.isEmpty()) {
			return 0 + depth;
		}
		ArrayList<Point> moves = new ArrayList<Point>();
		for (int i = 0; i < availPoints.size(); i++) {
			int x = availPoints.get(i).x;
			int y = availPoints.get(i).y;
			Point move = new Point(x, y);
			moves.add(move);
			if(canPlaceMove(move)) {
				board[x][y] = player;
			}
			if (player == 1) {
				depth++;
				int result = minimax(board, 2, depth);
				move.setScore(result);

			} else if (player == 2) {
				depth++;
				int result = minimax(board, 1, depth);
				move.setScore(result);
			}
			board[x][y] = 0;
		}

		Point bestMove = new Point(0, 0);
		if (player == 1) {
			int bestScore = Integer.MIN_VALUE;
			for (int i = 0; i < moves.size(); i++) {
				Point temp = moves.get(i);
				if (temp.score > bestScore) {
					bestScore = temp.score;
					bestMove = temp;
					bestMove.setScore(temp.score);
				}
			}
		}
	
		else if(player == 2){
			int bestScore = Integer.MAX_VALUE;
			for (int i = 0; i < moves.size(); i++) {
				Point temp = moves.get(i);
				if (temp.score < bestScore ) {
					bestScore = temp.score;
					bestMove = temp;
					bestMove.setScore(temp.score);
				}
			}  
		}

		compMove = bestMove;
		return bestMove.score;
	}

	public boolean canPlace(int[] coord) {
		if (sectors[coord[0]][coord[1]].getXorO() != 0) {
			return false;
		}
		return true;
	}

	public Sector getSector(int[] coord) {
		Sector newSec = sectors[0][0];
		for (int i = 0; i < sectors.length; i++) {
			for (int j = 0; j < sectors[0].length; j++) {
				if (sectors[i][j].inSector(coord) == true) {
					newSec = sectors[i][j];
				}
			}
		}
		return newSec;
	}

	public int[] getMouseXY() {
		int[] coord = { mouseX, mouseY };
		return coord;
	}

	public int[][] getBoard() {
		int[][] board = new int[3][3];
		for (int i = 0; i < sectors.length; i++) {
			for (int j = 0; j < sectors[0].length; j++) {
				board[i][j] = sectors[i][j].getXorO();
			}
		}
		return board;
	}
	
	public boolean canPlaceMove(Point point){
		int x = point.x;
		int y = point.y;
		Sector newSec = sectors[x][y];
		if(newSec.getXorO() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}