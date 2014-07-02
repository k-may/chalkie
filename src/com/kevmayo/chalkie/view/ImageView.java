package com.kevmayo.chalkie.view;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.kevmayo.chalkie.base.DisplayObject;
import com.kevmayo.chalkie.base.ImageLoaderTask;
import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.events.RegisterEvent;
import com.kevmayo.chalkie.events.RequestImageEvent;
import com.kevmayo.chalkie.interfaces.Graphics;

import processing.core.PImage;

/**
 * Created by Kev on 28/06/2014.
 */
public class ImageView extends DisplayObject {

    public ImageLoaderTask task;
    public String path;
    PImage image;

    public static float RATIO = 0.5f;

    public ImageView() {
        super("imageView");

        //register for added events
        new RegisterEvent(this, EventType.ADDED).dispatch();
        new RegisterEvent(this, EventType.REMOVED).dispatch();
    }


    public void setBitmap(Bitmap bmp){
image = new PImage(bmp);
    }

    public void dispose() {
        MainView.instance.disposeImage(image);
        image = null;
    }

    @Override
    public void draw(Graphics g) {
        Rect absPos = getAbsoluteRect();
        g.image(image, absPos.left, absPos.top);

    }

    @Override
    public void notifyEvent(ChalkieEvent evt) {
        if(evt.getType() == EventType.ADDED)
            reset();
        else if(evt.getType() == EventType.REMOVED)
            dispose();
    }

    public void reset() {
        new RequestImageEvent(this).dispatch();
    }
}
