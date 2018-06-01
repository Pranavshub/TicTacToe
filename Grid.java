
public class Grid {
	public int[][] board;

	public Grid(int[][] board) {
		//creates a multidimensional array representing the board
		// where 0 is blank, 1 is x, and 2 is o.
		this.board = board;
	}

	public boolean win(int [][] b,int player) { //code commented out can be put back in if the code needs to be modified to return
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

}
