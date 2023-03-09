import java.io.FileReader;
import java.util.*;

public class FindSafeTriangle {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Error: Must provide an input file");
			return;
		}

		// Read points from input file
		List<Point> points = readPointsFromFile(args[0]);

		// Find the largest triangle
		Triangle triangle = findLargestTriangle(points);

		// Write the vertices of the triangle to output file
		writeTriangleToFile(triangle);
	}

	// Read points from the input file
	private static List<Point> readPointsFromFile(String filename) {
		List<Point> points = new ArrayList<>();
		try {
			FileReader fr = new FileReader(filename);
			Scanner sc = new Scanner(fr);
			sc.nextLine(); // Skip the first line

			while (sc.hasNext()) {
				String[] line = sc.nextLine().split(",");
				points.add(new Point(Double.parseDouble(line[0]), Double.parseDouble(line[1])));
			}
		} catch (Exception e) {
			System.out.println("Error: Could not read from file");
			System.exit(1);
		}

		return points;
	}

	// Find the largest triangle that can be formed by the given points
	private static Triangle findLargestTriangle(List<Point> points) {
		Triangle largestTriangle = null;
		double largestArea = 0;

		for (int i = 0; i < points.size(); i++) {
			for (int j = i + 1; j < points.size(); j++) {
				for (int k = j + 1; k < points.size(); k++) {
					Triangle triangle = new Triangle(points.get(i), points.get(j), points.get(k));
					double area = triangle.calculateArea();
					if (area > largestArea) {
						largestTriangle = triangle;
						largestArea = area;
					}
				}
			}
		}

		return largestTriangle;
	}

	// Write the vertices of the triangle to the output file
	private static void writeTriangleToFile(Triangle triangle) {
		try {
			FileWriter fw = new FileWriter("safetriangle.csv");
			fw.write("X,Y\n");
			fw.write(triangle.getA().getX() + "," + triangle.getA().getY() + "\n");
			fw.write(triangle.getB().getX() + "," + triangle.getB().getY() + "\n");
			fw.write(triangle.getC().getX() + "," + triangle.getC().getY() + "\n");
			fw.close();
		} catch (Exception e) {
			System.out.println("Error: Could not write to file");
			System.exit(1);
		}
	}
}

class Point {
	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}

class Triangle {
	private Point a;
	private Point b;
	private Point c;

	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public double calculateArea() {
		// Calculate the area using Heron's formula
		double ab = Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
		double bc = Math.sqrt(Math.pow(c.getX() - b.getX(), 2) + Math.pow(c.getY() - b.getY(), 2));
		double ca = Math.sqrt(Math.pow(a.getX() - c.getX(), 2) + Math.pow(a.getY() - c.getY(), 2));
		double s = (ab + bc + ca) / 2;
		return Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));
	}

	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}

	public Point getC() {
		return c;
	}
}

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class FindSafePolygon {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Error: Must provide an input file");
			return;
		}

		// Read points from input file
		List<Point> points = readPointsFromFile(args[0]);

		// Find the largest convex polygon
		ConvexPolygon polygon = findLargestConvexPolygon(points);

		// Write the vertices of the polygon to the output file
		writePolygonToFile(polygon);
	}

	// Read points from the input file
	private static List<Point> readPointsFromFile(String filename) {
		List<Point> points = new ArrayList<>();
		try {
			FileReader fr = new FileReader(filename);
			Scanner sc = new Scanner(fr);
			
