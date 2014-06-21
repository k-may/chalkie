package com.kevmayo.chalkie;

import android.graphics.Typeface;

import com.kevmayo.chalkie.android.framework.AndroidGame;
import com.kevmayo.chalkie.base.TextureInfo;
import com.kevmayo.chalkie.base.math.Point;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Graphics.ImageFormat;
import com.kevmayo.chalkie.interfaces.Image;

import java.util.HashMap;
import java.util.Map;

public class Assets {
	
	public static Assets instance = null;
	public static Typeface Font_Franklin;
	private static Map<String, TextureInfo> _textures;
	public static Image canvas;
	public static String Icon  = "icon";
	public static String SaveButton = "saveButton";
    public static String BlurButton = "blurButton";
	
	private Assets(){
		_textures = new HashMap<String, TextureInfo>();
	}
	
	public static void load(AndroidGame game){
		
		Graphics g = game.getGraphics();
		
		if(instance == null)
			instance = new Assets();

		Font_Franklin = Typeface.createFromAsset(game.getAssets(), "Interstate.ttf");
		canvas = g.newImage("canvas.png", ImageFormat.RGB565);
		
		instance.setTexture(Icon, new TextureInfo(canvas, new Point(153, 19), new Point(266, 100)));
		instance.setTexture(SaveButton, new TextureInfo(canvas, new Point(9, 6), new Point(137, 143)));
		instance.setTexture(BlurButton, new TextureInfo(canvas, new Point(9, 152), new Point(155, 162)));

	}
	
	
	
	public void setTexture(String name, TextureInfo info){
		_textures.put(name, info);
        info.name = name;
	}
	
	public TextureInfo getTexture(String name){
		if(_textures.containsKey(name))
			return _textures.get(name);
		
		return null;
	}

}
