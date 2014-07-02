package com.kevmayo.chalkie.events;

import com.kevmayo.chalkie.base.DisplayObject;
import com.kevmayo.chalkie.view.Animate;

/**
 * Created by Kev on 02/07/2014.
 */
public class AnimationCompleteEvent extends ChalkieEvent{
    private final DisplayObject displayObject;

    public Animate getDirection() {
        return direction;
    }

    public DisplayObject getDisplayObject() {
        return displayObject;
    }

    private final Animate direction;

    public AnimationCompleteEvent(DisplayObject displayObject, Animate direction) {
        super(EventType.ANIMATION_COMPLETE, "animationComplete");
        this.displayObject = displayObject;
        this.direction = direction;
    }
}
