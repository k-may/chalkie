package com.kevmayo.chalkie.base.stroke;

import com.kevmayo.chalkie.base.math.Point;

public class StrokePoint extends Point {
	public float x;
	public float y;
	public float pressure;
	
	public StrokePoint(float x, float y, float pressure){
		super(x, y);
		this.x = x;
		this.y = y;
		this.pressure = pressure;
	}
}
