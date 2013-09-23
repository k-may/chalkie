package com.kevmayo.chalkie.android;

public class Circle {
	public Point center;
	public int radius;
	
	public Circle(){
	}
	
	public Circle(int x, int y, int radius){
		this(new Point(x, y), radius);
	}
	
	public Circle(Point a, int radius){
		center = a;
		this.radius = radius;
	}

}
