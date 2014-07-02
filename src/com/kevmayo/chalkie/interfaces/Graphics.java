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

    void stroke(int color);
    void stroke(int r, int g, int b);

    void fill(int color);
    void fill(int r, int g, int b);

    Image newImage(String fileName, ImageFormat format);

    void background(int r, int g, int b);
	void background(int color);

	void drawPath(Path path, int color);
	void line(float x, float y, float x2, float y2);
	void rect(float x, float y, float width, float height);
	//public void drawImage(Image image, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);
	void drawImage(String name, float x, float y);
	void text(String text, float x, float y);
	int getWidth();
	int getHeight();
	void drawARGB(int i, int j, int k, int l);
    void moveTo(float x, float y);
    void lineTo(float x, float y);
    void close();
}

