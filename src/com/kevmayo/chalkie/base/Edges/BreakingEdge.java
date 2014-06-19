package com.kevmayo.chalkie.base.edges;

import android.graphics.Color;
import android.util.Log;

import com.kevmayo.chalkie.base.math.Circle;
import com.kevmayo.chalkie.base.math.Line;
import com.kevmayo.chalkie.base.math.Point;
import com.kevmayo.chalkie.base.math.QuickHull;
import com.kevmayo.chalkie.interfaces.IEdge;
import com.kevmayo.chalkie.interfaces.StrokePoint;

import java.util.ArrayList;
import java.util.List;

import static com.kevmayo.chalkie.base.math.GeometryHelper.CircleLineIntersections;
import static com.kevmayo.chalkie.base.math.GeometryHelper.LineIntersections;
import static com.kevmayo.chalkie.base.math.GeometryHelper.RandomPoint;

/**
 * @author Kevin
 * 
 *         Creates a breaking edge shape
 * 
 */

public class BreakingEdge implements IEdge<Point> {

	private List<Point> _currentShape;
	private List<Point> _previousShape;
	private final int MAXLINES = 5;
	private int _size = 20;
	private float MAX_PRESSURE = 0.5f;
	private int _color = Color.BLACK;
	private List<Point> _intersections;
	private Circle _circle;
	private List<Line> _lines;

	public BreakingEdge() {
		int halfSize = (int) ((float) _size / 2);
		_circle = new Circle(halfSize, halfSize, halfSize);
		_lines = new ArrayList<Line>();
	}

	@Override
	public List<Point> getShape(StrokePoint pt) {
		if (_currentShape == null)
			reset();

		//trace("pressure : " + pt.pressure);
		// if pressure > max resetShape, create particles (dispatch)\
		
		//if(_currentShape == null || pt.pressure > MAX_PRESSURE)
		//	reset();

		return getTranslatedShape(new Point(pt.x, pt.y), _currentShape);
	}

	private void reset() {
		resetLines();
		resetIntersections();
		resetShape();

		trace("reset! size : " + _currentShape.size());

	}

	private List<Point> getTranslatedShape(Point pt, List<Point> shape) {
		List<Point> trans = new ArrayList<Point>();
		// translate points
		for (int i = 0; i < shape.size(); i++) {
			Point p = shape.get(i);
			trans.add(new Point(p.x + pt.x, pt.y + p.y));
		}
		return trans;
	}

	/**
	 * Using Convex Hull algorithm to 'piece back' the shape from the
	 * intersections of the fracture
	 */
	private void resetShape() {
		if (_currentShape != null)
			_previousShape = _currentShape;

		//_currentShape =  BruteHull.startAlgorithm(_intersections);

		//Debug.startMethodTracing("convexhull");
		_currentShape = QuickHull.startAlgorithm(_intersections);
		//Debug.stopMethodTracing();
		
		if(_currentShape.size() < 3)
			throw new RuntimeException("brute hull invalid : pts = " + _currentShape.size());

	}

	private void resetIntersections() {
		_intersections = new ArrayList<Point>();
		_intersections.addAll(LineIntersections(_lines));

		_intersections.addAll(CircleLineIntersections(_lines, _circle));

		// remove intersections outside circle
		for (int i = 0; i < _intersections.size(); i++) {
			Point p = _intersections.get(i);
			double d = Math.sqrt(Math.pow(p.x - _circle.center.x, 2)
					+ Math.pow(p.y - _circle.center.y, 2));
			if ((int) d > _size / 2) {
				_intersections.remove(i);
				i--;
			}
		}
		
		if(_intersections.size() < 3)
			throw new RuntimeException("intersections invalid : " + _intersections.size());
	}

	/**
	 * Add a random line to the stack (think pick up sticks!)
	 */
	private void resetLines() {

		if (_lines.size() > 0)
			_lines.remove(_lines.size() - 1);

		while (_lines.size() < MAXLINES) {
			Line line = new Line(RandomPoint(_size), RandomPoint(_size));
			_lines.add(line);
		}
	}
	
	private void trace(String msg){
		Log.i("BreakingEdge", msg);
	}

}
