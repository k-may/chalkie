package com.kevmayo.chalkie.android.framework;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.SurfaceView;

import com.kevmayo.chalkie.interfaces.Audio;
import com.kevmayo.chalkie.interfaces.FileIO;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Input;
import com.kevmayo.chalkie.view.Screen;

/**
 * Using http://www.kilobolt.com/day-1-introduction-to-android.html, amazing game framework tutorial
 */

public abstract class AndroidGame extends Activity implements Game {
    SurfaceView renderView;
    Graphics graphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    WakeLock wakeLock;
    Screen screen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        graphics = new AndroidGraphics(getAssets());
        renderView = new AndroidFastRenderView(this, graphics);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, renderView, 1f, 1f);
        screen = getInitScreen();

        setContentView(renderView);

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
                "MyGame");
    }

    @Override
    public void onResume() {
        super.onResume();
        wakeLock.acquire();
        screen.resume();
        ((AndroidFastRenderView) renderView).resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        ((AndroidFastRenderView) renderView).pause();
        screen.pause();

        if (isFinishing())
            screen.dispose();
    }



    public Screen getCurrentScreen() {
        return screen;
    }

    protected void trace(String msg) {
        Log.i("Game", msg);
    }
}
