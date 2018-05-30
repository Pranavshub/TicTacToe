import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.*;
import javax.swing.*;
import java.util.*;
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
	Point computerMove;
	int[][] board = new int[3][3];

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
		 playerTurn();
		 printItems();
		 int board[][] = getBoard();
		 if(isFinished(board,2)) {
			 System.exit(1);
		 }
		 minimax(board,1);
		 computerTurn();
		 printItems();
		 if(isFinished(board,1)) {
			 System.exit(1);
		 }
	}
	
	public void test() {
		for(int i = 0; i < getAvailableSpaces().size(); i++) {
			System.out.println(getAvailableSpaces().get(i));
		}
	}
	
	public void computerTurn() {
		int x = computerMove.x;
		int y = computerMove.y;
		Sector newSec = sectors[x][y];
		newSec.setXorO(2);
		
	}

	public boolean isFinished(int[][] board, int player) {
		int horiz = 0;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][0] == board[row][col] && board[row][0] != 0 && board[row][0] == player) {
					horiz++;
				}
				if (horiz == 3) {
					return true;
				}
				if (row == 0) {
					if (board[0][col] == board[1][col] && board[0][col] == board[2][col] && board[0][col] != 0
							&& board[0][col] == player) {
						return true;
					}
				}
			}
		}
		int mid = board[1][1];
		if (mid == board[0][0] && mid == board[2][2] && mid != 0 && mid == player) {
			return true;
		} else if (mid == board[0][2] && mid == board[2][0] && mid != 0 && mid == player) {
			return true;
		}
		return false;
	}

	public ArrayList<Point> getAvailableSpaces() {
		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 0; i < sectors.length; i++) {
			for (int j = 0; j < sectors[0].length; j++) {
				Point temp = new Point(i,j);
				if (sectors[i][j].getXorO() == 0) {
					points.add(temp);
				}
			}
		}
		return points;
	}

	public int minimax(int[][] board,int player) {
		ArrayList<Point> availPoints = getAvailableSpaces();
		int gameResult = 0;
		if (isFinished(board,1)) {
			return gameResult =  10;
		}
		else if(isFinished(board,2)) {
			return gameResult = -10;
		}
		else if(availPoints.isEmpty()) {
			return gameResult =  0;
		}
		ArrayList<Point> moves = new ArrayList<Point>();
		for(int i = 0 ; i < availPoints.size(); i ++) {
			int x = availPoints.get(i).x;
			int y = availPoints.get(i).y;
			Point move = new Point(x,y);
			moves.add(move);
			int temp = board[x][y];
			board[x][y] = player;
			
			if(player == 1) {
				int result = minimax(board, 2);
				move.setScore(result);
				
			}
			else if(player == 2) {
				int result = minimax(board,1);
				move.setScore(result);
			}
			board[x][y] = temp;
		}
		
		Point bestMove = new Point(0,0);
		if(player == 1) {
			int bestScore = Integer.MIN_VALUE;
			for(int i = 0; i < moves.size(); i++) {
				Point temp = moves.get(i);
				if(temp.score > bestScore) {
					bestScore = temp.score;
					bestMove = temp;
					bestMove.setScore(temp.score);
				}
			}
		}
		
		else {
			int bestScore = Integer.MAX_VALUE;
			for(int i = 0; i < moves.size(); i ++) {
				Point temp = moves.get(i);
				if(temp.score < bestScore) {
					bestScore = temp.score;
					bestMove = temp;
					bestMove.setScore(temp.score);
				}
			}
		}
		
		computerMove = bestMove;
		return bestMove.score;
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

	public void playerTurn() {
		if (mouseButton == LEFT) {
			int[] coord = { mouseX, mouseY };
			Point point = new Point(coord[0], coord[1]);
			Sector newSec = getSector(coord);
			newSec.setXorO(2);
		}
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
}