package com.kevmayo.chalkie.old;

import java.util.ArrayList;
import java.util.Iterator;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Config;
import android.util.Log;

import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.events.StrokeAddedEvent;

public class StrokeManager implements IManager {

	ArrayList<Stroke> _strokes;
	Controller _controller;
	int w;
	int h;
	Model _model;
	Bitmap _bitmap;
	Rect _rect;
	Paint _paint;
	Boolean _isInitiated = false;

	public StrokeManager(Controller controller, Model model) {
		_model = model;
		_controller = controller;
		_strokes = new ArrayList<Stroke>();
		_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		_paint.setColor(Color.WHITE);
		_rect = new Rect(0, 0, _model.w, _model.h);
	}

	public void draw(Canvas canvas, Bitmap bitmap) {
		canvas.drawBitmap(bitmap, new Rect(0, 0, _model.w, _model.h), new Rect(
				0, 0, _model.w, _model.h), _paint);
	}

	public void draw(Canvas canvas) {
//		if (_bitmap != null) {
//			_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//			_paint.setColor(Color.WHITE);
//			canvas.drawBitmap(_bitmap, new Rect(0, 0, _model.w, _model.h),
//					new Rect(0, 0, _model.w, _model.h), _paint);
//		}
	}

	public void update(int time) {
		_rect = new Rect(0, 0, _model.w, _model.h);
	}

	public void addStroke(Stroke stroke) {
		trace("---> add stroke!!! : " + stroke.getBitmap().getConfig());
		stroke.setMutable(false);
		_strokes.add(stroke);
		_bitmap = getMergedBitmap();

		new StrokeAddedEvent(this, _bitmap).dispatch();
		
//		new ChalkieEvent(this, EventType.STROKE_ADDED, "strokeAdded")
//				.dispatch();

		if (!_isInitiated) {
			_isInitiated = true;
			new ChalkieEvent(this, EventType.MANAGER_READY, "managerReady")
					.dispatch();
		}
	}

	public Bitmap getMergedBitmap() {
		Iterator<Stroke> _iterator = _strokes.iterator();
		Bitmap _merged = _iterator.next().getBitmap();
		while (_iterator.hasNext()) {
			_merged = _merged.copy(_iterator.next().getBitmap().getConfig(),
					true);
		}
		return _merged;
	}

	public void removeStroke(Stroke _stroke) {
		_strokes.remove(_stroke);
	}

	public void pop() {
		_strokes.remove(_strokes.size() - 1);
	}

	public ArrayList<Stroke> getStrokes() {
		return _strokes;
	}

	public int getStrokeCount() {
		return _strokes.size();
	}

	public Controller getController() {
		return _controller;
	}

	public void trace(String msg) {
		// System.out.println(msg);
		Log.d("trace", msg);
	}

}
