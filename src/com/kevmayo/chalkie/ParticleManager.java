package com.kevmayo.chalkie;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class ParticleManager {

	ArrayList<Particle> _particles;

	Controller _controller;
	Model _model;

	public ParticleManager(Controller _controller, Model model) {
		_particles = new ArrayList<Particle>();
		_model = model;
	}

	public void add(Particle particle) {
		_particles.add(particle);
	}

	public void addParticles(ArrayList<Particle> particles) {
		_particles.addAll(particles);
	}

	public void draw(Canvas canvas) {

	}

	public void update(int time) {

	}

	public Bitmap getMergedBitmap() {
		return null;
	}
}
