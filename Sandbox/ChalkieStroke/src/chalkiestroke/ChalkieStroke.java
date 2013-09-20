package chalkiestroke;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import display.*;
import view.edge.*;
import view.edge.Edge;
import processing.core.PApplet;
import processing.core.PVector;
import static utils.GeometryHelper.*;

public class ChalkieStroke extends PApplet {

	Edge _edge;

	public void setup() {
		size(1000, 1000);
		smooth();
		_edge = new Edge(this, new PVector(width/2, height/2), width/2, 8);
	}

	public void draw() {
		background(255);
		_edge.update();
		drawEdge(_edge);
	}
	
	private void drawEdge(Edge edge){
		noStroke();
		Color jC = edge.get_Color();
		int red = jC.getRed();
		fill(jC.getRed(), jC.getGreen(), jC.getBlue());
		//stroke(jC.getRed(), jC.getGreen(), jC.getBlue());
		List<PVector> shape = _edge.get_Shape();
		if (shape != null)
			drawShape(edge.get_Shape());
	}
	
	private void drawShape(List<PVector> shape) {
		PVector a;
		beginShape();
		for (int i = 0; i < shape.size(); i++) {
			a = shape.get(i);
			vertex(a.x, a.y);
		}
		endShape(CLOSE);
	}

	@Override
	public void mousePressed() {
		// TODO add controller!
		_edge.reset();
	}

}
