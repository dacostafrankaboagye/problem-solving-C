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

/*
safe - 

*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FindSafeTriangle {
    public static void main(String[] args) throws FileNotFoundException {
        // read in file
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        // read in points
        List<Point> points = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(",");
            double x = Double.parseDouble(line[0]);
            double y = Double.parseDouble(line[1]);
            points.add(new Point(x, y));
        }
        // find largest triangle
        Triangle largestTriangle = findLargestTriangle(points);
        // write results to file
        largestTriangle.writeToFile();
    }

    private static Triangle findLargestTriangle(List<Point> points) {
        Triangle largestTriangle = new Triangle(null, null, null);
        // sort points by x-coordinate
        points.sort((p1, p2) -> Double.compare(p1.x, p2.x));
        // loop through points
        for (int i = 0; i < points.size(); i++) {
            Point point1 = points.get(i);
            // loop through points after point1
            for (int j = i + 1; j < points.size(); j++) {
                Point point2 = points.get(j);
                // loop through points after point2
                for (int k = j + 1; k < points.size(); k++) {
                    Point point3 = points.get(k);
                    // create triangle
                    Triangle triangle = new Triangle(point1, point2, point3);
                    // check if triangle is valid
                    if (triangle.isValid()) {
                        // check if triangle is larger than largest triangle
                        if (triangle.area() > largestTriangle.area()) {
                            largestTriangle = triangle;
                        }
                    }
                }
            }
        }
        return largestTriangle;
    }

    static class Point {
        double x;
        double y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Triangle {
        Point p1;
        Point p2;
        Point p3;
        Triangle(Point p1, Point p2, Point p3) {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
        }
        // check if triangle is valid
        boolean isValid() {
            return (p1 != null && p2 != null && p3 != null);
        }
        // calculate area of triangle
        double area() {
            if (!isValid()) return 0;
            return Math.abs(p1.x * (p2.y - p3.y) +
                    p2.x * (p3.y - p1.y) +
                    p3.x * (p1.y - p2.y)) / 2;
        }
        // write triangle to file
        void writeToFile() throws FileNotFoundException {
            if (!isValid()) return;
            // create output file
            File file = new File("safetriangle.csv");
            // write to file
            PrintWriter pw = new PrintWriter(file);
            pw.println("X,Y");
            pw.println(p1.x + "," + p1.y);
            pw.println(p2.x + "," + p2.y);
            pw.println(p3.x + "," + p3.y);
            pw.close();
        }
    }
}
	
	
