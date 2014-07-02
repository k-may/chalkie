package com.kevmayo.chalkie.base;

import android.graphics.Rect;
import android.util.Log;

import com.kevmayo.chalkie.base.math.Point;
import com.kevmayo.chalkie.events.AnimationCompleteEvent;
import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.IDisplayObject;
import com.kevmayo.chalkie.interfaces.Input.TouchEvent;
import com.kevmayo.chalkie.view.Animate;
import com.kevmayo.chalkie.view.MainView;
import com.kevmayo.chalkie.view.Screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayObject implements IDisplayObject {

    protected Rect _rect;
    protected String _name;
    protected IDisplayObject _parent;
    protected List<IDisplayObject> _children;
    protected boolean _visible = true;
    public int id;
    private static int ID;
    public static Map<Integer, String> ID_MAP;

    public boolean isAnimating = false;
    Animate _direction;
    int _startTime;
    Point _startPos;
    Point _destPos;
    int _duration = 400;

    public DisplayObject(String name) {
        id = ID++;
        _name = name;
        _children = new ArrayList<IDisplayObject>();
        _rect = new Rect();
        init();
    }

    private void init() {
        if (ID_MAP == null)
            ID_MAP = new HashMap<Integer, String>();

        ID_MAP.put(id, _name);

        Screen screen = (Screen) getScreen();

        if (screen != null) {
            screen.registerEventListener(this, EventType.ADDED);
            screen.registerEventListener(this, EventType.REMOVED);
        }
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
    public void notifyEvent(ChalkieEvent evt) {

    }

    @Override
    public Rect getAbsoluteRect() {

        Rect rect = new Rect(this._rect.left, this._rect.top, this._rect.right, this._rect.bottom);
        IDisplayObject parent = this;
        while (parent.getParent() != parent && parent.getParent() != null) {
            rect.offset(parent.getParent().getRect().left, parent.getParent().getRect().top);//Offset(new Point(parent.parent.rect.X, parent.parent.rect.Y));
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
        _rect.offsetTo(x, y);
        resize();
    }

    public Rect getRect() {
        return _rect;
    }

    public void setRect(Rect _rect) {
        this._rect = new Rect(_rect.left, _rect.top, _rect.right, _rect.bottom);
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

        Screen screen = (Screen) getScreen();

        if (screen != null)
            screen.added((DisplayObject) child);

        return child;
    }

    private IDisplayObject getScreen() {
        IDisplayObject screen = this;
        while (screen != null && !(screen instanceof Screen))
            screen = screen.getParent();

        return screen;
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


        if (isAnimating) {
            float elapsed = MainView.TIME_ELAPSED - _startTime;
            float ratio = easing.Expo.easeOut(elapsed, 0.f, 1.f, _duration);

            ratio = Math.min(ratio, 1.f);
            log("update Aniamte : "  + getName() + " / " + ratio);

            updateAnimation(ratio);

            if(ratio == 1.f) {
                isAnimating = false;
                new AnimationCompleteEvent(this, _direction).dispatch();
            }

        }

    }

    private void log(String s) {
        Log.i(this.getName(), s);
    }

    private void updateAnimation(float ratio) {
        Point newPos = Point.lerp(_startPos, _destPos, ratio);
        log("pos : " + newPos.toString());
        _rect.offsetTo((int)newPos.x, (int)newPos.y);//left = (int) newPos.x;
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

    public void notifyEvent(EventType type) {

    }

    public List<IDisplayObject> get_children() {
        return _children;
    }

    public void animate(Animate direction) {
        _direction = direction;
        _startPos = new Point(_rect);
        switch (direction) {
            case LEFT:
                _destPos = new Point(_startPos.x -_rect.width(), _startPos.y);
                break;
            case RIGHT:
                _destPos = new Point(_startPos.x + _rect.width(), _startPos.y);
                break;
            case TOP:
                _destPos = new Point(_startPos.x ,_startPos.y -_rect.height());
                break;
            case BOTTOM:
                _destPos = new Point(_startPos.x, _startPos.y + _rect.height());
                break;
        }
        isAnimating = true;
        _startTime = (int) MainView.TIME_ELAPSED;

        log("start animation : " + _startPos + " - > " + _destPos);
    }

    public void insertChild(int index, IDisplayObject child) {
        _children.add(index, child);
    }
}
