import java.util.ArrayList;
import java.util.List;

/**
 * QuickHull class
 * 
 * Extends the Algorithm class to start the algorithm for QuickHull
 * 
 */
public class QuickHull extends Algorithm {

	/**
	 * Begins the algorithm 
	 * <b>Preconditions:</b> Collection must not be null.
	 * <b>Postconditions:</b> Calculates the convex hull using the QuickHull
	 * algorithm and then returns the collection of points on the hull.
	 */
	public List<Point> startAlgorithm(List<Point> coordCollection) {
		List<Point> finalHull = new ArrayList<Point>();
		List<Point> leftHull = new ArrayList<Point>();
		List<Point> rightHull = new ArrayList<Point>();

		// Receive the range of X-axis. Automatically a part of the hull.
		// Adds to the hull collection, removes from original collection.
		int[] range = getRangeIndices(coordCollection);
		int minX = range[2];
		int maxX = range[3];
		Point p1 = coordCollection.get(minX);
		Point p2 = coordCollection.remove(maxX);
		coordCollection.remove(p1);
		// p1 and p2 are min and max X values, automatically on hull.
		finalHull.add(p1);
		finalHull.add(p2);
		// Create a line with the two points
		Line line = new Line(p1, p2);
		// loop through point collection
		for (int i = 0; i < coordCollection.size(); i++) {
			// p3 is the current point
			Point p3 = coordCollection.get(i);
			// Find which side point 3 is on of the line made from p1 and p2.
			double side = line.findSide(p3);
			// Split hull into two.
			// If p3 is on left, add to the leftHull collection, else add to
			// rightHull.
			if (side > 0) {
				leftHull.add(p3);
			} else if (side < 0) {
				rightHull.add(p3);
			}
		}

		// Initialize recursive call to the right collection for line p1-p2.
		speedyHull(rightHull, finalHull, line);
		// Reverse the line direction
		line.reversePoints();
		// Initialize recursive call to the left collection for line p2-p1.
		speedyHull(leftHull, finalHull, line);
		return finalHull;
	}

	public void speedyHull(List<Point> set, List<Point> hull,
			Line line) {
		// Get the points from the line.
		Point pointA = line.getPointA();
		Point pointB = line.getPointB();
		// Collections to hold the new points being separated.
		List<Point> setAC = new ArrayList<Point>();
		List<Point> setCB = new ArrayList<Point>();
		int farthestIndex = 0;
		// Base case.
		if (set.size() == 0) {
			return;
		}
		// Get index of the farthest point from the line.
		farthestIndex = farthestPointIndex(set, line);
		// Store farthest point as pointC.
		Point pointC = set.remove(farthestIndex);
		// Add pointC to final hull collection, in the old position of pointB.
		hull.add(hull.indexOf(pointB), pointC);
		// Create new lines for pointA to pointC and pointC to pointB.
		Line lineAC = new Line(pointA, pointC);
		Line lineCB = new Line(pointC, pointB);
		// Loop through current collection size
		for (int i = 0; i < set.size(); i++) {
			// PointD will be current point to test.
			Point pointD = set.get(i);
			// Find which side of line AC pointD is on.
			double side = lineAC.findSide(pointD);
			// if it is on the left of AC, it will be added to AC collection.
			// Otherwise, disregarded.
			if (side < 0) {
				setAC.add(pointD);
			}
			// Find which side of line CB pointD is on.
			side = lineCB.findSide(pointD);
			// if it is on the left of CB, it will be added to CB collection.
			// Otherwise disregarded.
			if (side < 0) {
				setCB.add(pointD);
			}
		}
		// Recursively call the new set for line AC.
		speedyHull(setAC, hull, lineAC);
		// Recursively call the new set for line CB.
		speedyHull(setCB, hull, lineCB);
	}
}
