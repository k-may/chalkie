package com.kevmayo.chalkie.interfaces;

/**
 * Created by Kev on 21/06/2014.
 */
public interface IScreen extends IDisplayObject {
    boolean handleGesture(Input.TouchEvent evt);
}
