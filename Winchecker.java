import java.util.Arrays;
public class Winchecker {
	//make multidimensional arrays for all the stuff below
	static int [][]dwin= {{1,0,2},{2,1,0},{0,2,1}};
	static int [][]vwin= {{2,1,0},{2,0,1},{2,1,0}};
	static int [][]hwin= {{1,1,2},{2,2,2},{0,0,0}};
	static int [][]zerowin= {{0,1,0},{2,0,2},{0,1,0}};
	static int [][]nowin= {{0,2,1},{0,1,2},{1,0,2}};
	
	static Grid d= new Grid(dwin);//diagonal win
	static Grid v= new Grid (vwin);//vertical win
	static Grid h= new Grid (hwin); //horizontal win
	static Grid z= new Grid (zerowin);//win using 0's
	static Grid n= new Grid (nowin);//no win
public static void main (String [] args) {
	System.out.println(d.win()); //all return false????
	System.out.println(Arrays.toString(dwin[0]));
	System.out.println(Arrays.toString(dwin[1]));
	System.out.println(Arrays.toString(dwin[2]));
	System.out.println(v.win());
	System.out.println(h.win());
	System.out.println(z.win());
	System.out.println(n.win());
}
}
