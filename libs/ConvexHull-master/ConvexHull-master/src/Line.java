/**
 * Line class holding two points to generate a line
 * Used to find side of line a given point is on.
 */
public class Line {

	//Two points stored as instance variables
	private Point pointA;
	private Point pointB;
	//Flag determining if line has been reversed.
	private boolean hasBeenReversed;

	/**
	 * Line constructor taking two points
	 * <b>Preconditions:</b> None
	 * <b>Postconditions:</b> Stores given points to instance variables
	 * @param pointA
	 * @param pointB
	 */
	public Line(Point pointA, Point pointB) {
		hasBeenReversed = false;
		this.pointA = pointA;
		this.pointB = pointB;
	}

	/**
	 * Finds the side of the line a given point is on.
	 * <b>Preconditions:</b> None
	 * <b>Postconditions:</b> Calculates integer value to determine side of line.
	 * @param point
	 * @return
	 */
	public double findSide(Point point) {

		if (pointA.x() == pointB.x()) {
			return point.x() - pointA.x();
		}
		return (pointB.x() - pointA.x()) * (point.y() - pointA.y())
				- (pointB.y() - pointA.y()) * (point.x() - pointA.x());
	}

	/**
	 * Returns point A
	 * @return
	 */
	public Point getPointA() {
		return pointA;
	}

	/**
	 * Returns point B
	 * @return
	 */
	public Point getPointB() {
		return pointB;
	}

	/**
	 * Reverses the line.
	 */
	public void reversePoints() {
		Point temp = pointA;
		pointA = pointB;
		pointB = temp;
		if (!reversed()) {
			hasBeenReversed = true;
		} else {
			hasBeenReversed = false;
		}
	}

	/**
	 * Checks if the line has been reversed.
	 * @return
	 */
	public boolean reversed() {
		return hasBeenReversed;
	}

	/**
	 * Checks if line contains the given point.
	 * <b>Preconditions:</b> None
	 * <b>Postconditions:</b> If Point A, returns 1.
	 * If Point B, returns 2.
	 * If the line doesn't contain it, returns 0.
	 * @param point
	 * @return
	 */
	public int hasPoint(Point point) {
		if (point.equals(pointA)) {
			return 1;
		} else if (point.equals(pointB)) {
			return 2;
		} else {
			return 0;
		}
	}

	/**
	 * Formats line output showing what points it goes to.
	 */
	public String toString() {
		return "Line: " + pointA + " to " + pointB;
	}
}
