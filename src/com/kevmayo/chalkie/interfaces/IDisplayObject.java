package com.kevmayo.chalkie.interfaces;

import com.kevmayo.chalkie.interfaces.Input.TouchEvent;

import android.graphics.Rect;

public interface IDisplayObject {
	public void update(float time);
	public void draw(Graphics g);
	public IDisplayObject getParent();
	public void setParent(IDisplayObject parent);
	public IDisplayObject removeChild(IDisplayObject child);
	public Rect getRect();
	public Rect getAbsoluteRect();
	public void setRect(Rect rect);
	public String getName();
	public void handleTouch(TouchEvent evt);
}
