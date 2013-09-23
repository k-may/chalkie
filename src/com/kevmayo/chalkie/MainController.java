package com.kevmayo.chalkie;

import java.util.ArrayList;
import java.util.List;

import com.kevmayo.chalkie.android.AndroidGame;
import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.interfaces.Screen;
import com.kevmayo.chalkie.view.ChalkBoardScreen;

public class MainController {

	AndroidGame game;
	List<ChalkieEvent> _queue;

	private static MainController instance = null;

	protected MainController() {
		// Exists only to defeat instantiation.
		_queue = new ArrayList<ChalkieEvent>();
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
		_queue.add(evt);
	}
	
	public void update(float time){
		for(int i=  0; i < _queue.size(); i ++){
			processEvent(_queue.get(i));
		}
	}
	
	private void processEvent(ChalkieEvent evt){
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
