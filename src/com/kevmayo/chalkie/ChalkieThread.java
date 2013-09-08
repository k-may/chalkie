package com.kevmayo.chalkie;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class ChalkieThread extends Thread {
    private SurfaceHolder _surfaceHolder;
    private ChalkBoard _panel;
    private boolean _run = false;
 
    public ChalkieThread(SurfaceHolder surfaceHolder, ChalkBoard panel) {
        _surfaceHolder = surfaceHolder;
        _panel = panel;
    }
 
    public void setRunning(boolean run) {
        _run = run;
    }
 
    @Override
    public void run() {
        Canvas c;
        while (_run) {
            c = null;
            try {
                c = _surfaceHolder.lockCanvas(null);
                synchronized (_surfaceHolder) {
                    _panel.draw(c);
                }
            } finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) {
                    _surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}
