import java.util.List;

/**
 * Algorithm Class
 * Algorithm is an abstract class which contains methods that
 * may be shared with multiple algorithms. It is extended by
 * the algorithm itself and uses point and line data to calculate
 * the data required for the individual algorithm
 *
 */
public abstract class Algorithm {

	/**
	 * Abstract method to begin the algorithm
	 * @param coordCollection
	 * @return
	 */
	public abstract List<Point> startAlgorithm(
			List<Point> coordCollection);

	/**
	 * Sorts the hull based on the lines and vertices of the hull
	 * <b>Preconditions:</b> The collection of vertices and lines must be valid and on the hull.
	 * <b>Postconditions:</b> The hull collection is sorted in order and returned.
	 * @param vertices
	 * @param lineCollection
	 * @return
	 */
	public List<Point> sortHull(List<Point> vertices,
			List<Line> lineCollection) {
		Line line = lineCollection.remove(0);
		vertices.add(line.getPointA());
		Point point = line.getPointB();
		while (lineCollection.size() != 0) {
			vertices.add(point);
			int q = -1;
			for (int i = 0; i < lineCollection.size(); i++) {
				line = lineCollection.get(i);
				q = line.hasPoint(point);
				if (q != 0) {
					lineCollection.remove(i);
					break;
				}
			}
			if (q == 1) {
				point = line.getPointB();
			} else if (q == 2) {
				point = line.getPointA();
			}
		}
		return vertices;
	}

	/**
	 * Gets the distance of a point from a line.
	 * <b>Preconditions:</b> Line and point must not be null.
	 * <b>Postconditions:</b> Returns the distance of the point from the line as a double.
	 * @param line
	 * @param p
	 * @return
	 */
	public double distance(Line line, Point p) {
		Point pointA = line.getPointA();
		Point pointB = line.getPointB();
		double dX = pointB.x() - pointA.x();
		double dY = pointB.y() - pointA.y();
		double dist = dX * (pointA.y() - p.y()) - dY * (pointA.x() - p.x());
		if (dist < 0) {
			dist = -dist;
		}
		return dist;
	}

	/**
	 * Receives the maximum and minimum x and y points within given collection.
	 * <b>Preconditions:</b> None
	 * <b>Postconditions:</b> Returns an array storing the minimum 
	 * and maximum Y points in position 0 and 1, and the minimum and maximum
	 * X values stored in position 2 and 3.
	 * @param coordCollection
	 * @return
	 */
	public int[] getRangeIndices(List<Point> coordCollection) {

		double minY = Double.MAX_VALUE;
		double minX = Double.MAX_VALUE;
		double maxY = Double.MIN_VALUE;
		double maxX = Double.MIN_VALUE;
		int[] range = new int[4];
		for (int i = 0; i < coordCollection.size(); i++) {

			double getY = coordCollection.get(i).y();
			double getX = coordCollection.get(i).x();
			// Get minimum Y
			if (getY < minY) {
				minY = getY;
				range[0] = i;
			}
			// Get maximum Y
			if (getY > maxY) {
				maxY = getY;
				range[1] = i;
			}
			// Get minimum X
			if (getX < minX) {
				minX = getX;
				range[2] = i;
			}
			// Get maximum X
			if (getX > maxX) {
				maxX = getX;
				range[3] = i;
			}
		}
		return range;
	}

	/**
	 * Gets the index of the point in the collection farthest from the given line.
	 * <b>Preconditions:</b> Line must not be null.
	 * <b>Postconditions:</b> Returns the index of the farthest point.
	 * @param coordCollection
	 * @param line
	 * @return
	 */
	public int farthestPointIndex(List<Point> coordCollection, Line line) {

		double maxDist = Double.MIN_VALUE;
		int farthestIndex = 0;
		for (int i = 0; i < coordCollection.size(); i++) {
			Point p = coordCollection.get(i);
			double dist = distance(line, p);
			if (dist > maxDist) {
				maxDist = dist;
				farthestIndex = i;
			}
		}
		return farthestIndex;
	}
}
