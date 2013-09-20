package display;

import processing.core.PVector;

public class Line {

	private PVector _a;
	private PVector _b;

	public float A;
	public float B;
	public float C;

	public Line(PVector a, PVector b) {
		_a = a;
		_b = b;
		updateCoefficients();
	}

	public PVector a() {
		return _a;
	}

	public PVector b() {
		return _b;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return _a.toString() + "/" + _b.toString();
	}

	public void translate(PVector pos) {
		_a.x += pos.x;
		_a.y += pos.y;
		_b.x += pos.x;
		_b.y += pos.y;
		updateCoefficients();
	}

	public void updateCoefficients() {
		A = _b.y - _a.y;
		B = _a.x - _b.x;
		C = A * _a.x + B * _a.y;
	}

}
