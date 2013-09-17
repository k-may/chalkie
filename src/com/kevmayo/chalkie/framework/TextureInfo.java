package com.kevmayo.chalkie.framework;

import com.kevmayo.chalkie.interfaces.Image;

import android.graphics.Point;

public class TextureInfo {
    public String name;
    public Point pos;
    public Image image;
    public Point size;
    
    public TextureInfo(Image image, Point pos, Point size){
    	this.image = image;
    	this.pos = pos;
    	this.size = size;
    }

}
