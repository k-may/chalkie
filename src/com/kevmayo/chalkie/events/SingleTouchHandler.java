package com.kevmayo.chalkie.events;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.kevmayo.chalkie.interfaces.Input.TouchEvent;
import com.kevmayo.chalkie.view.MainView;

public class SingleTouchHandler extends BaseTouchHandler {
    boolean isTouched;
    int touchX;
    int touchY;

    public SingleTouchHandler(Context game, View view, float scaleX, float scaleY) {
        super(game, view, scaleX, scaleY);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        synchronized (this) {
            TouchEvent touchEvent = touchEventPool.newObject();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchEvent.type = TouchEvent.TOUCH_DOWN;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent.type = TouchEvent.TOUCH_MOVE;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    touchEvent.type = TouchEvent.TOUCH_UP;
                    isTouched = false;
                    break;
            }

            touchEvent.x = touchX = (int) (event.getX() * scaleX);
            touchEvent.y = touchY = (int) (event.getY() * scaleY);
            touchEvent.time = MainView.TIME_ELAPSED;
            touchEventsBuffer.add(touchEvent);

            return true;
        }
    }

    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this) {
            if (pointer == 0)
                return isTouched;
            else
                return false;
        }
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this) {
            return touchX;
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this) {
            return touchY;
        }
    }

}
