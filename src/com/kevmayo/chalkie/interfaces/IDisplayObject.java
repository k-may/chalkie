package com.kevmayo.chalkie.interfaces;

import android.graphics.Rect;

public interface IDisplayObject {
	public void update(float time);
	public void draw(Graphics g);
	public IDisplayObject getParent();
	public void setParent(IDisplayObject parent);
	public IDisplayObject removeChild(IDisplayObject child);
	public Rect getRect();
	public void setRect(Rect rect);
	public String getName();
}
