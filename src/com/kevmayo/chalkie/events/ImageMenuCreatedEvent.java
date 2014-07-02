package com.kevmayo.chalkie.events;

import com.kevmayo.chalkie.view.MenuImages;

/**
 * Created by Kev on 01/07/2014.
 */
public class ImageMenuCreatedEvent extends ChalkieEvent{
    private final MenuImages menuView;

    public ImageMenuCreatedEvent(MenuImages menuImages) {
        super(EventType.IMAGE_MENU_CREATED, "imageMenuCreated");
        this.menuView = menuImages;
    }

    public MenuImages getMenuView() {
        return menuView;
    }
}
