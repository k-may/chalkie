package com.kevmayo.chalkie.interfaces;

import android.graphics.Path;

public interface Graphics<T> {

    void drawBuffer(T graphics);

    void clear();

    void image(T bgImage, int i, int i1);

    public static enum ImageFormat{
		ARGB8888, ARGB4444, RGB565
	}

    public void strokeWeight(float width);
    public void stroke(int color);
    public void fill(int color);

	public Image newImage(String fileName, ImageFormat format);
	public void background(int color);
	public void drawPath(Path path, int color);
	public void line(float x, float y, float x2, float y2);
	public void rect(float x, float y, float width, float height);
	//public void drawImage(Image image, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);
	public void drawImage(String name, float x, float y);
	void text(String text, float x, float y);
	public int getWidth();
	public int getHeight();
	public void drawARGB(int i, int j, int k, int l);
    public void moveTo(float x, float y);
    public void lineTo(float x, float y);
    public void close();
}

