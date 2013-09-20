package com.kevmayo.chalkie.android;

import android.graphics.Point;

public class Line {

	public Point pt1;
	public Point pt2;

	public float A;
	public float B;
	public float C;
	
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

}
