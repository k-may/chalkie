package com.kevmayo.chalkie;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;

import com.kevmayo.chalkie.base.TextureInfo;
import com.kevmayo.chalkie.base.math.Point;
import com.kevmayo.chalkie.events.AssetsLoadedEvent;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Graphics.ImageFormat;
import com.kevmayo.chalkie.interfaces.Image;
import com.kevmayo.chalkie.view.MainView;

import java.util.HashMap;
import java.util.Map;

public class Assets extends AsyncTask<String, Integer, Image> {

    public static Assets instance = null;
    public static Typeface Font_Franklin;

    public static Map<String, TextureInfo> get_textures() {
        return _textures;
    }

    private static Map<String, TextureInfo> _textures;
    public static Image canvas;
    public static String Icon = "icon";
    public static String SaveButton = "saveButton";
    public static String BlurButton = "blurButton";

    private Assets() {
        _textures = new HashMap<String, TextureInfo>();
    }

    public static Assets getInstance() {

        if (instance == null)
            instance = new Assets();

        return instance;
    }

    @Override
    protected Image doInBackground(String... params) {

        Font_Franklin = Typeface.createFromAsset(((Activity) MainView.instance).getAssets(), "Interstate.ttf");

        Graphics g = MainView.instance.getGraphics();
        return g.newImage("canvas.png", ImageFormat.RGB565);
    }

    @Override
    protected void onPostExecute(Image image) {

        if (instance == null)
            instance = new Assets();

        Graphics g = MainView.instance.getGraphics();


        instance.setTexture(Icon, new TextureInfo(canvas, new Point(153, 19), new Point(266, 100)));
        instance.setTexture(SaveButton, new TextureInfo(canvas, new Point(9, 6), new Point(137, 143)));
        instance.setTexture(BlurButton, new TextureInfo(canvas, new Point(9, 152), new Point(155, 162)));

        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("timer", "update: " + millisUntilFinished);
            }

            @Override
            public void onFinish() {
                Log.i("timer", "finished!");
                new AssetsLoadedEvent().dispatch();
            }
        }.start();
    }

    public void setTexture(String name, TextureInfo info) {
        _textures.put(name, info);
        info.name = name;
    }

    public TextureInfo getTexture(String name) {
        if (_textures.containsKey(name))
            return _textures.get(name);

        return null;
    }


}
