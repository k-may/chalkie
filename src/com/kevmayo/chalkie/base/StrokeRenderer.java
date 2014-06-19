package com.kevmayo.chalkie.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

import com.kevmayo.chalkie.android.framework.AndroidGame;
import com.kevmayo.chalkie.android.framework.AndroidImage;
import com.kevmayo.chalkie.base.math.Point;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.IEdge;
import com.kevmayo.chalkie.interfaces.Input.TouchEvent;
import com.kevmayo.chalkie.interfaces.Pool;
import com.kevmayo.chalkie.interfaces.Pool.PoolObjectFactory;
import com.kevmayo.chalkie.interfaces.StrokePoint;

import java.util.List;

public class StrokeRenderer extends DisplayObject {

	public static int MAX_STROKES = 100;
	private Pool<Stroke> strokePool;
	// private Dictionary<int, Stroke> _strokeDict;
	private AndroidImage _strokeBuffer;
	private Bitmap _boardBuffer;
	private Stroke _stroke;
	boolean _invalidated = false;
	IEdge _edge;

	public StrokeRenderer(Bitmap buffer) {
		super("strokeController");
		_boardBuffer = buffer;
		PoolObjectFactory<Stroke> factory = new PoolObjectFactory<Stroke>() {
			@Override
			public Stroke createObject() {
				// TODO Auto-generated method stub
				return new Stroke();
			}
		};
		_rect = new Rect(0, 0, AndroidGame.SCREEN_WIDTH,
				AndroidGame.SCREEN_HEIGHT);
	}

	public void setEdge(IEdge edge) {
		_edge = edge;
	}

	public void start() {
		if (_strokeBuffer != null )//|| _boardBuffer != null)
			dispose();

		_strokeBuffer = ChalkieHelper.CreateCanvas();
		//_boardBuffer = ChalkieHelper.CreateCanvas();
	}

	@Override
	public void update(float time) {
		// TODO Auto-generated method stub
		super.update(time);

		// draw to buffer
		if (_invalidated) {
			_invalidated = false;
			drawStroke(_strokeBuffer, _edge, _stroke.getPoints());

			if (_stroke.closed) {
				//_boardBuffer.merge(_strokeBuffer);
				//mergeBuffers();
				_strokeBuffer.dispose();
				_strokeBuffer = ChalkieHelper.CreateCanvas();
				_stroke = null;
			}
		}
	}

	private void mergeBuffers(){
		// TODO Auto-generated method stub
		Bitmap src = _strokeBuffer.bitmap;
		Bitmap merged = Bitmap.createBitmap(_boardBuffer.getWidth(),
				_boardBuffer.getHeight(), _boardBuffer.getConfig());
		Canvas canvas = new Canvas(merged);
		canvas.drawBitmap(_boardBuffer, new Matrix(), null);
		canvas.drawBitmap(src, 0, 0, null);
		_boardBuffer.recycle();
		_boardBuffer = merged;
	}
	
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		super.draw(g);

		if (_strokeBuffer == null || _boardBuffer == null)
			return;

		//g.drawImage(_boardBuffer, 0, 0);

		if (_stroke != null)
			g.drawImage(_strokeBuffer, 0, 0);
	}

	public void dispose() {
		_strokeBuffer.dispose();
		_strokeBuffer = null;
		//_boardBuffer.dispose();
		//_boardBuffer = null;
	}

	public boolean handleTouch(TouchEvent evt) {
		switch (evt.type) {
		case TouchEvent.TOUCH_DOWN:
			// create new stroke
			_stroke = new Stroke();// strokePool.newObject(); // new
									// Stroke(evt.x, evt.y,
									// evt.pressure,
									// evt.pointer);
		case TouchEvent.TOUCH_MOVE:
			if (_stroke != null) {
				_stroke.update(evt.x, evt.y, evt.pressure);
				// test pointer index?
				_stroke.pointerIndex = evt.pointer;
				_invalidated = true;
			}
			break;
		case TouchEvent.TOUCH_UP:
			// dispose stroke
			// strokePool.free(stroke);
			if (_stroke != null) {
				_stroke.update(evt.x, evt.y, evt.pressure);
				_stroke.close();
				_invalidated = true;
			}
			break;
		}

        return true;
	}

	private void drawStroke(AndroidImage image, IEdge<Point> edge,
			List<StrokePoint> points) {
		Bitmap bitmap = image.bitmap;
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);

		for (int i = 0; i < points.size(); i++) {
			StrokePoint sPt = points.get(i);
			Path path = new Path();
			List<Point> shape = edge.getShape(sPt);
			Point pt = shape.get(0);
			path.moveTo(pt.x, pt.y);
			for (int j = 1; j < shape.size(); j++) {
				pt = shape.get(j);
				path.lineTo(pt.x, pt.y);
			}
			path.close();
			canvas.drawPath(path, paint);
		}
	}

}
