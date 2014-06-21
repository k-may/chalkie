package com.kevmayo.chalkie.events;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.kevmayo.chalkie.android.framework.AndroidGame;
import com.kevmayo.chalkie.base.Pool;
import com.kevmayo.chalkie.interfaces.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kev on 21/06/2014.
 */
public abstract class BaseTouchHandler implements TouchHandler {

    protected float scaleX;
    protected float scaleY;

    protected Pool<Input.TouchEvent> touchEventPool;

    protected List<Input.TouchEvent> gestureEventsBuffer = new ArrayList<Input.TouchEvent>();
    protected List<Input.TouchEvent> gestureEvents = new ArrayList<Input.TouchEvent>();
    protected List<Input.TouchEvent> touchEvents = new ArrayList<Input.TouchEvent>();
    protected List<Input.TouchEvent> touchEventsBuffer = new ArrayList<Input.TouchEvent>();
    GestureDetector gestureDetector;

    public BaseTouchHandler(Context context, View view, float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;

        view.setOnTouchListener(this);

        Pool.PoolObjectFactory<Input.TouchEvent> factory = new Pool.PoolObjectFactory<Input.TouchEvent>() {
            @Override
            public Input.TouchEvent createObject() {
                return new Input.TouchEvent();
            }
        };
        touchEventPool = new Pool<Input.TouchEvent>(factory, 10);
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    protected final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            synchronized (this) {
                createTouchEvent(Input.TouchEvent.TAPPED, (int) (e.getX() * scaleX), (int) (e.getY() * scaleY));
                return true;
            }
        }


        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            synchronized (this) {
                boolean result = false;
                try {
                    float downX = e1.getX()*scaleX;
                    float downY = e1.getY()*scaleY;
                    float endX = e2.getX() *scaleX;
                    float endY = e2.getY()*scaleY;

                    float diffY = endY - downY;
                    float diffX = endX - downX;

                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight(downX, downY, endX, endY);
                            } else {
                                onSwipeLeft(downX, downY, endX, endY);
                            }
                        }
                    } else {
                        if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffY > 0) {
                                onSwipeBottom(downX, downY, endX, endY);
                            } else {
                                onSwipeTop(downX, downY, endX, endY);
                            }
                        }
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }
    }

    private void createTouchEvent(int type, int x, int y) {
        Input.TouchEvent touchEvent = touchEventPool.newObject();
        touchEvent.type = type;
        touchEvent.x = x;
        touchEvent.y = y;
        touchEvent.time = AndroidGame.TIME_ELAPSED;
        touchEventsBuffer.add(touchEvent);
    }

    public void onSwipeRight(float dX, float dY, float eX, float eY) {
        createGestureEvent(Input.TouchEvent.SWIPE_RIGHT, dX, dY, eX, eY);
    }

    public void onSwipeLeft(float dX, float dY, float eX, float eY) {
        createGestureEvent(Input.TouchEvent.SWIPE_LEFT, dX, dY, eX, eY);
    }

    public void onSwipeTop(float dX, float dY, float eX, float eY) {
        createGestureEvent(Input.TouchEvent.SWIPE_UP, dX, dY, eX, eY);
    }

    public void onSwipeBottom(float dX, float dY, float eX, float eY) {
        createGestureEvent(Input.TouchEvent.SWIPE_DOWN, dX, dY, eX, eY);
    }

    protected  void createGestureEvent(int type){
        createGestureEvent(type, -1, -1, -1, -1);
    }

    protected void createGestureEvent(int type, float downX, float downY, float endX, float endY) {
        Input.TouchEvent touchEvent = touchEventPool.newObject();
        touchEvent.type = type;
        touchEvent.downX = (int) downX;
        touchEvent.downY = (int) downY;
        touchEvent.x = (int) endX;
        touchEvent.y = (int) endY;
        touchEvent.time = AndroidGame.TIME_ELAPSED;
        gestureEventsBuffer.add(touchEvent);
    }

    @Override
    public List<Input.TouchEvent> getGestureEvents() {
        synchronized (this){
            int len = gestureEvents.size();
            for (int i = 0; i < len; i++)
                touchEventPool.free(gestureEvents.get(i));
            gestureEvents.clear();
            gestureEvents.addAll(gestureEventsBuffer);
            gestureEventsBuffer.clear();
            return gestureEvents;
        }
    }

    @Override
    public List<Input.TouchEvent> getTouchEvents() {
        synchronized (this) {
            int len = touchEvents.size();
            for (int i = 0; i < len; i++)
                touchEventPool.free(touchEvents.get(i));
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();
            return touchEvents;
        }
    }
}
