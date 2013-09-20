package com.kevmayo.chalkie.android;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;

import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Graphics.ImageFormat;
import com.kevmayo.chalkie.interfaces.Pool;
import com.kevmayo.chalkie.interfaces.Input.TouchEvent;
import com.kevmayo.chalkie.interfaces.Pool.PoolObjectFactory;

public class StrokeRenderer extends DisplayObject {

	public static int MAX_STROKES = 100;
	private Pool<Stroke> strokePool;
	// private Dictionary<int, Stroke> _strokeDict;
	private AndroidImage _strokeBuffer;
	private AndroidImage _boardBuffer;
	Stroke stroke;

	public StrokeRenderer() {
		super("strokeController");
		PoolObjectFactory<Stroke> factory = new PoolObjectFactory<Stroke>() {
			@Override
			public Stroke createObject() {
				// TODO Auto-generated method stub
				return new Stroke();
			}
		};
	}

	public void start() {
		if (_strokeBuffer != null || _boardBuffer != null)
			dispose();

		Config config = Config.ARGB_4444;
		int color = Color.argb(0, 255, 255, 255);
		
		_strokeBuffer = new AndroidImage(createBitmap(config,color),
				ImageFormat.ARGB4444);
		_boardBuffer = new AndroidImage(createBitmap(config,color),
				ImageFormat.ARGB4444);
	}
	
	private Bitmap createBitmap(Config config, int color){
		//Config config = Config.ARGB_4444;
		Bitmap bm = Bitmap.createBitmap(AndroidGame.SCREEN_WIDTH, AndroidGame.SCREEN_HEIGHT, config);
		bm.eraseColor(color);
		return bm;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		super.draw(g);


		if (_strokeBuffer == null || _boardBuffer == null)
			return;
		
		g.drawImage(_boardBuffer, 0, 0);
		g.drawImage(_strokeBuffer, 0, 0);
	}

	public void dispose() {
		_strokeBuffer.dispose();
		_strokeBuffer = null;
		_boardBuffer.dispose();
		_boardBuffer = null;
	}

	public void handleTouch(TouchEvent evt) {
		switch (evt.type) {
		case TouchEvent.TOUCH_DOWN:
			// create new stroke
			stroke = new Stroke();// strokePool.newObject(); // new
									// Stroke(evt.x, evt.y,
									// evt.pressure,
									// evt.pointer);
			stroke.update(evt.x, evt.y, evt.pressure);
			stroke.pointerIndex = evt.pointer;

			_strokeBuffer.setPixels(stroke.getPoints());
			break;
		case TouchEvent.TOUCH_MOVE:
			// update stroke
			stroke.update(evt.x, evt.y, evt.pressure);
			_strokeBuffer.setPixels(stroke.getPoints());
			break;
		case TouchEvent.TOUCH_UP:
			// dispose stroke
			// strokePool.free(stroke);
			stroke.update(evt.x, evt.y, evt.pressure);
			_boardBuffer.merge(_strokeBuffer);
			_strokeBuffer.dispose();
			_strokeBuffer = new AndroidImage(createBitmap(Config.ARGB_4444, Color.argb(0, 255, 255, 255)),
					ImageFormat.ARGB4444);
			break;
		}
	}

}
