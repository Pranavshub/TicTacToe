
public class ComputerTurn extends Grid{
	public int [][] board;
	//constructor
	public ComputerTurn(int [][] board) {
		super(int [][] board);
	}
	/*//returns the position to place the x to block a win if a win by the opponent
	//is possible, otherwise returns "no"
	public String atRisk() { 
		
	}
	
	//returns the position to place the x if a win is possible, otherwise returns the
	//best possible position to place the x
	public String atAdvantage() {
		
	}
	*/
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
	
	public void nextMove(int[][] grid) { //uses minimax algorithm to get best next position for the computer to do
		int rem= remainingSpaces (grid);
		if (rem==1) {
			for(int r=0; r<grid.length;r++) {
				int [] row= grid [r];
				for (int c=0; c<row.length;c++) {
					int i= row[c];
					if (i==0) {
						grid[r][c]=1;
					}
				}
			}
		}
	}
}
