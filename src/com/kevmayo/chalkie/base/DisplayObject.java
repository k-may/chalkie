package com.kevmayo.chalkie.base;

import android.graphics.Rect;

import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.IDisplayObject;
import com.kevmayo.chalkie.interfaces.Input.TouchEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class DisplayObject implements IDisplayObject {

    protected Rect _rect;
    protected String _name;
    protected IDisplayObject _parent;
    protected List<IDisplayObject> _children;
    protected boolean _visible = true;

    public DisplayObject(String name) {
        _name = name;
        _children = new ArrayList<IDisplayObject>();
        _rect = new Rect();
    }

    @Override
    public int get_numChildren() {
        return _children.size();
    }

    @Override
    public IDisplayObject get_childAt(int i) {
        return _children.get(i);
    }

    @Override
    public Rect getAbsoluteRect() {

        Rect rect = new Rect(this._rect.left, this._rect.top, this._rect.right, this._rect.bottom);
        IDisplayObject parent = this;
        while (parent.getParent() != parent && parent.getParent() != null) {
            rect.offset(parent.getParent().getRect().left, parent.getParent().getRect().left);//Offset(new Point(parent.parent.rect.X, parent.parent.rect.Y));
            parent = parent.getParent();
        }

        return rect;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public void setPos(int x, int y) {
        _rect.offset(x, y);
        resize();
    }

    public Rect getRect() {
        return _rect;
    }

    public void setRect(Rect _rect) {
        this._rect = _rect;
        resize();
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
        child.resize();

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

    public void update(float time) {
        for (int i = 0; i < _children.size(); i++)
            _children.get(i).update(time);
    }

    public void draw(Graphics g) {
        if (!_visible)
            return;

        for (int i = 0; i < _children.size(); i++)
            _children.get(i).draw(g);
    }

    public boolean handleTouch(TouchEvent evt) {
        return false;
    }

    public void resize() {
    }

    ;
}
