package com.kevmayo.chalkie.android.framework;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import com.kevmayo.chalkie.events.MultiTouchHandler;
import com.kevmayo.chalkie.events.SingleTouchHandler;
import com.kevmayo.chalkie.events.TouchHandler;
import com.kevmayo.chalkie.interfaces.Input;

import java.util.List;

public class AndroidInput implements Input {
	TouchHandler touchHandler;

	public AndroidInput(Context context, View view, float scaleX, float scaleY) {

		if (VERSION.SDK_INT < 5)
			touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
		else
			touchHandler = new MultiTouchHandler(view, scaleX, scaleY);

	}

	@Override
	public boolean isTouchDown(int pointer) {
		return touchHandler.isTouchDown(pointer);
	}

	@Override
	public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
	}

	@Override
	public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
	}

	@Override
	public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
	}

}
