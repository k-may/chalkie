package com.kevmayo.chalkie.events;

/**
 * Created by Kev on 02/07/2014.
 */
public class AssetsLoadedEvent extends ChalkieEvent {

    public AssetsLoadedEvent(){
        super(EventType.ASSETS_LOADED, "assetsloaded");
    }
}
