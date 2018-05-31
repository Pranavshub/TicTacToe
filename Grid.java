
public class Grid {
	   public int [][] board;
	   public Grid (int [][] board){
		   //creates a multidimensional array representing the board
		   //where 0 is blank, 1 is x, and 2 is o.
	        this.board= board;
	    }
	 

	public int win() {
		//horizontal/vertical win checking code
		
		   //checks if there is a winning horizontal or vertical pattern and returns who won (1 or 2), or if it was a 
		//draw (returns -1) or if there is no win or no draw (0)
		   int horiz;
		   for (int row=0; row<board.length; row++) {
			   horiz=0; //resets to 0 for each new row
			   for (int col=0; col<board[row].length; col++) {
				   if (board[row][0]==board[row][col]&&board[row][0]!=0) {
					   horiz++;
				   }
				   if (horiz==3) {
					 if (board[row][0]==1) {
						 return 1;
					 }
					 
					 else {
						 return 2;
					 }
					 //if the first number(which represents either an x or o, matches
					 //with the rest of the numbers in the row (aka if the entire row has
					 //the same number), return the number which won using horizontal pattern
				   }
				   
				   if (row==0) {//goes through this only with the first row
					   if (board[0][col]==board[1][col] && board[0][col]==board[2][col]&&board[0][col]!=0) {
						   if (board[0][col]==1) {
							   return 1;
						   }
						   else {
							   return 2;
						   }
						   //if a number in the first row is the same as the two below it,
						   //return the number/ x or o that won with a vertical pattern
					   }
				   }
			   }
		   }
		   
		   //diagonal win checking code
		   
		 //if the middle number equals the top right and bottom left numbers or equals the 
		   //top left and bottom right numbers, return true to show a winning diagonal
		   //pattern
		   int mid= board[1][1];
		   if (mid== board [0][0] && mid== board [2][2]&&mid!=0) { //top left and bottom right
			   if (mid==1) {
				   return 1;
			   }
			   else {
				   return 2;
			   }
		   }
		   else if (mid==board[0][2]&& mid==board[2][0]&&mid!=0) { //top right and bottom left
			   if (mid==1) {
				   return 1;
			   }
			   else {
				   return 2;
			   }
		   }
		   
		   //draw checking code
		   
		   //checks if there are no remaining spaces but no win (a draw) and returns -1 if so
		   int zeros=9;
			for (int []arr:board) {
				for (int a: arr) {
					if (a==0) {
						break;
					}
					else {
						zeros--;
					}
				}
			}
			
			if (zeros==0) {
				return -1;
			}
			
			//no win code
		   return 0;//if there are no winning patterns found, return 0
		   }
	   
	   }
	   
