package com.kevmayo.chalkie.android;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.kevmayo.chalkie.interfaces.Image;
import com.kevmayo.chalkie.interfaces.Graphics.ImageFormat;
import com.kevmayo.chalkie.interfaces.StrokePoint;

public class AndroidImage implements Image {

	public Bitmap bitmap;
	ImageFormat format;

	public AndroidImage(Bitmap bitmap, ImageFormat format) {
		this.bitmap = bitmap;
		this.format = format;
	}

	@Override
	public int getWidth() {
		return bitmap.getWidth();
	}

	@Override
	public int getHeight() {
		return bitmap.getHeight();
	}

	@Override
	public ImageFormat getFormat() {
		return format;
	}

	@Override
	public void dispose() {
		bitmap.recycle();
	}

	@Override
	public void setPixels(List<StrokePoint> points) {
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		
		for (int i = 0; i < points.size(); i++) {
			StrokePoint pt = points.get(i);
			//bitmap.setPixel(pt.x, pt.y, Color.BLACK);
			canvas.drawCircle(pt.x, pt.y, pt.pressure * 10, paint);
		}
	}

	@Override
	public void merge(Image image) {
		// TODO Auto-generated method stub
		Bitmap src = ((AndroidImage) image).bitmap;
		Bitmap merged = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), bitmap.getConfig());
		Canvas canvas = new Canvas(merged);
		canvas.drawBitmap(bitmap, new Matrix(), null);
		canvas.drawBitmap(src, 0, 0, null);
		bitmap.recycle();
		bitmap = merged;
	}

}
