package com.kevmayo.chalkie.android.framework;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Image;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Kev on 25/06/2014.
 */
public abstract class AndroidGraphicsBase<T> implements Graphics<T> {

    AssetManager assets;


    public AndroidGraphicsBase(AssetManager assets){
        this.assets = assets;
    }
    @Override
    public Image newImage(String fileName, ImageFormat format) {
        Bitmap.Config config = null;
        if (format == ImageFormat.RGB565)
            config = Bitmap.Config.RGB_565;
        else if (format == ImageFormat.ARGB4444)
            config = Bitmap.Config.ARGB_4444;
        else
            config = Bitmap.Config.ARGB_8888;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;
        options.inScaled = false;

        InputStream in = null;
        Bitmap bitmap = null;

        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            if (bitmap == null)
                throw new RuntimeException("couldn't load bitmap from asset : "
                        + fileName);
        } catch (IOException e) {
            throw new RuntimeException("couldn't load bitmap from asset : "
                    + fileName);
        } finally {
            if (in == null)
                ;
            try {
                in.close();
            } catch (IOException e) {
            }
        }

        if (bitmap.getConfig() == Bitmap.Config.RGB_565)
            format = ImageFormat.RGB565;
        else if (bitmap.getConfig() == Bitmap.Config.ARGB_4444)
            format = ImageFormat.ARGB4444;
        else
            format = ImageFormat.ARGB8888;

        bitmap.setDensity(Bitmap.DENSITY_NONE);

        return new AndroidImage(bitmap, format);
    }

    protected void log(String s) {
        Log.i(this.getClass().getName(), s);
    }
}
