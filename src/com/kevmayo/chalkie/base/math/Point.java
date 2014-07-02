package com.kevmayo.chalkie.base.math;

import android.graphics.Rect;

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

    public Point(Rect rect) {
        this.x = rect.left;
        this.y = rect.top;
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

    public Point sub(Point startPos) {
        return new Point(x - startPos.x, y  - startPos.y);
    }

    public Point mult(float ratio) {
        return new Point(x * ratio, y * ratio);
    }

    @Override
    public String toString() {
        return "x : " + x + ", y : " + y;
    }

    public float length() {
        return (float) Math.hypot(x, y);
    }
}
