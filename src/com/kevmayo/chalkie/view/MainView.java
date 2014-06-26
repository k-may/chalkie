package com.kevmayo.chalkie.view;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.kevmayo.chalkie.MainController;
import com.kevmayo.chalkie.android.framework.AndroidAudio;
import com.kevmayo.chalkie.android.framework.AndroidFileIO;
import com.kevmayo.chalkie.android.framework.AndroidInput;
import com.kevmayo.chalkie.android.processing.PAndroidGame;
import com.kevmayo.chalkie.android.processing.PAndroidGraphics;
import com.kevmayo.chalkie.events.ImageSavedEvent;
import com.kevmayo.chalkie.interfaces.Audio;
import com.kevmayo.chalkie.interfaces.FileIO;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Input;

import processing.core.PGraphics;

/**
 * Created by Kev on 21/06/2014.
 */
public class MainView extends PAndroidGame {

    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    public static String MODE;
    public static final String PROCESSING_MODE = "processing";
    public static final String CANVAS_MODE = "canvas";
    public static final String OPENGL_MODE = "opengl";
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static long TIME_ELAPSED;
    long startMillis = 0;
    long startTime = 0;
    int millis;

    public static MainView instance;

    private HomeScreen homeScreen;
    private boolean _saveImage;

    @Override
    public void onCreate(Bundle bundle) {

        instance = this;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        // todo move this to congifuration change event
        SCREEN_WIDTH = size.x;
        SCREEN_HEIGHT = size.y;
        trace("SCREEN DIMENSIONS : " + size.x + " / " + size.y);

        screen = getInitScreen();

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
                "MyGame");

        startMillis = System.currentTimeMillis();
        startTime = System.nanoTime();

        super.onCreate(bundle);

        input = new AndroidInput(this, getSurfaceView(), 1f, 1f);
        graphics = new PAndroidGraphics(this);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
    }

    @Override
    public void draw() {

        background(255);

        PGraphics g = ((PAndroidGraphics) getGraphics()).g;
        g.beginDraw();

        float deltaTime = (System.nanoTime() - startTime) / 10000000.000f;
        startTime = System.nanoTime();

        if (deltaTime > 3.15) {
            deltaTime = (float) 3.15;
        }

        MainView.TIME_ELAPSED = (System.currentTimeMillis() - startMillis);

        MainController.getInstance().update(deltaTime);
        getCurrentScreen().update(deltaTime);
        getCurrentScreen().draw(getGraphics());

        g.endDraw();

        image(g, 0, 0);

        if (_saveImage) {
            _saveImage = false;
            g.loadPixels();
            new ImageSavedEvent(g.get()).dispatch();
        }
    }

    @Override
    public Screen getInitScreen() {
        // TODO Auto-generated method stub
        return new LoadingScreen(this);
    }

    @Override
    public Screen getHomeScreen() {
        if (homeScreen == null) {
            homeScreen = new HomeScreen(this);
        }

        return homeScreen;
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public void setScreen(Screen screen) {
        trace("set screen  : " + screen.getName());

        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();

        screen.resume();
        screen.update(0);
        this.screen = screen;
    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        getCurrentScreen().backButton();
    }

    private void trace(String s) {
        Log.i("MainView", s);
    }

    public Screen getCurrentScreen() {
        return screen;
    }

    @Override
    public void saveImage() {
        _saveImage = true;
    }
}
