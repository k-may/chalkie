package com.kevmayo.chalkie.interfaces;

import java.util.List;

import com.kevmayo.chalkie.base.StrokePoint;
import com.kevmayo.chalkie.interfaces.Graphics.ImageFormat;

public interface Image {
	public int getWidth();

	public int getHeight();

	public ImageFormat getFormat();

	public void dispose();
	
	public void setPixels(List<StrokePoint> points);
	
	public void merge(Image image);
}
