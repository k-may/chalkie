package com.kevmayo.chalkie.android;

import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Point;


public class GeometryHelper {

	public static Point LineIntersection(Line a, Line b) {
		float det = a.A * b.B - b.A * a.B;

		if (det == 0) {
			// Lines are parallel
			return null;
		}

		float x = (b.B * a.C - a.B * b.C) / det;
		float y = (a.A * b.C - b.A * a.C) / det;
		return new Point(x, y);
	}

	public static List<Point> LineIntersections(Line[] lines) {
		List arr = new ArrayList<Point>();
		int n = 0;
		do {
			for (int i = n; i < lines.length; i++) {
				Point a = LineIntersection(lines[n], lines[i]);

				if (a != null)
					arr.add(a);
			}
			n++;
		} while (n < lines.length);

		return arr;
	}

	public static List<Point> CircleLineIntersections(Line[] lines, Circle cir) {
		List<Point> arr = new ArrayList<Point>();
		int n = 0;
		do {
			for (int i = n; i < lines.length; i++) {
				List<Point> a = CircleLineIntersection(lines[n].a(),
						lines[n].b(), cir.a(), 250);
				if (a != null) {
					arr.addAll(a);
				}
			}
			n++;
		} while (n < lines.length);
		return arr;
	}

	// Code adapted from Paul Bourke:
	// http://local.wasp.uwa.edu.au/~pbourke/geometry/sphereline/raysphere.c
	public static List<Point> CircleLineIntersection(Point A, Point B,
			Point C, int rad) {
		// boolean circleLineIntersect(float x1, float y1, float x2, float y2,
		// float cx, float cy, float cr ) {
		float dx = B.x - A.x;
		float dy = B.y - A.y;
		float a = dx * dx + dy * dy;
		float b = 2 * (dx * (A.x - C.x) + dy * (A.y - C.y));
		float c = C.x * C.x + C.y * C.y;
		c += A.x * A.x + A.y * A.y;
		c -= 2 * (C.x * A.x + C.y * A.y);
		c -= rad * rad;
		float bb4ac = b * b - 4 * a * c;
		// println(bb4ac);

		if (bb4ac < 0) // Not intersecting
			return null;
		//
		float mu1 = (float) (-b + sqrt(b * b - 4 * a * c)) / (2 * a);
		float ix1 = A.x + mu1 * (dx);
		float iy1 = A.y + mu1 * (dy);
		float mu2 = (float) (-b - sqrt(b * b - 4 * a * c)) / (2 * a);
		float ix2 = A.x + mu2 * (dx);
		float iy2 = A.y + mu2 * (dy);
		//
		// // if ((mu1 > 1 && mu2 < 0) || (mu1 < 0 && mu2 > 1))
		// // return null;
		//
		Point p1 = new Point(ix1, iy1);
		Point p2 = new Point(ix2, iy2);
		ArrayList arr = new ArrayList();
		arr.add(p1);
		arr.add(p2);

		return arr;

	}

	public static Point RandomPoint() {
		return new Point((float) (random() * 500), (float) (random() * 500));
	}

	
	public static Boolean IsLeftTurn(Point a, Point b, Point c) {

		Point ab = new Point(a.x - b.x, a.y - b.y);
		Point cb = new Point(c.x - b.x, c.y - b.y);
		float cross = CrossProduct(ab, cb);
		// int cross = (X[i] - X[p]) x (X[n] - X[p]);
		return (cross < 0);

	}

	public static float CrossProduct(Point v1, Point v2) {
		return (v1.x * v2.y) - (v1.y * v2.x);
	}

}
