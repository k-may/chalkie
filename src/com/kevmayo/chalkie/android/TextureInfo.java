package com.kevmayo.chalkie.android;

import com.kevmayo.chalkie.interfaces.Image;


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
