package com.kevmayo.chalkie.view;

import android.graphics.Rect;
import android.util.Log;

import com.kevmayo.chalkie.android.framework.AndroidGame;
import com.kevmayo.chalkie.base.DisplayObject;
import com.kevmayo.chalkie.base.exceptions.ChalkieException;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.IDisplayObject;
import com.kevmayo.chalkie.interfaces.IScreen;
import com.kevmayo.chalkie.interfaces.Input;
import com.kevmayo.chalkie.interfaces.Input.TouchEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kevin
 *         <p/>
 *         Screen serves as base container for each interface
 *         <p/>
 *         Screen was originally developed (based on the Android Game book) to
 *         only be a pattern for navigation purposes, but I've intended it to be
 *         also integrated into the display list framework, so it automates the
 *         drawing of children, updating etc.
 */

public abstract class Screen implements IScreen {

    protected final Game game;
    public String _name = "";
    protected IDisplayObject _parent;
    protected List<IDisplayObject> _children;
    private Map<String, List<Integer>> _observers;

    public static String HOME = "home";
    public static String CHALKBOARD = "chalkBoard";

    public Screen(Game game, String name) {
        this.game = game;
        this._name = name;
        _observers = new HashMap<String, List<Integer>>();

        _children = new ArrayList<IDisplayObject>();
    }

    @Override
    public Rect getAbsoluteRect() {
        return null;
    }

    public void update(float time) {

        runTouches();

        for (int i = 0; i < _children.size(); i++)
            _children.get(i).update(time);
    }

    private void runTouches() {

        List<TouchEvent> touches = this.game.getInput().getTouchEvents();

        for (int i = 0; i < touches.size(); i++) {
            TouchEvent evt = touches.get(i);
            for (int j = 0; j < _children.size(); j++) {
                try {
                    Rect rect = _children.get(j).getRect();
                    if (rect.contains(evt.x, evt.y)) {
                        _children.get(j).handleTouch(evt);
                    }
                } catch (NullPointerException e) {
                    trace("null pointer : " + e.getMessage());
                }
            }
        }
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

    public Rect getRect() {
        return new Rect(0, 0, AndroidGame.SCREEN_WIDTH,
                AndroidGame.SCREEN_HEIGHT);
    }

    @Override
    public boolean handleTouch(TouchEvent evt) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean handleGesture(Input.TouchEvent evt) {
        switch (evt.type) {
            case Input.TouchEvent.SWIPE_DOWN:
            case Input.TouchEvent.SWIPE_LEFT:
            case Input.TouchEvent.SWIPE_RIGHT:
            case Input.TouchEvent.SWIPE_UP:
                break;
        }

        return false;
    }

    @Override
    public void resize() {
        for (int i = 0; i < _children.size(); i++) {
            _children.get(i).resize();
        }
    }

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();

    public abstract void backButton();

    private void trace(String msg) {
        Log.i("Screen : " + _name, msg);
    }

    @Override
    public int get_numChildren() {
        return _children.size();
    }

    @Override
    public IDisplayObject get_childAt(int i) {
        return _children.get(i);
    }

    public ArrayList<IDisplayObject> getTargetsAtLocation(int x, int y) {

        IDisplayObject element = this;

        ArrayList<TouchTarget> targets = new ArrayList<TouchTarget>();

        try {
            getTargetsAtLocation(element, x, y, targets, 0);
        } catch (Exception e) {
            // exception to exit recursion
        }

        //sort targets by capture level
        //Collections.sort(targets);


        ArrayList<IDisplayObject> elements = new ArrayList<IDisplayObject>();
        elements.add(this);

        for (TouchTarget target : targets) {
            if (!elements.contains(target.target))
                elements.add(target.target);
            else
                throw new ChalkieException("odd targets!");
        }

        return elements;
    }


    private void getTargetsAtLocation(IDisplayObject parent, float x, float y,
                                      ArrayList<TouchTarget> elements, int level) throws Exception {
        for (int i = parent.get_numChildren() - 1; i >= 0; i--) {
            IDisplayObject child = parent.get_childAt(i);

            Rect rect = child.getRect();
            if (rect.contains((int) x, (int) y)) {
                if (child.get_numChildren() == 0) {
                    //if (child.isTouchEnabled())
                    elements.add(new TouchTarget(child, level));

                    break;
                } else {
                    //if (child.isTouchEnabled())
                    elements.add(new TouchTarget(child, level));

                    // exit recursion if press target found
                    /// if (child.isPressTarget() || child.isHoverTarget())
                    //  break; //

                    x -= child.getRect().left;
                    y -= child.getRect().right;

                    getTargetsAtLocation(child, x, y, elements, level + 1);
                }
            }
        }

    }

    public void registerEventListener(DisplayObject object, EventType type) {
        List<Integer> childs;
        if (_observers.containsKey(type.name())) {
            childs = _observers.get(type.name());
        } else {
            childs = new ArrayList<Integer>();
            _observers.put(type.name(), childs);
        }

        childs.add(object.id);

    }

    public void added(DisplayObject child) {
        if (_observers.containsKey(EventType.ADDED)) {
            if (_observers.get(EventType.ADDED).contains(child.id))
                child.notifyEvent(EventType.ADDED);
        }
    }

    protected DisplayObject getChildById(Integer integer) {
        DisplayObject child = null;
        return getChildById(_children, integer, child);
    }

    protected DisplayObject getChildById(List<IDisplayObject> children, Integer integer, DisplayObject child) {
        for (int i = 0; i < children.size(); i++) {
            child = ((DisplayObject) children.get(i)).id == integer ?
                    (DisplayObject) children.get(i) : child;

            if (child == null) {
                if (child.get_numChildren() > 0)
                    child = getChildById(child.get_children(), integer, child);
            }

            if (child != null)
                return child;
        }

        return child;
    }


    public class TouchTarget implements Comparable<TouchTarget> {

        int level;
        IDisplayObject target;

        public TouchTarget(IDisplayObject target, int level) {
            this.level = level;
            this.target = target;
        }

        @Override
        public int compareTo(TouchTarget another) {
            return this.level - another.level;
        }
    }
}
