package com.kevmayo.chalkie.android;

import static com.kevmayo.chalkie.android.GeometryHelper.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Camera.Size;

import com.kevmayo.chalkie.interfaces.IEdge;
import com.kevmayo.chalkie.interfaces.StrokePoint;

/**
 * @author Kevin
 * 
 *         Creates a morphable edge shape
 *         
 */
public class Edge implements IEdge<Point> {

	private List<Point> _currentPoints;
	private List<Point> _previousPoints;
	private final int MAXLINES = 8;
	private int _size = 20;
	private int _color = Color.BLACK;
	private List<Point> _intersections;

	private List<Line> _lines;

	@Override
	public List<Point> getShape(StrokePoint pt) {
		if (_currentPoints == null)
			reset();

		return _currentPoints;
	}

	private void reset() {
		_lines = new ArrayList<Line>();

		resetLines();
		resetIntersections();
	}

	private void resetIntersections() {
		_intersections = new ArrayList<Point>();
	}

	private void resetLines() {

		if (_lines.size() > 0)
			_lines.remove(_lines.size() - 1);

		while (_lines.size() < MAXLINES) {
			Line line = new Line(RandomPoint(), RandomPoint());
			_lines.add(line);
		}

	}

}
