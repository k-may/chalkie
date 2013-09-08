package com.kevmayo.chalkie;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface IManager {

	public Controller getController();

	public void draw(Canvas canvas);

	public void update(int time);

	public Bitmap getMergedBitmap();

}
