package com.kevmayo.chalkie.view;

import android.graphics.Color;

import com.kevmayo.chalkie.Assets;
import com.kevmayo.chalkie.base.ButtonDO;
import com.kevmayo.chalkie.base.DisplayObject;
import com.kevmayo.chalkie.base.math.ButtonHandler;
import com.kevmayo.chalkie.base.stroke.IStrokeRenderer;
import com.kevmayo.chalkie.base.stroke.PStrokeRenderer;
import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.events.SaveEvent;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Input;

import processing.core.PGraphics;
import processing.core.PImage;

public class ChalkBoardScreen extends Screen implements ButtonHandler {

    private ButtonDO _saveBtn;
    private ButtonDO _blurButton;

    private IStrokeRenderer _controller;

    boolean tapped = false;
    public PImage saveScreen;
    int clearCount = 0;

    private boolean _invalidated = false;

    public ChalkBoardScreen(Game game) {
        super(game, Screen.CHALKBOARD);

        game.getGraphics().background(Color.argb(0, 255, 255, 255));

        _controller = new PStrokeRenderer(createBuffer());
        _controller.start();
        addChild((DisplayObject) _controller);

        _blurButton = new ButtonDO(Assets.BlurButton, Assets.BlurButton);
        _blurButton.setPos(MainView.SCREEN_WIDTH - _blurButton.getRect().width(), MainView.SCREEN_HEIGHT - _blurButton.getRect().height());
        // addChild(_blurButton);

        _saveBtn = new ButtonDO(Assets.SaveButton, Assets.SaveButton);
        _saveBtn.setPos(MainView.SCREEN_WIDTH - _saveBtn.getRect().width()
                - 10, 15);
        //addChild(_saveBtn);

        _invalidated = true;
    }

    private PGraphics createBuffer() {
        PGraphics buffer = MainView.instance.createGraphics(MainView.SCREEN_WIDTH, MainView.SCREEN_HEIGHT);
        buffer.beginDraw();
        buffer.fill(0xffddffff);
        buffer.endDraw();
        return buffer;
    }


    @Override
    public void draw(Graphics g) {
        if (_invalidated) {
            _invalidated = false;
            g.background(0xffffffff);
        }// else
        //g.background(0x00000000);


        super.draw(g);


        if (tapped) {
            tapped = false;
            new SaveEvent(null).dispatch();
        }
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

    @Override
    public void update(float time) {
        // TODO Auto-generated method stub
        super.update(time);

    }

    @Override
    public void resize() {

    }

    @Override
    public void pressed(String name) {
        if (name == _saveBtn.getName())
            new ChalkieEvent(EventType.SAVE_BUTTON_PRESSED, "saveChalkBoard").dispatch();
    }

    @Override
    public boolean handleTouch(Input.TouchEvent evt) {
        if (evt.type == Input.TouchEvent.DOUBLE_TAP) {
            tapped = true;
            return true;
        }

        return false;
    }
}
