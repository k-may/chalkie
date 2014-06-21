package com.kevmayo.chalkie.interfaces;

import android.graphics.Rect;

import com.kevmayo.chalkie.interfaces.Input.TouchEvent;

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
	public boolean handleTouch(TouchEvent evt);
    public void resize();
    int get_numChildren();
    IDisplayObject get_childAt(int i);

}
