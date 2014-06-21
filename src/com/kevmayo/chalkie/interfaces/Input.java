package com.kevmayo.chalkie.interfaces;

import java.util.List;

public interface Input {

	public static class TouchEvent{
		public static final int TOUCH_DOWN = 0;
		public static final int TOUCH_UP = 1;
		public static final int TOUCH_MOVE = 2;
		public static final int TOUCH_HOLD = 3;
        public static final int SWIPE_LEFT = 4;
        public static final int SWIPE_RIGHT = 5;
        public static final int SWIPE_DOWN = 6;
        public static final int SWIPE_UP = 7;
        public static final int TAPPED = 8;

        public int downX, downY;
        public float pressure;
		public int type;
		public int x, y;
		public int pointer;
        public long time;
    }
	
	public boolean isTouchDown(int pointer);
	public int getTouchX(int pointer);
	public int getTouchY(int pointer);
	public List<TouchEvent> getTouchEvents();
    public List<TouchEvent> getGestureEvents();

	
}
