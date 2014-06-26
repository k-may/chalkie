package com.kevmayo.chalkie.events;

import processing.core.PImage;

/**
 * Created by Kev on 26/06/2014.
 */
public class SaveEvent extends  ChalkieEvent {

    public PImage image;

    public SaveEvent(PImage image) {
        super(EventType.SAVE_BUTTON_PRESSED, "saved");

        this.image = image;
    }
}
