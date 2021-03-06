package com.kevmayo.chalkie.interfaces;

import com.kevmayo.chalkie.base.math.Point;

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
        public static final int DOUBLE_TAP = 9;

        public int downX, downY;
        public float pressure;
		public int type;
		public int x, y;
		public int pointer;
        public long time;

        public Point getPos() {
            return new Point(x, y);
        }
    }
	
	public boolean isTouchDown(int pointer);
	public int getTouchX(int pointer);
	public int getTouchY(int pointer);
	public List<TouchEvent> getTouchEvents();
    public List<TouchEvent> getGestureEvents();

	
}
