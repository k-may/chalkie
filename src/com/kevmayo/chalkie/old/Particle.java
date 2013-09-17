package com.kevmayo.chalkie.old;

public class Particle {
	int _x;
	int _y;
	float _weight;

	public Particle(){
		_weight = 1;
	}
	
	public Particle(int x, int y){
		_x = x;
		_y = y;
		_weight = 1;
	}
	
	public Particle(int x, int y, float weight){
		_x = x;
		_y = y;
		_weight = weight;
	}
	
}
