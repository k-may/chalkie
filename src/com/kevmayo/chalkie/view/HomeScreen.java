package com.kevmayo.chalkie.view;

import android.graphics.Rect;
import android.util.Log;

import com.kevmayo.chalkie.base.DisplayObject;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Graphics;

public class HomeScreen extends HomeScreenBase {

    public static HomeScreen instance;

    public HomeScreen(Game game) {
        super(game, Screen.HOME);

        if (instance != null)
            Log.i("homeScreen", "can't instatiate two home screens!");

        instance = this;

        _mainCont = new DisplayObject("homeMainCont");
        addChild(_mainCont);
        _mainCont.setRect(getRect());

        _chalkBoard = new ChalkBoardScreen("chalkboard");
        _mainCont.addChild(_chalkBoard);

        _bg = new HomeBg();
        _bg.setRect(new Rect(0, 0, MainView.SCREEN_WIDTH, MainView.SCREEN_HEIGHT));
        _mainCont.addChild(_bg);

        _menuImage = new MenuImages();

        _menuThumbs = new MenuThumbs();
    }

    // TODO add buttons

    @Override
    public void draw(Graphics g) {

        g.background(0xffffff);
        super.draw(g);
    }



}
