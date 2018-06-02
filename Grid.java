
public class Grid {
	public int[][] board;

	public Grid(int[][] board) {
		//creates a multidimensional array representing the board
		// where 0 is blank, 1 is x, and 2 is o.
		this.board = board;
	}

	public boolean win(int [][] b,int player) { 
		// horizontal/vertical win checking code

		// checks if there is a winning horizontal or vertical pattern and returns if the player won, or if there
		// were no winning patterns
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
					// if the first number(which represents either an x or o, matches
					// with the rest of the numbers in the row (aka if the entire row has
					// the same number), return true
				}

				if (row == 0) {// goes through this only with the first row
					
					if (b[0][col] == b[1][col] && b[0][col] == b[2][col] && b[0][col] != 0) {
						if (b[0][col] == player) {
							return true;
						} 
						// if a number in the first row is the same as the two below it,
						// return that the player won
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
		} else if (mid == b[0][2] && mid == b[2][0] && mid != 0) { // top right and bottom left
			if (mid == player) {
				return true;
			} 
		}

		// no win code
		// if there are no winning patterns found, return false
		return false;
	}

}
