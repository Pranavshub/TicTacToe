
public class Grid {
	   public int [][] board;
	   public Grid (int [][] board){
		   board=new int [3][3];//creates a multidimensional array representing the board
		   //where 0 is blank, 1 is x, and 2 is o.
	        this.board= board;
	    }
	   
	   public boolean win() {
		   //checks if there is a winning horizontal or vertical pattern
		   int horiz=0;
		   for (int row=0; row<board.length; row++) {
			   for (int col=0; col<board[row].length; col++) {
				   if (board[row][0]==board[row][col]&&board[row][0]!=0) {
					   horiz++;
				   }
				   if (horiz==3) {
					 return true;  
					 //if the first number(which represents either an x or o, matches
					 //with the rest of the numbers in the row (aka if the entire row has
					 //the same number), return true to show a winning horizontal pattern
				   }
				   if (row==0) {//goes through this only with the first row
					   if (board[0][col]==board[1][col] && board[0][col]==board[2][col]&&board[0][col]!=0) {
						   return true;
						   //if a number in the first row is the same as the two below it,
						   //return true to show a winning vertical pattern
					   }
				   }
			   }
		   }
		 //if the middle number equals the top right and bottom left numbers or equals the 
		   //top left and bottom right numbers, return true to show a winning vertical
		   //pattern
		   int mid= board[1][1];
		   if (mid== board [0][0] && mid== board [2][2]&&mid!=0) { //top left and bottom right
			   return true;
		   }
		   else if (mid==board[0][2]&& mid==board[2][0]&&mid!=0) { //top right and bottom left
			   return true;
		   }
			   return false;//if there are no winning patterns found, return false
		   }
	   
	 //draws x with graphics and assigns that place on the grid (within the array)
	   //to be 1 to signify an x
	   public void drawX() {
		   
	   }//can this be done in the processing/graphics class?
	   
	   public void drawO() {
		   
	   }
	   
	   }
	   
