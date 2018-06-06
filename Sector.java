import processing.core.PApplet;
import java.util.*;

public class Sector extends PApplet {
	private int whatSector;
	//if it is an X sector or and O sector
	private int isXorO;
	private int[][] graph = new int[400][400];
	//starting point for the sector
	private int[] startingPoint;
	//ending point for the sector
	private int[] endingPoint;
	private int length;
	//drawing points
	private int[] drawingPoint1;
	private int[] drawingPoint2;
	private int[] drawingPoint3;
	private int[] drawingPoint4;
	private int[] midPoint;

	public Sector(int[] point1, int[] point2, int dimension, int num) {
		whatSector = num;
		isXorO = 0;
		startingPoint = new int[point1.length];
		endingPoint = new int[point1.length];
		for (int i = 0; i < point1.length; i++) {
			startingPoint[i] = point1[i];
			endingPoint[i] = point2[i];
		}
		length = dimension + startingPoint[0];
		for (int i = startingPoint[0]; i < length; i++) {
			for (int j = startingPoint[1]; j < endingPoint[1]; j++) {
				graph[i][j] = whatSector;
			}
		}
		//defines all of the drawing points
		drawingPoint1 = new int[endingPoint.length];
		drawingPoint2 = new int[endingPoint.length];
		drawingPoint3 = new int[endingPoint.length];
		drawingPoint4 = new int[endingPoint.length];
		midPoint = new int[endingPoint.length];
		drawingPoint1[0] = startingPoint[0];
		drawingPoint1[1] = startingPoint[1];
		drawingPoint2[0] = endingPoint[0];
		drawingPoint2[1] = endingPoint[1];
		drawingPoint3[0] = endingPoint[0] + 100;
		drawingPoint3[1] = endingPoint[1] - 100;
		drawingPoint4[0] = startingPoint[0] + 100;
		drawingPoint4[1] = startingPoint[1] + 100;
		midPoint[0] = drawingPoint2[0] + 50;
		midPoint[1] = drawingPoint2[1] - 50;
	}
	//this prints the entire graph for testing purposes
	public void Print() {
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[0].length; j++) {
				System.out.print(graph[i][j]);
			}
			System.out.println();
		}

	}
	//Gets a point and checks which sector the point is in.
	public boolean inSector(int[] point) {
		if (graph[point[0]][point[1]] != 0) {
			return true;
		} else {
			return false;
		}
	}
	//sets x or o
	public void setXorO(int num) {
		isXorO = num;
	}
	//returns x or o
	public int getXorO() {
		return isXorO;
	}
	//returns which sector
	public int getWhatSector() {
		return whatSector;
	}

	public int[] getStartPoint() {
		return startingPoint;
	}

	public int[] getEndPoint() {
		return endingPoint;
	}

	public int[] getDrawingPoint1() {
		return drawingPoint1;
	}

	public int[] getDrawingPoint2() {
		return drawingPoint2;
	}

	public int[] getDrawingPoint3() {
		return drawingPoint3;
	}

	public int[] getDrawingPoint4() {
		return drawingPoint4;
	}

	public int[] getMidPoint() {
		return midPoint;
	}

}
