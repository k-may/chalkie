package com.kevmayo.chalkie.events;

import com.kevmayo.chalkie.interfaces.IDisplayObject;

/**
 * Created by Kev on 01/07/2014.
 */
public class RemovedEvent extends ChalkieEvent {
    public IDisplayObject getChild() {
        return child;
    }

    private IDisplayObject child;
    public RemovedEvent(IDisplayObject child) {
        super(EventType.REMOVED, "removed");

        this.child = child;
    }
}
