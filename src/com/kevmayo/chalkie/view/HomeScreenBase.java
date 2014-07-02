package com.kevmayo.chalkie.view;

import android.util.Log;

import com.kevmayo.chalkie.base.DisplayObject;
import com.kevmayo.chalkie.events.AnimationCompleteEvent;
import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.events.RegisterEvent;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Input;

/**
 * Created by Kev on 28/06/2014.
 */
public abstract class HomeScreenBase extends Screen {

    protected DisplayObject _mainCont;
    protected ChalkBoardScreen _chalkBoard;
    protected HomeBg _bg;

    private enum HomeMode {
        HOME,
        CHALKBOARD,
        MENU_IMAGES,
        MENU_THUMBS
    }

    private HomeMode _mode = HomeMode.HOME;
    private HomeMode _previousMode;

    protected DisplayObject _menuImage;
    protected DisplayObject _menuThumbs;

    public HomeScreenBase(Game game, String name) {
        super(game, name);

        new RegisterEvent(this, EventType.ANIMATION_COMPLETE).dispatch();
    }


    @Override
    public void pause() {
        //TODO save image
    }


    public DisplayObject getContainer() {

        if (_mode == HomeMode.MENU_IMAGES)
            return _menuImage;
        else if (_mode == HomeMode.MENU_THUMBS)
            return _menuThumbs;

        return null;
    }


    @Override
    public void resume() {
        switch (_mode) {
            case HOME:
                break;
            case CHALKBOARD:
                break;
            case MENU_IMAGES:
                break;
            case MENU_THUMBS:
                break;
        }
        _bg.reset();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        if (_mode != _previousMode && _mode != HomeMode.HOME)
            setMode(_previousMode);
    }

    @Override
    public boolean handleTouch(Input.TouchEvent evt) {
        super.handleTouch(evt);

        if (evt.type == Input.TouchEvent.TAPPED)
            return handleTap(evt);
        else if (evt.type == Input.TouchEvent.DOUBLE_TAP)
            return handleDoubleTap(evt);

        Log.i("homescreen", "handle touch!");
        return false;
    }

    private boolean handleDoubleTap(Input.TouchEvent evt) {
        if (_bg.isAnimating() || _menuImage.isAnimating || _menuThumbs.isAnimating)
            return true;

        switch (_mode) {
            case HOME:
                break;
            case CHALKBOARD:
                setMode(HomeMode.HOME);
                break;
            case MENU_IMAGES:
                break;
            case MENU_THUMBS:
                break;
        }
        return true;
    }

    @Override
    public boolean handleGesture(Input.TouchEvent evt) {
        switch (evt.type) {
            case Input.TouchEvent.SWIPE_UP:
            case Input.TouchEvent.SWIPE_DOWN:
                break;
            case Input.TouchEvent.SWIPE_LEFT:
                switch (_mode) {
                    case HOME:
                        setMode(HomeMode.MENU_IMAGES);
                        break;
                    case CHALKBOARD:
                        break;
                    case MENU_IMAGES:
                        setMode(HomeMode.MENU_THUMBS);
                        break;
                    case MENU_THUMBS:
                        //TODO check whether chalkboard still open?
                        setMode(HomeMode.HOME);
                        break;
                }
                break;
            case Input.TouchEvent.SWIPE_RIGHT:
                switch (_mode) {
                    case HOME:
                        setMode(HomeMode.MENU_THUMBS);
                        break;
                    case CHALKBOARD:
                        break;
                    case MENU_IMAGES:
                        setMode(HomeMode.HOME);
                        break;
                    case MENU_THUMBS:
                        setMode(HomeMode.MENU_IMAGES);
                        break;
                }
                break;
        }

        return false;
    }

    @Override
    public void notifyEvent(ChalkieEvent evt) {
        if (evt.getType() == EventType.ANIMATION_COMPLETE) {
            AnimationCompleteEvent e = (AnimationCompleteEvent) evt;
            handleAnimationComplete(e.getDirection(), e.getDisplayObject());
        }
    }

    public boolean handleTap(Input.TouchEvent evt) {

        if (_bg.isAnimating() || _menuImage.isAnimating || _menuThumbs.isAnimating)
            return true;

        switch (_mode) {
            case HOME:
                setMode(HomeMode.CHALKBOARD);
                break;
            case CHALKBOARD:
                setMode(HomeMode.HOME);
                break;
            case MENU_IMAGES:
                break;
            case MENU_THUMBS:
                break;
        }
        return true;
    }

    private void setMode(HomeMode mode) {
        _previousMode = _mode;

        _mode = mode;
        switch (_mode) {
            case CHALKBOARD:
                setChalkboardMode();
                break;
            case HOME:
                setHomeMode();
                break;
            case MENU_IMAGES:
                setMenuImagesMode();
                break;
            case MENU_THUMBS:
                setMenuThumbsMode();
                break;
        }
    }

    private void setMenuThumbsMode() {
        addChild(_menuThumbs);

        switch (_previousMode) {
            case HOME:
            case CHALKBOARD:
                _mainCont.animate(Animate.RIGHT);
                _menuThumbs.setPos(-getRect().width(), 0);
                _menuThumbs.animate(Animate.RIGHT);
                break;
            case MENU_IMAGES:
                _menuImage.animate(Animate.LEFT);
                _menuThumbs.setPos(getRect().width(), 0);
                _menuThumbs.animate(Animate.LEFT);
                break;
            case MENU_THUMBS:
                break;
        }
    }

    private void setMenuImagesMode() {
        addChild(_menuImage);

        switch (_previousMode) {
            case HOME:
            case CHALKBOARD:
                _mainCont.animate(Animate.LEFT);
                _menuImage.setPos(getRect().width(), 0);
                _menuImage.animate(Animate.LEFT);
                break;
            case MENU_IMAGES:
                break;
            case MENU_THUMBS:
                _menuImage.setPos(-getRect().width(), 0);
                _menuImage.animate(Animate.RIGHT);
                _menuThumbs.animate(Animate.RIGHT);
                break;
        }
    }

    private void setHomeMode() {
        addChild(_mainCont);

        switch (_previousMode) {
            case HOME:
            case CHALKBOARD:
                _bg.reset();
                break;
            case MENU_IMAGES:
                _mainCont.setPos(-getRect().width(), 0);
                _mainCont.animate(Animate.RIGHT);
                _menuImage.animate(Animate.RIGHT);
                break;
            case MENU_THUMBS:
                _mainCont.setPos(getRect().width(), 0);
                _mainCont.animate(Animate.LEFT);
                _menuThumbs.animate(Animate.LEFT);
                break;
        }
    }

    private void setChalkboardMode() {
        switch (_previousMode) {
            case HOME:
                _bg.start();
                break;
            case CHALKBOARD:
                break;
            case MENU_IMAGES:
                _mainCont.animate(Animate.LEFT);
                addChild(_menuImage);
                _menuImage.setPos(MainView.SCREEN_WIDTH, getRect().top);
                _menuImage.animate(Animate.LEFT);
                break;
            case MENU_THUMBS:
                _mainCont.animate(Animate.RIGHT);
                addChild(_menuThumbs);
                _menuThumbs.setPos(-MainView.SCREEN_WIDTH, getRect().top);
                _menuThumbs.animate(Animate.RIGHT);
                break;
        }
    }

    @Override
    public void handleAnimationComplete(Animate direction, DisplayObject child) {
        if (_mainCont.isAnimating || _menuImage.isAnimating || _menuThumbs.isAnimating)
            return;

        switch (direction) {
            case LEFT:
                switch (_mode) {
                    case HOME:
                    case CHALKBOARD:
                        removeChild(_menuThumbs);
                        break;
                    case MENU_IMAGES:
                        removeChild(_mainCont);
                        break;
                    case MENU_THUMBS:
                        removeChild(_menuImage);
                        break;
                }
                break;
            case RIGHT:
                switch (_mode) {
                    case HOME:
                    case CHALKBOARD:
                        removeChild(_menuImage);
                        break;
                    case MENU_IMAGES:
                        removeChild(_menuThumbs);
                        break;
                    case MENU_THUMBS:
                        removeChild(_mainCont);
                        break;
                }
                break;
            case TOP:
                break;
            case BOTTOM:
                break;
        }
    }
}
