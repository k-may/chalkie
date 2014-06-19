package com.kevmayo.chalkie.base.math;

public class Point {
	public float x;
	public float y;

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(){
		this.x = 0;
        this.y = 0;
	}

    public void lerpTo(Point dest, float value){
        x = x + (dest.x - x)*value;
        y = y + (dest.y - y)*value;
    }

    public static Point lerp(Point start, Point dest, float value) {
        float x = start.x + (dest.x - start.x)*value;
        float y = start.y + (dest.y - start.y)*value;
        return new Point(x, y);
    }
}
