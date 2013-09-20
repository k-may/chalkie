package display;

import processing.core.PVector;

public class Circle {

	private PVector _a;
	private int _radius;
	
	public Circle(){
	}
	
	public Circle(int x, int y, int radius){
		this(new PVector(x, y), radius);
	}
	
	public Circle(PVector a, int radius){
		_a = a;
		_radius = radius;
	}
	
	public PVector a(){
		return _a;
	}
	
	public int radius(){
		return _radius;
	}
}
