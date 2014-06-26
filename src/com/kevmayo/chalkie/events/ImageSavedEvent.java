package com.kevmayo.chalkie.events;

import processing.core.PImage;

/**
 * Created by Kev on 26/06/2014.
 */
public class ImageSavedEvent extends ChalkieEvent{

    public PImage pImage;
    public ImageSavedEvent(PImage pImage) {
        super(EventType.IMAGE_SAVED, "imageSaved");

        this.pImage = pImage;
    }
}
