package com.kevmayo.chalkie;

import java.util.HashMap;
import java.util.Map;

import android.graphics.Point;

import com.kevmayo.chalkie.framework.TextureInfo;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Graphics.ImageFormat;
import com.kevmayo.chalkie.interfaces.Image;

public class Assets {
	
	public static Assets instance = null;
	
	private static Map<String, TextureInfo> _textures;
	public static Image canvas;
	public static String Icon  = "icon";
	public static String SaveButton = "saveButton";
	
	private Assets(){
		_textures = new HashMap<String, TextureInfo>();
	}
	
	public static void load(Graphics g){
		
		if(instance == null)
			instance = new Assets();
		
		canvas = g.newImage("canvas.png", ImageFormat.RGB565);
		
		instance.setTexture(Icon, new TextureInfo(canvas, new Point(69, 12), new Point(175, 44)));
		instance.setTexture(SaveButton, new TextureInfo(canvas, new Point(5, 5), new Point(47, 49)));
		
	}
	
	public void setTexture(String name, TextureInfo info){
		_textures.put(name, info);
	}
	
	public TextureInfo getTexture(String name){
		if(_textures.containsKey(name))
			return _textures.get(name);
		
		return null;
	}

}
