package com.kevmayo.chalkie.android;

import android.graphics.Point;


public class Circle {
	private Point _a;
	private int _radius;
	
	public Circle(){
	}
	
	public Circle(int x, int y, int radius){
		this(new Point(x, y), radius);
	}
	
	public Circle(Point a, int radius){
		_a = a;
		_radius = radius;
	}
	
	public Point a(){
		return _a;
	}
	
	public int radius(){
		return _radius;
	}
}
