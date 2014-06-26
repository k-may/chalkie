package com.kevmayo.chalkie.base;

import com.kevmayo.chalkie.base.math.Point;
import com.kevmayo.chalkie.interfaces.Image;


public class TextureInfo {
    public String name;
    public Point pos;
    public Image image;
    public Point size;
    public int id;
    public static int ID = 0;
    
    public TextureInfo(Image image, Point pos, Point size){
    	this.image = image;
    	this.pos = pos;
    	this.size = size;
        id = ID++;
    }

}
