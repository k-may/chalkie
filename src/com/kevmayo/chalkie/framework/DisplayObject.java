package com.kevmayo.chalkie.framework;

import java.util.List;

import android.graphics.Rect;

import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.IDisplayObject;
import com.kevmayo.chalkie.interfaces.Input;
import com.kevmayo.chalkie.interfaces.Input.TouchEvent;

public abstract class DisplayObject implements IDisplayObject {
	
	private Rect _rect;
	private String _name;
	private IDisplayObject _parent;
	private List<IDisplayObject> _children;
	private boolean _visible = true;

	public DisplayObject(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}

	public Rect getRect() {
		return _rect;
	}

	public void setRect(Rect _rect) {
		this._rect = _rect;
	}

	public IDisplayObject getParent() {
		return _parent;
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

	public IDisplayObject removeChild(IDisplayObject child) {
		if (child == null)
			throw new IllegalArgumentException("child can't be null");

		_children.remove(child);
		if (child != null)
			child.setParent(null);

		return child;
	}

	public void update(float time){
        for (int i = 0; i < _children.size(); i++)
            _children.get(i).update(time);
	}

	public void draw(Graphics g){
        if (!_visible)
            return;
        
        for (int i = 0; i < _children.size(); i++)
            _children.get(i).draw(g);
	}
	
	public void handleTouch(TouchEvent evt){
		
	}
}
