import processing.core.PApplet;

public class UsingProcessing extends PApplet {
	int[] point1 = { 0, 0 };
	int[] point2 = { 0, 100};
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
	Sector[][] sectors = { { sector1, sector2, sector3 }, 
			{ sector4, sector5, sector6 },
			{ sector7, sector8, sector9 } };

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
		System.out.println();
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
		mouseClick();
		printItems();
	}

	public void printItems() {
		for (int i = 0; i < sectors.length; i++) {
			for (int j = 0; j < sectors[0].length; j++) {
				if (sectors[i][j].getXorO() == 1) {
					int[] point = sectors[i][j].getMidPoint();
					System.out.println(point[1]);
					ellipse(point[0], point[1], 100, 100);
				}

				else if (sectors[i][j].getXorO() == 2) {
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

	public void mouseClick() {
		if (mouseButton == LEFT) {
			int[] coord = { mouseX, mouseY };
			Sector newSec = getSector(coord);
			newSec.setXorO(1);
			System.out.println(newSec.getWhatSector());
			
		}
		
		else if (mouseButton == RIGHT) {
			int[] coord = { mouseX, mouseY };
			Sector newSec = getSector(coord);
			newSec.setXorO(2);
			System.out.println(newSec.getWhatSector());
			
		}
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

}