package com.kevmayo.chalkie.view;

import android.graphics.Rect;

import com.kevmayo.chalkie.base.ScrollableDO;
import com.kevmayo.chalkie.interfaces.Graphics;

import java.util.ArrayList;

/**
 * Created by Kev on 28/06/2014.
 */
public class MenuImages extends ScrollableDO {

    ArrayList<ImageView> _images;
    int numRows = 1;

    public MenuImages() {
        super("menuImages");

        _images = new ArrayList<ImageView>();

        int numItems = numRows + 2;
        for (int i = 0; i < numItems; i++) {
            ImageView view = new ImageView();
            _images.add(view);
            addChild(view);
        }

        _rect = new Rect(0,0,MainView.SCREEN_WIDTH, MainView.SCREEN_HEIGHT);
    }

    @Override
    public void reset() {

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.fill(255,200,103);
        g.rect(getRect().left, getRect().top, getRect().width(), getRect().height());
    }
}
