import java.util.*;
public class Point {
	public int x;
	public int y;
	public int score;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "{" + x + ", " + y + "}";
	}
	
	public void setScore(int num) {
		score = num;
	}
}
