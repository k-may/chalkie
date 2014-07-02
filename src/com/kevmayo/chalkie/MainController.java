package com.kevmayo.chalkie;

import android.util.Log;

import com.kevmayo.chalkie.base.DisplayObject;
import com.kevmayo.chalkie.base.ScrollableDO;
import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.events.RegisterEvent;
import com.kevmayo.chalkie.events.RequestImageEvent;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.IDisplayObject;
import com.kevmayo.chalkie.interfaces.Input;
import com.kevmayo.chalkie.view.HomeScreen;
import com.kevmayo.chalkie.view.MenuImages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController {

    Game game;
    List<ChalkieEvent> _queue;
    Map<EventType, ArrayList<IDisplayObject>> _observers;
    MenuImages _currentMenu;
    private static MainController instance = null;

    protected MainController() {
        // Exists only to defeat instantiation.
        _queue = new ArrayList<ChalkieEvent>();
        _observers = new HashMap<EventType, ArrayList<IDisplayObject>>();
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public void registerGame(Game game) {
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

        boolean handled;
        for (Input.TouchEvent evt : touches) {
            handled = false;
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

        switch (evt.getType()) {

            case STROKE_ADDED:
                break;
            case STROKE_REMOVED:
                break;
            case MANAGER_ADDED:
                break;
            case MANAGER_READY:
                break;
            case SAVE_BUTTON_PRESSED:
                break;
            case LOAD_COMPLETE:
                seeLoadComplete();
                break;
            case ADDED:
                break;
            case REGISTER_EVENT:
                registerHandler((RegisterEvent) evt);
                break;
            case REMOVED:
                break;
            case LAUNCH_BUTTON_PRESSED:
                break;
            case IMAGE_SAVED:
                break;
            case REQUEST_IMAGE:
                seeRequestImage((RequestImageEvent) evt);
                break;
            case IMAGE_MENU_CREATED:
                break;
            case ASSETS_LOADED:
                seeLoadComplete();
                break;
            case ANIMATION_COMPLETE:
                break;
        }

        seeRegistrants(evt);
    }

    private void seeRequestImage(RequestImageEvent evt) {
        HomeScreen screen = (HomeScreen) game.getHomeScreen();
        DisplayObject cont = screen.getContainer();
        if (cont != null && cont instanceof ScrollableDO) {

            int index = ((ScrollableDO) cont).getItemIndex(evt.imageView);
            String path = game.getImagesModel().getPath(index);

            game.getImagesModel().loadImage(evt.imageView, path);

        }
    }

    private void log(String s) {
        Log.i(this.getClass().getSimpleName(), s);
    }

    private void registerHandler(RegisterEvent evt) {
        EventType registerType = evt.getRegisterType();

        if (!_observers.containsKey(registerType))
            _observers.put(registerType, new ArrayList<IDisplayObject>());

        _observers.get(registerType).add(evt.getListener());
    }


    private void seeRegistrants(ChalkieEvent evt) {
        if (_observers.containsKey(evt.getType())) {
            for (IDisplayObject obj : _observers.get(evt.getType()))
                obj.notifyEvent(evt);
        }
    }

    private void seeLoadComplete() {
        game.setScreen(game.getHomeScreen());
    }

}
