package com.kevmayo.chalkie.android;

import java.util.ArrayList;
import java.util.List;

import com.kevmayo.chalkie.interfaces.StrokePoint;

/**
 * @author kev
 * 
 *         Class for encapsulating data related to a single stroke
 * 
 *         A single stroke is defined but an index for the finger, and a list of
 *         data
 */
public class Stroke {

	private int _dY;
	private int _dX;
	private StrokePoint _downPos;
	public int pointerIndex;
	private List<StrokePoint> _points;
	public boolean closed = false;

	// private boolean isPrimary = false;
	public Stroke() {
	}

	public List<StrokePoint> getPoints() {
		return _points;
	}

	public Stroke(int x, int y, float pressure, int pointerIndex) {
		_downPos = new StrokePoint(x, y, pressure);
		_points = new ArrayList<StrokePoint>();
		_points.add(_downPos);
		this.pointerIndex = pointerIndex;
	}

	public void close() {
		closed = true;
	}

	public void update(int x, int y, float pressure) {

		if (_downPos == null) {
			_downPos = new StrokePoint(x, y, pressure);
			_points = new ArrayList<StrokePoint>();
			_points.add(_downPos);
			return;
		}

		if (y < 0)
			y = 0;
		if (x < 0)
			x = 0;

		_dX = x - _downPos.x;
		_dY = y - _downPos.y;

		if (Math.abs(_dY) > Math.abs(_dX)) {
			renderVerticalBias(x, y, pressure);
		} else {
			renderHorizontalBias(x, y, pressure);
		}
		_downPos = new StrokePoint(x, y, pressure);
	}

	private void renderVerticalBias(int x, int y, float pressure) {
		if (_dY != 0) {

			int Y;
			int X;
			float m = ((float) _dX / (float) _dY);
			int dir = _dY / Math.abs(_dY);
			int ceil = (int) Math.floor(Math.abs(_dY));
			for (int i = 0; i < ceil; i++) {
				Y = i * dir + _downPos.y;
				X = (int) (i * dir * m) + _downPos.x;
				_points.add(new StrokePoint(X, Y, pressure));
			}
		}
	}

	private void renderHorizontalBias(int x, int y, float pressure) {
		if (_dX != 0) {

			int Y;
			int X;
			float m = ((float) _dY / (float) _dX);
			int dir = _dX / Math.abs(_dX);

			for (int i = 0; i < Math.abs(_dX); i++) {
				X = i * dir + _downPos.x;
				Y = (int) (i * dir * m) + _downPos.y;
				_points.add(new StrokePoint(X, Y, pressure));

			}
		}
	}
}
