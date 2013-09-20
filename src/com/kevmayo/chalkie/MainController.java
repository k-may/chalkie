package com.kevmayo.chalkie;

import com.kevmayo.chalkie.android.AndroidGame;
import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.interfaces.Screen;
import com.kevmayo.chalkie.view.ChalkBoardScreen;

public class MainController {

	AndroidGame game;

	private static MainController instance = null;

	protected MainController() {
		// Exists only to defeat instantiation.
	}

	public static MainController getInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}

	public void registerGame(AndroidGame game) {
		this.game = game;
	}

	public void eventRecieved(ChalkieEvent evt) {

		if (evt.getType() == EventType.SAVE_BUTTON_PRESSED)
			seeSavePressed();
		else if (evt.getType() == EventType.LAUNCH_BUTTON_PRESSED)
			seeLauncnPressed();
	}

	private void seeSavePressed() {
		// TODO Auto-generated method stub
		if (game.getCurrentScreen().getName() == Screen.HOME)
			game.setScreen(new ChalkBoardScreen(game));
		else if(game.getCurrentScreen().getName() == Screen.CHALKBOARD){
			//save drawing
		}
	}

	private void seeLauncnPressed() {
		// TODO Auto-generated method stub

	}
}
