package com.kevmayo.chalkie;

import com.kevmayo.chalkie.android.framework.AndroidGame;
import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.events.RegisterEvent;
import com.kevmayo.chalkie.interfaces.IDisplayObject;
import com.kevmayo.chalkie.interfaces.Input;
import com.kevmayo.chalkie.view.Screen;
import com.kevmayo.chalkie.view.ChalkBoardScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController {

    AndroidGame game;
    List<ChalkieEvent> _queue;
    Map<String, IDisplayObject> _observers;

    private static MainController instance = null;

    protected MainController() {
        // Exists only to defeat instantiation.
        _queue = new ArrayList<ChalkieEvent>();
        _observers = new HashMap<String, IDisplayObject>();
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

    public void update(float time) {
        runGestures();
        runTouches();

        for (int i = 0; i < _queue.size(); i++) {
            processEvent(_queue.get(i));
        }

        _queue.clear();
    }

    private void runTouches() {
        List<Input.TouchEvent> touches = game.getInput().getTouchEvents();

        boolean handled = false;
        for (Input.TouchEvent evt : touches) {
            ArrayList<IDisplayObject> targets = game.getCurrentScreen().getTargetsAtLocation(evt.x, evt.y);
            for (IDisplayObject target : targets) {
                //handle event propagation
                handled = handled || target.handleTouch(evt);

                if (handled)
                    break;
            }
        }

    }

    private boolean runGestures() {
        List<Input.TouchEvent> gestures = game.getInput().getGestureEvents();

        boolean handled = false;
        for (Input.TouchEvent evt : gestures) {
            handled = handled || game.getCurrentScreen().handleGesture(evt);
        }

        return handled;
    }


    private void processEvent(ChalkieEvent evt) {
        if (evt.getType() == EventType.SAVE_BUTTON_PRESSED)
            seeSavePressed();
        else if (evt.getType() == EventType.LAUNCH_BUTTON_PRESSED)
            seeLaunchPressed();
        else if (evt.getType() == EventType.LOAD_COMPLETE)
            seeLoadComplete();
        else if (evt.getType() == EventType.ADDED)
            seeChildAdded(evt);
        else if (evt.getType() == EventType.REGISTER_EVENT)
            registerHandler((RegisterEvent) evt);
    }

    private void registerHandler(RegisterEvent evt) {

    }

    private void seeChildAdded(ChalkieEvent evt) {

    }

    private void seeLoadComplete() {
        game.setScreen(game.getHomeScreen());
    }

    private void seeSavePressed() {
        // TODO Auto-generated method stub
        if (game.getCurrentScreen().getName() == Screen.HOME)
            game.setScreen(new ChalkBoardScreen(game));
        else if (game.getCurrentScreen().getName() == Screen.CHALKBOARD) {
            //save drawing
            game.setScreen(game.getInitScreen());
        }
    }

    private void seeLaunchPressed() {
        if (game.getCurrentScreen().getName() == Screen.HOME)
            game.setScreen(new ChalkBoardScreen(game));
    }
}
