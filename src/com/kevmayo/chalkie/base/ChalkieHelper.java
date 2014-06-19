package com.kevmayo.chalkie.base;

import com.kevmayo.chalkie.android.AndroidGame;
import com.kevmayo.chalkie.android.AndroidImage;
import com.kevmayo.chalkie.interfaces.Graphics.ImageFormat;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;

public class ChalkieHelper {

	public static AndroidImage CreateCanvas(){
		Config config = Config.ARGB_4444;
		int color = Color.argb(0, 255, 255, 255);
		
		Bitmap bm = Bitmap.createBitmap(AndroidGame.SCREEN_WIDTH,
				AndroidGame.SCREEN_HEIGHT, config);
		bm.eraseColor(color);
		
		AndroidImage image = new AndroidImage(bm, ImageFormat.ARGB4444);
		return image;
	}
}
