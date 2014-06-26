package com.kevmayo.chalkie.view;

import android.graphics.Rect;
import android.util.Log;

import com.kevmayo.chalkie.Assets;
import com.kevmayo.chalkie.base.ButtonDO;
import com.kevmayo.chalkie.base.TextureDO;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Input;

import processing.core.PImage;

public class HomeScreen extends Screen {

    private HomeBg _bg;
    private TextureDO _icon;
    private ButtonDO _saveBtn;
private PImage _bgImage;
    public static HomeScreen instance;

    public HomeScreen(Game game) {
        super(game, Screen.HOME);

        if(instance != null)
            Log.i("homeScreen", "can't instatiate two home screens!");

        instance = this;

        _bg = new HomeBg();
        _bg.setRect(new Rect(0, 0, MainView.SCREEN_WIDTH, MainView.SCREEN_HEIGHT));
        addChild(_bg);

        _icon = new TextureDO(Assets.Icon);
        _icon.setPos(30, 15);
        //addChild(_icon);

        _saveBtn = new SaveButton();
        _saveBtn.setPos(MainView.SCREEN_WIDTH - _saveBtn.getRect().width()
                - 10, 15);
        //addChild(_saveBtn);
    }

    // TODO add buttons

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        g.background(0xffffff);

        if(_bgImage != null)
            g.image(_bgImage, 0, 0);

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

    public void setBackgroundImage(PImage image) {
        _bgImage = image;
    }
}
