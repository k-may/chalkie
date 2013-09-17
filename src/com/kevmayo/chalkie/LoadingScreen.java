package com.kevmayo.chalkie;

import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Screen;
import com.kevmayo.chalkie.interfaces.Graphics.ImageFormat;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game, "home");
	}

	@Override
	public void update(float time) {
		Graphics g = game.getGraphics();
		Assets.startImage = g.newImage("start.jpg", ImageFormat.RGB565);

		game.setScreen(new HomeScreen(game));
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub

	}

}
