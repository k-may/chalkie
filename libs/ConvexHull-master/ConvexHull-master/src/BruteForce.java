import java.util.ArrayList;
import java.util.List;
/**
 * Bruteforce algorithm
 * Extends the Algorithm class to start the Bruteforce algorithm.
 *
 */
public class BruteForce extends Algorithm {

	/**
	 * Begins the algorithm
	 * <b>Preconditions:</b> Collection must not be null.
	 * <b>Postconditions:</b> Calculates the collection with with Bruteforce to 
	 * determine which points are on the hull. It then sorts the hull, and 
	 * returns the sorted collection.
	 */
	public List<Point> startAlgorithm(List<Point> coordCollection) {

		boolean left = false;
		boolean right = false;
		List<Point> vertices = new ArrayList<Point>();
		List<Line> lineCollection = new ArrayList<Line>();
		//Loop through entire collection
		for (int i = 0; i < coordCollection.size(); i++) {
			//Every possible combinations without repeats or duplicates
			for (int j = i + 1; j < coordCollection.size(); j++) {

				Point getPoint1 = coordCollection.get(i);
				Point getPoint2 = coordCollection.get(j);

				//If the points are not the same
				if (getPoint1.x() != getPoint2.x()
						&& getPoint1.y() != getPoint2.y()) {
					
					//Create a line with the two points
					Line line = new Line(getPoint1, getPoint2);
					double side = 0;
					//Loop through entire collection
					for (int k = 0; k < coordCollection.size(); k++) {
						//If current point isn't the points forming the line
						if (k != i && k != j) {
							//Find the side of the line the point is on
							side = line.findSide(coordCollection.get(k));
							//If it is on the left, or right, toggle its flag true.
							if (side < 0) {
								left = true;
							} else if (side > 0) {
								right = true;
							}
							//If it is on both sides, that line isn't on the hull.
							//Stop the checks.
							if (left == true && right == true)
								break;
						}
					}
					//If the line isn't on the hull, reset flags, begin new check.
					if (left && right) {
						left = right = false;
					} else {
						//If there are no points on both sides of the line, add to hull collection.
						lineCollection.add(line);
					}
					//Reset flags.
					left = right = false;
				}
			}
		}
		return sortHull(vertices, lineCollection);
	}
}
