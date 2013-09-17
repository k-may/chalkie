package com.kevmayo.chalkie.interfaces;

import com.kevmayo.chalkie.interfaces.Graphics.ImageFormat;

public interface Image {
	public int getWidth();

	public int getHeight();

	public ImageFormat getFormat();

	public void dispose();
}
