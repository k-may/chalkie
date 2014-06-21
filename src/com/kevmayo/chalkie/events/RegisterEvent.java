package com.kevmayo.chalkie.events;

import com.kevmayo.chalkie.interfaces.IDisplayObject;

/**
 * Created by Kev on 20/06/2014.
 */
public class RegisterEvent extends ChalkieEvent {

    public RegisterEvent(IDisplayObject listener, EventType type) {
        super(EventType.REGISTER_EVENT, "registerEvent");
    }
}
