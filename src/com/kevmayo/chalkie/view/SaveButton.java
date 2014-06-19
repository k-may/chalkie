package com.kevmayo.chalkie.view;

import com.kevmayo.chalkie.Assets;
import com.kevmayo.chalkie.base.ButtonDO;
import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.interfaces.Input.TouchEvent;

public class SaveButton extends ButtonDO {

	public SaveButton(){
		super(Assets.SaveButton, Assets.SaveButton);
	}
	
	@Override
	public boolean handleTouch(TouchEvent evt) {
		new ChalkieEvent(EventType.SAVE_BUTTON_PRESSED, "save").dispatch();
        return true;
	}
}
