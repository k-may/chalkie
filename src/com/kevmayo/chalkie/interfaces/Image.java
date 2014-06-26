package com.kevmayo.chalkie.interfaces;

import android.graphics.Bitmap;

import com.kevmayo.chalkie.base.stroke.StrokePoint;
import com.kevmayo.chalkie.interfaces.Graphics.ImageFormat;

import java.util.List;

public interface Image {
	public int getWidth();

	public int getHeight();

	public ImageFormat getFormat();

	public void dispose();
	
	public void setPixels(List<StrokePoint> points);
	
	public void merge(Image image);

    Bitmap getBitmap();
}
