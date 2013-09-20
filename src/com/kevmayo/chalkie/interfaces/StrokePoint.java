package com.kevmayo.chalkie.interfaces;

import android.graphics.Point;

public class StrokePoint extends Point {
	public int x;
	public int y;
	public float pressure;
	
	public StrokePoint(int x, int y, float pressure){
		super(x, y);
		this.x = x;
		this.y = y;
		this.pressure = pressure;
	}
}
