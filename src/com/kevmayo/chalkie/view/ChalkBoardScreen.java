package com.kevmayo.chalkie.view;

import com.kevmayo.chalkie.base.DisplayObject;
import com.kevmayo.chalkie.base.stroke.IStrokeRenderer;
import com.kevmayo.chalkie.base.stroke.PStrokeRenderer;
import com.kevmayo.chalkie.events.SaveEvent;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Input;

import processing.core.PGraphics;
import processing.core.PImage;

public class ChalkBoardScreen extends DisplayObject {

    private IStrokeRenderer _controller;

    boolean tapped = false;
    public PImage saveScreen;
    int clearCount = 0;

    private boolean _invalidated = false;

    public ChalkBoardScreen(String name) {
        super(name);

        _controller = new PStrokeRenderer(createBuffer());
        _controller.start();
        addChild((DisplayObject) _controller);

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
            //clear chalkboard
            g.background(0xffffffff);
        }

        super.draw(g);


        if (tapped) {
            tapped = false;
            new SaveEvent(null).dispatch();
        }
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
    public boolean handleTouch(Input.TouchEvent evt) {
        if (evt.type == Input.TouchEvent.DOUBLE_TAP) {
            tapped = true;
            return true;
        }

        return false;
    }
}
