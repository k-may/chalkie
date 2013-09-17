package com.kevmayo.chalkie.view;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import com.kevmayo.chalkie.framework.TextureInfo;

public class Textures {

	private static Map<String, TextureInfo> _textures = new HashMap<String, TextureInfo>();
	
	public static void getTexture(String name, TextureInfo info){
		_textures.put(name, info);
	}
	
	public static TextureInfo getTexture(String name){
		if(_textures.containsKey(name))
			return _textures.get(name);
		
		return null;
	}
	
}
