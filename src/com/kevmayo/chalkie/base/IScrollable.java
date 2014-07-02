package com.kevmayo.chalkie.base;

/**
 * Created by Kev on 29/06/2014.
 */
public interface IScrollable {
    void reset();
    int getScrollPos();
    int getNumItems();
    int getItemIndex(DisplayObject displayObject);
}
