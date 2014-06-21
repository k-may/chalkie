package com.kevmayo.chalkie.view;

import com.kevmayo.chalkie.Assets;
import com.kevmayo.chalkie.android.framework.AndroidGame;
import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.interfaces.Game;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game, "home");
	}

	@Override
	public void update(float time) {
		//Graphics g = game.getGraphics();
		Assets.load((AndroidGame)game);

        new ChalkieEvent(EventType.LOAD_COMPLETE, "loadComplete").dispatch();
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
