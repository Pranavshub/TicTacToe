import java.util.ArrayList;

public class ComputerTurn extends Grid{
	public int [][] board;
	//constructor
	public ComputerTurn(int [][] board) {
		super(board);//ask!
	}
	//returns the position to place the x to block a win if a win by the opponent
	//is possible (1-9), otherwise returns 0
	public int atRisk(int player) { 
		if (player!=1) {
			player=2;
		} //if not used for the computer to see if it is at an advantage, use this method to check if the human can win
		for (int row=0; row<board.length; row++) {//checks if any rows have two o's and if the computer is at risk
			//of letting the player win
			int midOfRow= board [row][1];
				if (midOfRow==player && midOfRow==board[row][0]&& board[row][2]==0) {
					//if computer needs to place x at end of row
					return (row*3+2)+1;
				}
				
				else if (midOfRow==player && midOfRow==board[row][2] && board [row][0]==0) {
					//if computer needs to place x at beginning of row
					return (row*3)+1;
				}
				
				else if (midOfRow==0 && board[row][0]==board[row][2] && board [row][0]==player) {
					//if computer needs to place x in middle of row
					return row*3+2;
				}
		}
		for(int col=0; col<board[0].length; col++) { //same as before but it checks if there are two vertical o's in a row
			int midOfCol= board [1][col];
			if (midOfCol==player && midOfCol==board[0][col]&& board[2][col]==0) { //if computer needs to place x at
				//bottom of the column
				return 6+col;
			}
			
			else if (midOfCol==player && midOfCol==board[2][col] && board [0][col]==0) { //if the computer needs to place x
				//at top of column
				return col;
			}
			
			else if (midOfCol==0 && board[0][col]==board[2][col] && board [0][col]==player) { //if computer needs to place x in
				//middle of column
				return 3+col;
			}
		}
		
		int mid= board[1][1];
		if (mid==player && board [2][2]==0 && mid==board[0][0]) { //compares middle w top left
			return 9;
		}
		else if (mid==player && board[2][0]==0 && mid==board[0][2]) { //compares middle w top right
			return 7;
		}
		else if (mid==player && board [0][2]==0 && mid==board[2][0]) { //compares middle w bottom left
			return 3;
		}
		else if (mid==player && board [0][0]==0 && mid==board[2][2]) { //compares middle w bottom right
			return 1;
		}  
		else if (mid==0) { //if two diagonals match and mid is blank, return middle position
			if ((board[0][0]==board[2][2] && board [0][0]==player) || (board[0][2]==board[2][0] && board [0][2]==player)) {
				return 5;
			}
		}
		return 0;
	}
	
	//uses the atRisk() method but instead sees if the computer has a place it can put the x to win the game
	public int atAdvantage() {
		return atRisk (1);
	}

	public int remainingSpaces(int [][] grid) { //finds the number of blank spaces left
		int remaining=0;
		for (int[] arr: grid) {
			for (int i:arr) {
				if (i==0) {
					remaining++;
				}
			}
		}
		return remaining;
	}
	
	//returns the best next move (if a win or block of a win is available), or the only remaining spot, or otherwise 
	//just a random blank space on the board
	public int nextMoveMedium() {
		int rem= remainingSpaces (board);
		if (rem==1) { //if there is only one space remaining, locate it and return the spot
			for(int r=0; r<board.length;r++) {
				int [] row= board [r];
				for (int c=0; c<row.length;c++) {
					int i= row[c];
					if (i==0) {
						return (r*3 + c) + 1; //spot (1-9)
					}
				}
			}
		}
		else {
			if (atRisk(1)!=0) { //if there is a way for the computer to win, return that spot it needs to place
				return atRisk(1);
			}
			else if (atRisk(2)!=0) { //otherwise if the computer is at risk of losing, return that spot it needs to place
				return atRisk(2);
			}
			else { //otherwise return a random empty space on the board
				ArrayList <Integer> blanks=new ArrayList <Integer>();
				for(int r=0; r<board.length;r++) {
					int [] row= board [r];
					for (int c=0; c<row.length;c++) {
						int i= row[c];
						if (i==0) {
							blanks.add((r*3 + c)+1);
						}
					}
				}
				return blanks.get((int)(Math.random()*blanks.size()));
			}
		}
	}
	
	public int nextMoveEasy () { //chooses a random blank spot on the board
		ArrayList <Integer> blanks=new ArrayList <Integer>();
		for(int r=0; r<board.length;r++) {
			int [] row= board [r];
			for (int c=0; c<row.length;c++) {
				int i= row[c];
				if (i==0) {
					blanks.add((r*3 + c)+1); //finds indexes of blank spots and puts them into an array list
				}
			}
		}
		return blanks.get((int)(Math.random()*blanks.size())); // chooses a random blank space from the ones found
	}
	
	public int [] toPoint (int level) { //parameter level is 1 for easy mode and 2 for medium mode
		int ret;
		if (level==1) {
			ret= nextMoveEasy(); //calls easy mode
		}
		else{
		ret= nextMoveMedium(); //calls medium mode
		}
		//turns the int returned (ret) into an array representing x and y if the Tic Tac Toe board were a grid
		//in order to work with Pranav's code
		int [] point= new int [2];
		if (ret>0 && ret<4) {
			point [0]= ret;
			point [1]= 1;
		}
		else if (ret>3 && ret<7) {
			point [0]= ret-3;
			point [1]= 2;
		}
		else {
			point [0]= ret-6;
			point [1]= 3;
		}
		return point;
	}
}
