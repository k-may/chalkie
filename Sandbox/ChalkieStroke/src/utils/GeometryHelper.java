package utils;

import static java.lang.Math.*;
import static utils.GeometryHelper.CrossProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import display.Circle;
import display.Line;
import processing.core.PVector;

public class GeometryHelper {

	public static PVector LineIntersection(Line a, Line b) {
		float det = a.A * b.B - b.A * a.B;

		if (det == 0) {
			// Lines are parallel
			return null;
		}

		float x = (b.B * a.C - a.B * b.C) / det;
		float y = (a.A * b.C - b.A * a.C) / det;
		return new PVector(x, y);
	}

	public static List<PVector> LineIntersections(Line[] lines) {
		List arr = new ArrayList<PVector>();
		int n = 0;
		do {
			for (int i = n; i < lines.length; i++) {
				PVector a = LineIntersection(lines[n], lines[i]);

				if (a != null)
					arr.add(a);
			}
			n++;
		} while (n < lines.length);

		return arr;
	}

	public static List<PVector> CircleLineIntersections(Line[] lines, Circle cir) {
		List<PVector> arr = new ArrayList<PVector>();
		int n = 0;
		do {
			for (int i = n; i < lines.length; i++) {
				List<PVector> a = CircleLineIntersection(lines[n].a(),
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
	public static List<PVector> CircleLineIntersection(PVector A, PVector B,
			PVector C, int rad) {
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
		PVector p1 = new PVector(ix1, iy1);
		PVector p2 = new PVector(ix2, iy2);
		ArrayList arr = new ArrayList();
		arr.add(p1);
		arr.add(p2);

		return arr;

	}

	public static PVector RandomPoint() {
		return new PVector((float) (random() * 500), (float) (random() * 500));
	}

	
	public static Boolean IsLeftTurn(PVector a, PVector b, PVector c) {

		PVector ab = new PVector(a.x - b.x, a.y - b.y);
		PVector cb = new PVector(c.x - b.x, c.y - b.y);
		float cross = CrossProduct(ab, cb);
		// int cross = (X[i] - X[p]) x (X[n] - X[p]);
		return (cross < 0);

	}

	public static float CrossProduct(PVector v1, PVector v2) {
		return (v1.x * v2.y) - (v1.y * v2.x);
	}

}
