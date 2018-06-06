import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import processing.core.PApplet;

public class UsingProcessing extends PApplet {
	//All Sectors that divide the board.
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
	//Difficulty sectors
	int[] point13 = { 0, 300 };
	int[] point14 = { 0, 400 };
	Sector easy = new Sector(point13, point14, 100, 10);
	int[] point15 = { 100, 300 };
	int[] point16 = { 100, 400 };
	Sector medium = new Sector(point15, point16, 100, 100);
	int[] point17 = { 200, 300 };
	int[] point18 = { 200, 400 };
	Sector hard = new Sector(point17, point18, 100, 1000);
	//array of sectors
	Sector[][] sectors = { { sector1, sector2, sector3 }, { sector4, sector5, sector6 },
			{ sector7, sector8, sector9 } };
	//array of difficulties
	Sector[] difficulties = { easy, medium, hard };
	//This manages whose turn it is
	boolean moveOk;
	//This is the computers chosen move;
	Point compMove;
	int counter;
	int num = 100;

	public static void main(String[] args) {
		PApplet.main("UsingProcessing");
	}

	public void settings() {
		size(300, 400);
	}

	public void setup() {
		background(135);
	}

	public void draw() {
		//Draws the board
		strokeWeight(10);
		stroke(0, 255, 0);
		line(0, 0, 300, 0);
		line(0, 0, 0, 400);
		line(300, 400, 300, 0);
		line(300, 300, 0, 300);
		line(0, 400, 300, 400);
		for (int i = 0; i <= 300; i += 100) {
			line(0, i, 400, i);
			line(i, 0, i, 400);

		}
		strokeWeight(10);
		fill(0, 0, 255);
		stroke(0, 0, 255);
		ellipse(50,350,10,10);
		ellipse(150,350,30,30);
		ellipse(250,350,50,50);
		//Says that it is okay to click the mouse after it is unclicked
		if (!mousePressed) {
			moveOk = true;
			int[][] board = getBoard();
			ArrayList<Point> availPoints = getAvailableSpaces(board);
			//Checks if it is a draw
			if (!isFinished(board, 2) && !isFinished(board, 1) && availPoints.isEmpty() && counter == 0) {
				JFrame Frame = new JFrame("Result");
				JLabel label = new JLabel("Draw :|", JLabel.CENTER);
				Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Frame.setSize(500, 500);
				Frame.add(label);
				Frame.setVisible(true);
				counter++;
			}
		}
		//When the mouse is pressed this runs.
		if (mousePressed) {
			int[] coord = { mouseX, mouseY };
			//Sets the difficulty.
			if (mouseX > 300 || mouseY > 300) {
				Sector difficulty = getWhatDifficulty(coord);
				if(difficulty.getWhatSector() == 10) {
					num = 10;
					difficulty.setXorO(3);
					resetDifficulties();
					printItems();
				}
				else if(difficulty.getWhatSector() == 100) {
					num = 100;
					difficulty.setXorO(3);
					resetDifficulties(); 
					printItems();
				}
				else if(difficulty.getWhatSector() == 1000) {
					num = 1000;
					difficulty.setXorO(3);
					resetDifficulties();
					printItems();
				}
				printItems();
			}
			//Starts the game
			else {
				playGame();
				moveOk = false;
			}
		}
	}
	//If a different difficulty is chosen during the game it gets rid of the other ones.
	public void resetDifficulties() {
		for(int i = 0; i < difficulties.length; i ++) {
			if(num != difficulties[i].getWhatSector()) {
				difficulties[i].setXorO(0);
			}
		}
	}
	
	//Calls player turn
	public void playGame() {
		if (moveOk) {
			printItems();
			playerTurn();
			printItems();
		}
	}
	
	
	public void playerTurn() {
		//Gets where the mouse is
		int[] coord = {mouseX, mouseY};
		Sector newSec = getSector(coord);
		//Sets that area to a player area
		newSec.setXorO(2);
		int[][] board = getBoard();
		//Checks if they won
		if (isFinished(board, 2)) {
			JFrame Frame = new JFrame("Result");
			JLabel label = new JLabel("You Won :(", JLabel.CENTER);
			Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Frame.setSize(500, 500);
			Frame.add(label);
			Frame.setVisible(true);
		}
		//calls computerTurn with the chosen difficulty
		computerTurn(num);
	}

	public void computerTurn(int level) {
		if (moveOk) {
			
			int[][] board = getBoard();
			//hard
			if(level == 1000) {
				//calls minimax 
				int result = minimax(board, 1, 0);
				int x = compMove.x;
				int y = compMove.y;
				Sector newSec = sectors[x][y];
				//chooses the area to place
				newSec.setXorO(1);
				board = getBoard();
				printItems();
			}
			//medium
			if(level == 100) {
				//calls easy minimax
				int result = easyMinimax(board, 1, 0);
				int x = compMove.x;
				int y = compMove.y;
				//chooses area
				Sector newSec = sectors[x][y];
				newSec.setXorO(1);
				board = getBoard();
				printItems();
			}
			//easy
			if(level == 10) {
				//chooses the next available point
				int x;
				int y;
				ArrayList<Point> spaces = getAvailableSpaces(board);
				Point point = spaces.get(0);
				x = point.x;
				y = point.y;
				Sector newSec = sectors[x][y];
				newSec.setXorO(1);
				printItems();
			}
			//checks if computer has won
			if (isFinished(board, 1)) {
				JFrame Frame = new JFrame("Result");
				JLabel label = new JLabel("I Won :)", JLabel.CENTER);
				Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Frame.setSize(500, 500);
				Frame.add(label);
				Frame.setVisible(true);
			}
		}
	}
	//gets which difficulty the player has chosen from the difficulty array.
	public Sector getWhatDifficulty(int[] coord) {
		int[] Point1 = { 0, 300 };
		int[] Point2 = { 0, 400 };
		Sector newsec = new Sector(Point1, Point2, 100, 10);
		for (int i = 0; i < difficulties.length; i++) {
			if (difficulties[i].inSector(coord)) {
				newsec = difficulties[i];
			}
		}
		return newsec;
	}
	
	//Prints things like O and X
	public void printItems() {
		stroke(0,255,0);
		for (int i = 0; i < sectors.length; i++) {
			for (int j = 0; j < sectors[0].length; j++) {
				if (sectors[i][j].getXorO() == 2) {
					//If player print O
					int[] point = sectors[i][j].getMidPoint();
					fill(135);
					ellipse(point[0], point[1], 100, 100);
				}

				else if (sectors[i][j].getXorO() == 1) {
					//If AI print X
					int[] point1 = sectors[i][j].getDrawingPoint1();
					int[] point2 = sectors[i][j].getDrawingPoint2();
					int[] point3 = sectors[i][j].getDrawingPoint3();
					int[] point4 = sectors[i][j].getDrawingPoint4();

					line(point1[0], point1[1], point4[0], point4[1]);
					line(point2[0], point2[1], point3[0], point3[1]);
				}
			}
		}
		//Highlights the difficulty that was chosen.
		for(int i = 0; i < difficulties.length; i ++) {
			if(difficulties[i].getXorO() == 3){
				fill(0,255,0);
				int x = difficulties[i].getDrawingPoint1()[0];
				int y = difficulties[i].getDrawingPoint1()[1];
				rect(x,y,100,100);
			}
			//Unhighlights difficulties that weren't chosen.
			else if(difficulties[i].getXorO() == 0){
				fill(135);
				int x = difficulties[i].getDrawingPoint1()[0];
				int y = difficulties[i].getDrawingPoint1()[1];
				rect(x,y,100,100);
			}
		}
	}

	public boolean isFinished(int[][] b, int player) { // code commented out can be put back in if the code needs to be
														// modified to return
		// who won
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

					/*
					 * else { return 2; }
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
						/*
						 * else { return 2; }
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
			/*
			 * else { return 2; }
			 */
		} else if (mid == b[0][2] && mid == b[2][0] && mid != 0) { // top right and bottom left
			if (mid == player) {
				return true;
			}
			/*
			 * else { return 2; }
			 */
		}

		// draw checking code

		// checks if there are no remaining spaces but no win (a draw) and returns -1 if
		// so
		int zeros = 9;
		for (int row = 0; row < b.length; row++) {
			int[] arr = b[row];
			for (int col = 0; col < arr.length; col++) {
				int num = b[row][col];
				if (num == 0) {
					break;
				} else {
					zeros--;
				}
			}
		}

		if (zeros == 0) {
			return false;
			// return -1;
		}

		// no win code
		// return 0;// if there are no winning patterns found, return 0
		return false;
	}
	//Returns an array of all available points
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
	//Minimax function
	public int minimax(int[][] board, int player, int depth) {
		ArrayList<Point> availPoints = getAvailableSpaces(board);
		//Checks if someone has won
		//if AI wins
		if (isFinished(board, 1)) {
			//returns a score based on who has won and how far the game has progressed
			return 10 - depth;
			//if player wins
		} else if (isFinished(board, 2)) {
			//returns a score based on who has won and how far the game has progressed
			return -10 - depth;
			//if draw
		} else if (availPoints.isEmpty()) {
			//returns a score based on who has won and how far the game has progressed
			return 0 + depth;
		}
		//makes an array of possible moves
		ArrayList<Point> moves = new ArrayList<Point>();
		for (int i = 0; i < availPoints.size(); i++) {
			int x = availPoints.get(i).x;
			int y = availPoints.get(i).y;
			Point move = new Point(x, y);
			//adds possible moves to the array.
			moves.add(move);
			//Places that move on the board
			if (canPlaceMove(move)) {
				board[x][y] = player;
			}
			//if it is the player it calls minimax until it reaches an endgame state
			if (player == 2) {
				depth++;
				int result = minimax(board, 1, depth);
				move.setScore(result);
			//if it is the AI it still calls minimax until it reaches an end game state
			} else if (player == 1) {
				depth++;
				int result = minimax(board, 2, depth);
				move.setScore(result);
			}
			//resets the board space for future simulations
			board[x][y] = 0;
		}
		
		Point bestMove = new Point(0, 0);
		//if it is the AI then it chooses the move with the highest score.
		if (player == 1) {
			int bestScore = Integer.MIN_VALUE;
			//goes through all moves
			for (int i = 0; i < moves.size(); i++) {
				Point temp = moves.get(i);
				//gets the score and compares it with bestScore
				if (temp.score > bestScore) {
					//if it is bigger than it changes the best score and move to the chosen move
					bestScore = temp.score;
					bestMove = temp;
					bestMove.setScore(temp.score);
				}
			}
		}
		//if it is the player then it chooses the move with the lowest score.
		else if (player == 2) {
			int bestScore = Integer.MAX_VALUE;
			for (int i = 0; i < moves.size(); i++) {
				Point temp = moves.get(i);
				//gets the score and compares it with bestScore
				if (temp.score < bestScore) {
					//if it is smaller than it changes the best score and move to the chosen move
					bestScore = temp.score;
					bestMove = temp;
					bestMove.setScore(temp.score);
				}
			}
		}
		//Assigns compMove to bestMove
		compMove = bestMove;
		//Returns best score
		return bestMove.score;
	}
	
	//Exact same thing as minimax except for one
	public int easyMinimax(int[][] board, int player, int depth) {
		ArrayList<Point> availPoints = getAvailableSpaces(board);
		if (isFinished(board, 1)) {
			return 10 - depth;
		} else if (isFinished(board, 2)) {
			//This adds the depth instead of subtracting it. This leads to the computer sometimes making mistakes when choosing a best move.
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
			if (canPlaceMove(move)) {
				board[x][y] = player;
			}
			if (player == 2) {
				depth++;
				int result =  easyMinimax(board, 1, depth);
				move.setScore(result);

			} else if (player == 1) {
				depth++;
				int result =  easyMinimax(board, 2, depth);
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

		else if (player == 2) {
			int bestScore = Integer.MAX_VALUE;
			for (int i = 0; i < moves.size(); i++) {
				Point temp = moves.get(i);
				if (temp.score < bestScore) {
					bestScore = temp.score;
					bestMove = temp;
					bestMove.setScore(temp.score);
				}
			}
		}

		compMove = bestMove;
		return bestMove.score;
	}
	//Gets the sector that the mouse is in.
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
	//Returns a 3x3 array of the current board's state
	public int[][] getBoard() {
		int[][] board = new int[3][3];
		for (int i = 0; i < sectors.length; i++) {
			for (int j = 0; j < sectors[0].length; j++) {
				board[i][j] = sectors[i][j].getXorO();
			}
		}
		return board;
	}
	//Checks if the move can be placed.
	public boolean canPlaceMove(Point point) {
		int x = point.x;
		int y = point.y;
		Sector newSec = sectors[x][y];
		if (newSec.getXorO() == 0) {
			return true;
		} else {
			return false;
		}
	}
}