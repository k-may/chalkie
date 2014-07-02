package com.kevmayo.chalkie.view;

import android.graphics.Rect;

import com.kevmayo.chalkie.base.ScrollableDO;
import com.kevmayo.chalkie.interfaces.Graphics;

/**
 * Created by Kev on 28/06/2014.
 */
public class MenuThumbs extends ScrollableDO{
    public MenuThumbs() {
        super("menuThumbs");


        _rect = new Rect(0,0,MainView.SCREEN_WIDTH, MainView.SCREEN_HEIGHT);
    }

    @Override
    public void reset() {

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.fill(205,100,203);
        g.rect(getRect().left, getRect().top, getRect().width(), getRect().height());
    }

    @Override
    public Rect getRect() {
        return super.getRect();
    }
}
