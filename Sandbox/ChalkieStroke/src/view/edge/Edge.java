package view.edge;

import static utils.GeometryHelper.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import static processing.core.PApplet.*;
import processing.core.PVector;
import display.Circle;
import display.Line;



public class Edge {

	PApplet _parent;
	Line[] _lines;
	Circle _circle;
	List<PVector> _intersections;
	List<PVector> _previousShape;
	List<PVector> _currentShape;
	List<PVector> _shape;
	private Boolean _isMorphComplete = false;
	private int _counter = 0;
	private int maxCount = 100;
	Color _color;
	int _res;
	public PVector _pos = new PVector(20, 50);
	public int _size = 300;

	public Edge(PApplet parent, PVector position, int size, int resolution) {
		_parent = parent;
		_pos = position;
		_size = size;
		_res = resolution;
		init();
	}
	
	private void init(){
		_lines = new Line[_res];
		for (int i = 0; i < _lines.length; i++) {
			_lines[i] = new Line(RandomPoint(), RandomPoint());
		}
		_circle = new Circle((int)(_pos.x + _size / 2),(int)(_pos.y + _size / 2), _size);
		_color = new Color((float) Math.random(), (float) Math.random(),
				(float) Math.random());

		update();
		
		reset();
	}

	public void update() {

		if (!_isMorphComplete && _previousShape != null)
			updateMorph();
		else
			reset();
	}

	private void updateMorph() {
		int c, p = 0;
		float x, y = 0;
		int maxPoints = max(_currentShape.size(), _previousShape.size());
		_shape = new ArrayList<PVector>();
		float diff = (float) ((_counter * 1.) / (maxCount * 1.));
		for (int i = 0; i < maxPoints; i++) {
			if (_currentShape.size() > _previousShape.size()) {
				c = i;
				p = (int) (i / ((_currentShape.size() * 1.) / (_previousShape
						.size() * 1.)));
			} else {
				c = (int) (i / ((_previousShape.size() * 1.) / (_currentShape
						.size() * 1.)));
				p = i;
			}
			PVector cV = _currentShape.get(c);
			PVector pV = _previousShape.get(p);
			x = pV.x + diff * (cV.x - pV.x);
			y = pV.y + diff * (cV.y - pV.y);
			_shape.add(new PVector(x, y));
		}

		_counter++;
		if (_counter > maxCount)
			_isMorphComplete = true;
	}

	public void reset() {
		// update time here!
		_isMorphComplete = false;
		_counter = 0;

		resetLines();
		resetIntersections();
		resetShape();
	}

	private void resetShape() {
		if (_currentShape != null)
			_previousShape = _currentShape;

		_currentShape = new ArrayList<PVector>();
		int N = _intersections.size();
		int p = 0;
		// First find the leftmost point
		for (int i = 1; i < N; i++) {
			if (_intersections.get(i).x < _intersections.get(p).x)
				p = i;
		}
		int start = p;
		int count = 0;
		do {
			int n = -1;

			for (int i = 0; i < N; i++) {

				// Don't go back to the same point you came from
				if (i == p)
					continue;

				// If there is no N yet, set it to i
				if (n == -1)
					n = i;

				PVector a = _intersections.get(i);
				PVector b = _intersections.get(p);
				PVector c = _intersections.get(n);

				if (IsLeftTurn(a, b, c)) {
					// As described above, set N=X
					n = i;
				} else {
					// stop feedback
					count++;
					if (count > N) {
						start = p;
						// cant find next point
						reset();
					}
				}
			}
			p = n;
			count = 0;
			_currentShape.add(_intersections.get(p));
		} while (start != p);

	}

	private void resetIntersections() {
		_intersections = new ArrayList<PVector>();
		_intersections.addAll(LineIntersections(_lines));
		_intersections.addAll(CircleLineIntersections(_lines, _circle));
		println("num intr : " + _intersections.size());
		// remove intersections outside circle
		for (int i = 0; i < _intersections.size(); i++) {
			PVector p = _intersections.get(i);
			float d = dist(p.x, p.y, _circle.a().x, _circle.a().y);
			if ((int) d > _circle.radius()) {
				_intersections.remove(i);
				i--;
			}
		}
	}

	private void resetLines() {
		// pop/shift
		Line[] tempLines = new Line[_lines.length];
		for (int i = 1; i < _lines.length; i++) {
			tempLines[i - 1] = _lines[i];
		}

		Line newLine = new Line(RandomPoint(), RandomPoint());
		newLine.translate(_pos);
		tempLines[_lines.length - 1] = newLine;

		_lines = tempLines;
	}

	public Circle get_Circle() {
		return _circle;
	}

	public Line[] get_Lines() {
		return _lines;
	}

	public List<PVector> get_Intersections() {
		return _intersections;
	}

	public List<PVector> get_Shape() {
		return _shape;
	}

	private void trace(String message) {
		println(message);
	}

	public Color get_Color() {
		return _color;
	}
}
