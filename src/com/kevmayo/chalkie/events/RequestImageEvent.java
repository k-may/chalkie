package com.kevmayo.chalkie.events;

import com.kevmayo.chalkie.view.ImageView;

/**
 * Created by Kev on 29/06/2014.
 */
public class RequestImageEvent extends ChalkieEvent {

    public ImageView imageView;
    public RequestImageEvent(ImageView imageView) {
        super(EventType.REQUEST_IMAGE, "requestImage");

        this.imageView = imageView;
    }
}
