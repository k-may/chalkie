package com.kevmayo.chalkie.view;

import com.kevmayo.chalkie.Assets;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Image;

import processing.core.PImage;

public class LoadingScreen extends Screen {

    boolean loading = false;
    PImage image;


    public LoadingScreen(Game game) {
        super(game, "home");


    }

    @Override
    public void draw(Graphics g) {
        g.background(0);

        if (image == null) {
            Image loading = g.newImage("loading.png", Graphics.ImageFormat.RGB565);
            image = new PImage(loading.getBitmap());//, 0,0,(int)loading.getWidth(), (int)loading.getHeight());
        }

        g.image(image, (getRect().width() -  image.width)/2, (getRect().height() - image.height)/2);

    }

    @Override
    public void update(float time) {
        if (!loading) {
            loading = true;
            Assets.getInstance().execute("canvas.png");
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void backButton() {

    }

}
