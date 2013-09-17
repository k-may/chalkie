package com.kevmayo.chalkie.old;

import java.util.ArrayList;

import android.graphics.Point;
import android.util.Log;

public class Model {

	
	public static int w;
	public static int h;
	public ArrayList<IManager> _managers = new ArrayList<IManager>();
	public static String TAG = "model";
	public int time = -1;
	
	public void addManager(IManager manager){
		Log.d("", "---> add manager!");
		_managers.add(manager);
	}
	
	public ArrayList<IManager> getActiveManagers(){
		return _managers;
	}
	
	public void setSize(Point p){
		w = p.x;
		h = p.y;
		Log.d(TAG, "size : " + w + " X " + h);
		//todo :  dispatch event
	}
	
	private void update(){
		for(IManager manager : _managers){
			manager.update(time);
		}
	}
	
}
