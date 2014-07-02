package com.kevmayo.chalkie.events;

import com.kevmayo.chalkie.interfaces.IDisplayObject;

/**
 * Created by Kev on 01/07/2014.
 */
public class AddedEvent extends ChalkieEvent {
    public IDisplayObject child;
    public AddedEvent(IDisplayObject child) {
        super(EventType.ADDED, "childAdded : " + child.getName());
        this.child = child;
    }
}
