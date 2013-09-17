package com.kevmayo.chalkie.interfaces;

public abstract class Screen {
	protected final Game game;

	public Screen(Game game) {
		this.game = game;
	}

	public abstract void update(float time);

	public abstract void paint(float time);

	public abstract void pause();

	public abstract void resume();

	public abstract void dispose();

	public abstract void backButton();

}
