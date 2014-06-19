package com.kevmayo.chalkie;

import java.util.ArrayList;
import java.util.List;

import com.kevmayo.chalkie.android.framework.AndroidGame;
import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.interfaces.IDisplayObject;
import com.kevmayo.chalkie.interfaces.Input;
import com.kevmayo.chalkie.interfaces.Screen;
import com.kevmayo.chalkie.view.ChalkBoardScreen;

import java.util.ArrayList;
import java.util.List;

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
        runTouches();

		for(int i=  0; i < _queue.size(); i ++){
			processEvent(_queue.get(i));
		}

        _queue.clear();
	}

    private void runTouches() {
        List<Input.TouchEvent> touches = game.getInput().getTouchEvents();

        for(Input.TouchEvent evt : touches){
            ArrayList<IDisplayObject> targets = game.getCurrentScreen().getTargetsAtLocation(evt.x, evt.y);
            for(IDisplayObject target : targets){
                //handle event propagation
                if(target.handleTouch(evt))
                    break;
            }
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
