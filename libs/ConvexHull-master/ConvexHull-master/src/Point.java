/**
 * Point class stores x and y coordinates. 
 * Contains methods to get the x and y values, 
 * and formats the output data for the points.
 */
public class Point {
	
	//Private variables used to store the x and y values of the point.
	private final double xCord, yCord;

	/**
	 * Constructor for Point object, stores the value of x and y.
	 * <b>Preconditions:</b> None
	 * <b>Postconditions:</b> Stores the sent x and y values in instance variables.
	 * @param xCord
	 * @param yCord
	 */
	public Point(double xCord, double yCord) {
		this.xCord = xCord;
		this.yCord = yCord;
	}

	/**
	 * Receives the x value of a point.
	 * <b>Preconditions:</b> None
	 * <b>Postconditions:</b> Returns x value.
	 * @return
	 */
	public double x() {
		return xCord;
	}

	/**
	 * Receives the y value of a point.
	 * <b>Preconditions:</b> None
	 * <b>Postconditions:</b> Returns the y value.
	 * @return
	 */
	public double y() {
		return yCord;
	}

	/**
	 * Formats the output for the point class.
	 * <b>Preconditions:</b> None
	 * <b>Postconditions:</b> Returns formatted data of point.
	 */
	public String toString() {
		return "(" + xCord + "," + yCord + ")";
	}

}
