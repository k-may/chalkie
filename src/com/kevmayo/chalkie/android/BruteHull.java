package com.kevmayo.chalkie.android;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * Class adapted from https://github.com/trsinard/ConvexHull
 *
 */
public class BruteHull {
	public static List<Point> startAlgorithm(List<Point> coordCollection) {

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
				if (getPoint1.x != getPoint2.x
						&& getPoint1.y != getPoint2.y) {
					
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
	
	public static List<Point> sortHull(List<Point> vertices,
			List<Line> lineCollection) {
		Line line = lineCollection.remove(0);
		vertices.add(line.pt1);
		Point point = line.pt2;
		int count = 0;
		int maxIterations = lineCollection.size()*lineCollection.size();
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
				point = line.pt2;
			} else if (q == 2) {
				point = line.pt1;
			}
			count ++;
			if(count > maxIterations)
				throw new RuntimeException("wtf");
		}
		return vertices;
	}
}
