package com.kevmayo.chalkie;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ChalkBoard extends SurfaceView implements SurfaceHolder.Callback {

	int[] x = new int[2];
	int[] y = new int[2];

	float[] r = { 1, 1 };

	int count = 0;

	Stroke _stroke;
	StrokeManager _strokeManager;

	ChalkieThread thread;

	ParticleManager _particleManager;
	Controller _controller;
	Model _model;

	private Bitmap _bitmap;

	public ChalkBoard(Context ctx) {
		super(ctx);
		getHolder().addCallback(this);
		thread = new ChalkieThread(getHolder(), this);

		_model = new Model();

		_controller = new Controller(this, _model);

		_particleManager = new ParticleManager(_controller, _model);

		_strokeManager = new StrokeManager(_controller, _model);

	}

	@Override
	protected void onMeasure(int wSpec, int hSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(wSpec, hSpec);

		Point size = new Point(MeasureSpec.getSize(wSpec),
				MeasureSpec.getSize(hSpec));
		_model.setSize(size);
		setBitmap(createBitmap());
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);

		canvas.drawColor(Color.BLACK);

		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		p.setColor(Color.WHITE);
		Rect rect = new Rect(0, 0, Model.w, Model.h);

		if (_stroke != null)
			canvas.drawBitmap(_stroke.getBitmap(), rect, rect, p);

		//canvas.drawBitmap(_bitmap, rect, rect, p);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			_stroke = createStroke(event);
		}

		if (event.getAction() == MotionEvent.ACTION_UP) {
			_strokeManager.addStroke(_stroke);
			_stroke = null;
		}

		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (_stroke != null) {
				_stroke.handleMove((int) event.getX(), (int) event.getY(),
						(int) event.getPressure() * 300);
			}
		}

		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// we will try it again and again...
			}
		}
	}

	public void trace(String msg) {
		Log.d("trace", msg);
	}

	public Bitmap createBitmap() {
		return Bitmap.createBitmap(_model.w, _model.h, Bitmap.Config.RGB_565);
	}

	public Bitmap getBitmap() {
		return _bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		_bitmap = bitmap;
	}

	public Stroke createStroke(MotionEvent event) {
		Point pt = new Point((int) event.getX(), (int) event.getY());
		Rect rect = new Rect(0,0, Model.w, Model.h);
		return new Stroke(createBitmap(), pt);
	}
}
