package com.kevmayo.chalkie.view;

import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;

import com.kevmayo.chalkie.Assets;

import com.kevmayo.chalkie.base.ButtonDO;
import com.kevmayo.chalkie.base.TextureDO;
import com.kevmayo.chalkie.android.framework.AndroidGame;

import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Input;

public class HomeScreen extends Screen {

    private HomeBg _bg;
    private TextureDO _icon;
    private ButtonDO _saveBtn;

    public static HomeScreen instance;

    public HomeScreen(Game game) {
        super(game, Screen.HOME);

        if(instance != null)
            Log.i("homeScreen", "can't instatiate two home screens!");

        instance = this;

        _bg = new HomeBg();
        _bg.setRect(new Rect(0, 0, AndroidGame.SCREEN_WIDTH, AndroidGame.SCREEN_HEIGHT));
        addChild(_bg);

        _icon = new TextureDO(Assets.Icon);
        _icon.setPos(30, 15);
        addChild(_icon);

        _saveBtn = new SaveButton();
        _saveBtn.setPos(AndroidGame.SCREEN_WIDTH - _saveBtn.getRect().width()
                - 10, 15);
        addChild(_saveBtn);
    }

    // TODO add buttons

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        g.clearScreen(Color.argb(255, 255, 255, 255));
        super.draw(g);
    }

    @Override
    public boolean handleTouch(Input.TouchEvent evt) {
        super.handleTouch(evt);

        if(evt.type == Input.TouchEvent.TAPPED)
            return handleTap(evt);

        Log.i("homescreen", "handle touch!");
        return false;
    }

    @Override
    public boolean handleGesture(Input.TouchEvent evt) {
        switch (evt.type) {
            case Input.TouchEvent.SWIPE_DOWN:
            case Input.TouchEvent.SWIPE_LEFT:
            case Input.TouchEvent.SWIPE_RIGHT:
            case Input.TouchEvent.SWIPE_UP:
            break;
        }

        return false;
    }

    public boolean handleTap(Input.TouchEvent evt) {
        _bg.start((int) evt.time);
        return true;
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        _bg.reset();
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
