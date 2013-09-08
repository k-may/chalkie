package com.kevmayo.chalkie.events;

import com.kevmayo.chalkie.IManager;

import android.graphics.Bitmap;

public class StrokeAddedEvent extends ChalkieEvent {

	private Bitmap _bitmap;
	
	public StrokeAddedEvent(IManager sender, Bitmap bitmap){
		super(sender, EventType.STROKE_ADDED, "strokeAdded");
		_bitmap = bitmap;	
	}
	
	public Bitmap getBitmap(){
		return _bitmap;
	}
}
