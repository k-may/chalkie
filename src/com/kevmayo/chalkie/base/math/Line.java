package com.kevmayo.chalkie.base.math;

public class Line {

	public Point pt1;
	public Point pt2;

	public float A;
	public float B;
	public float C;
	
	private boolean hasBeenReversed = false;
	
	public Line(Point pt1, Point pt2) {
		this.pt1 = pt1;
		this.pt2 = pt2;
		updateCoefficients();
	}
	
	public void translate(Point pos) {
		pt1.x += pos.x;
		pt1.y += pos.y;
		pt2.x += pos.x;
		pt2.y += pos.y;
		updateCoefficients();
	}
	
	public void updateCoefficients() {
		A = pt2.y - pt1.y;
		B = pt1.x - pt2.x;
		C = A * pt1.x + B * pt1.y;
	}
	
	public double findSide(Point point) {

		if (pt1.x == pt2.x) {
			return point.x - pt1.x;
		}
		return (pt2.x - pt1.x) * (point.y - pt1.y)
				- (pt2.y - pt1.y) * (point.x - pt1.x);
	}
	
	public int hasPoint(Point point) {
		if (point.x == pt1.x && point.y == pt1.y) {
			return 1;
		} else if (point.x == pt2.x && point.y == pt2.y) {
			return 2;
		} else {
			return 0;
		}
	}
	
	/**
	 * Reverses the line.
	 */
	public void reversePoints() {
		Point temp = pt1;
		pt1 = pt2;
		pt2 = temp;
		if (!hasBeenReversed) {
			hasBeenReversed = true;
		} else {
			hasBeenReversed = false;
		}
	}

}
