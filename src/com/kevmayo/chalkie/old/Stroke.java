package com.kevmayo.chalkie.old;

import java.util.ArrayList;

import android.R.bool;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;

public class Stroke {

	ArrayList<Particle> _particles;
	private Bitmap _bitmap;
	private boolean _mutable = true;
	private Point _downPos;
	private int _dY;
	private int _dX;
	private static String TAG = "stroke";

	public Stroke(Bitmap bitmap, Point downPos) {
		_bitmap = bitmap;
		_downPos = downPos;
	}

	public synchronized void handleMove(int x, int y, int c) {
		if (!_mutable)
			return;

		if(y < 0) y = 0;
		if(x < 0) x = 0;
		
		_dX = x - _downPos.x;
		_dY = y - _downPos.y;

		if (Math.abs(_dY) > Math.abs(_dX)) {
			renderVerticalBias(x, y, c);
		} else {
			renderHorizontalBias(x, y, c);
		}
		_downPos = new Point(x, y);
	}

	private void renderVerticalBias(int x, int y, int c) {
		if (_dY != 0) {

			int Y;
			int X;
			float m = ((float) _dX / (float) _dY);
			int dir = _dY / Math.abs(_dY);
			int ceil = (int) Math.floor(Math.abs(_dY));
			for (int i = 0; i < ceil; i++) {
				Y = i * dir + _downPos.y;
				X = (int) (i * dir * m) + _downPos.x;
				_bitmap.setPixel(X, Y, Color.RED);
			}
		}
	}

	private void renderHorizontalBias(int x, int y, int c) {
		if (_dX != 0) {

			int Y;
			int X;
			float m = ((float) _dY / (float) _dX);
			int dir = _dX / Math.abs(_dX);

			for (int i = 0; i < Math.abs(_dX); i++) {
				X = i * dir + _downPos.x;
				Y = (int) (i * dir * m) + _downPos.y;
				_bitmap.setPixel(X, Y, Color.BLUE);
			}
		}
	}

	public void setMutable(boolean mutable) {
		if (_mutable)
			_mutable = mutable;
	}

	public Bitmap getBitmap() {
		return _bitmap;
	}

}
