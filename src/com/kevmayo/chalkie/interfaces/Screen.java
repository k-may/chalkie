package com.kevmayo.chalkie.interfaces;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Rect;
import android.util.Log;

import com.kevmayo.chalkie.framework.AndroidGame;
import com.kevmayo.chalkie.framework.DisplayObject;


/**
 * @author Kevin
 * 
 * Screen serves as base container for each interface
 * 
 * Screen was originally developed (based on the Android Game book) to only be a pattern for 
 * navigation purposes, but I've intended it to be also integrated into the display list framework,
 * so it automates the drawing of children, updating etc.
 * 
 */

public abstract class Screen implements IDisplayObject {

	protected final Game game;
	public String _name = "";
	protected IDisplayObject _parent;
	protected List<IDisplayObject> _children;


	public Screen(Game game, String name) {
		this.game = game;
		this._name = name;
		_children = new ArrayList<IDisplayObject>();
	}

	@Override
	public Rect getAbsoluteRect() {
		return null;
	}
	
	public void update(float time) {
		for (int i = 0; i < _children.size(); i++)
			_children.get(i).update(time);
	}

	public void draw(Graphics g) {
		for (int i = 0; i < _children.size(); i++)
			_children.get(i).draw(g);
	}
	
	public IDisplayObject getParent() {
		return this;
	}

	public void setParent(IDisplayObject parent) {
		_parent = parent;
	}

	public IDisplayObject addChild(IDisplayObject child) {
		if (child == null)
			throw new IllegalArgumentException("child can't be null");

		if (child.getParent() != null)
			child.getParent().removeChild(child);

		_children.add(child);

		child.setParent(this);

		return child;
	}

	@Override
	public void setRect(Rect rect) {
		throw new IllegalArgumentException("screen dimensions can't be set");
	}

	@Override
	public String getName() {
		return _name;
	}

	public IDisplayObject removeChild(IDisplayObject child) {
		if (child == null)
			throw new IllegalArgumentException("child can't be null");

		_children.remove(child);
		if (child != null)
			child.setParent(null);

		return child;
	}

	public Rect getRect(){
		return new Rect(0,0, AndroidGame.SCREEN_WIDTH, AndroidGame.SCREEN_HEIGHT);
	}
	
	public abstract void pause();

	public abstract void resume();

	public abstract void dispose();

	public abstract void backButton();

	private void trace(String msg){
		Log.i("Screen : " + _name, msg);
	}
}
