package com.kevmayo.chalkie.base;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;

import com.kevmayo.chalkie.android.framework.AndroidImage;
import com.kevmayo.chalkie.interfaces.Graphics.ImageFormat;
import com.kevmayo.chalkie.view.MainView;

public class ChalkieHelper {

	public static AndroidImage CreateCanvas(){
		Config config = Config.ARGB_4444;
		int color = Color.argb(0, 255, 255, 255);
		
		Bitmap bm = Bitmap.createBitmap(MainView.SCREEN_WIDTH,
                MainView.SCREEN_HEIGHT, config);
		bm.eraseColor(color);
		
		AndroidImage image = new AndroidImage(bm, ImageFormat.ARGB4444);
		return image;
	}
}
