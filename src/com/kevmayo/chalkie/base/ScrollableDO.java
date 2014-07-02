package com.kevmayo.chalkie.base;

import android.graphics.Rect;
import android.util.Log;

import com.kevmayo.chalkie.base.math.Point;
import com.kevmayo.chalkie.interfaces.IDisplayObject;
import com.kevmayo.chalkie.interfaces.Input;

/**
 * Created by Kev on 29/06/2014.
 */
public class ScrollableDO extends DisplayObject implements IScrollable {
    private boolean _invalidated;

    private float _speed;
    private boolean _isDown = false;
    private boolean _isDownGood = false;
    private Point _downPos;
    private Point _pointerPos;
    private Point _startPos;

    private DisplayObject _container;
    private Point _lastPos;

    private int scrollPos = 0;

    public ScrollableDO(String name) {
        super(name);

        _container = new DisplayObject("scrollContainer");
    }

    @Override
    public void update(float time) {
        super.update(time);

        //update scroll
        if (_isDownGood) {
            _invalidated = true;
            Point difference = _pointerPos.sub(_downPos);

            scrollPos = (int) (_startPos.y + difference.y);
        } else {
            if (_speed > 1) {
                _invalidated = true;
                scrollPos += _speed;

                _speed *= 0.9;
            }
        }

        if (_invalidated) {
            _invalidated = false;

            if (sort()) {

            }
        }
    }

    private boolean sort() {

        boolean changed = false;
        for (int i = 0; i < _container.get_numChildren(); i++) {
            IDisplayObject child = _container.get_childAt(i);
            Rect rect = child.getAbsoluteRect();

            //resort children
            //when child reset() it will dispatch to update its image
            if (rect.top < -rect.height()) {
                _container.removeChild(child);
                _container.addChild(child);
                ((IScrollable) child).reset();
                changed = true;
            } else if (rect.top > _container.getRect().height()) {
                _container.removeChild(child);
                _container.insertChild(0, child);
                ((IScrollable) child).reset();
                changed = true;
            }

        }

        return changed;
    }

    @Override
    public boolean handleTouch(Input.TouchEvent evt) {

        if (evt.type == Input.TouchEvent.TOUCH_DOWN) {
            _isDown = true;
            _downPos = evt.getPos();
            _startPos = new Point(_container.getRect().left, _container.getRect().top);
        } else if (evt.type == Input.TouchEvent.TOUCH_UP) {
            _isDown = false;
            _isDownGood = false;
            _speed = evt.y - _lastPos.y;
            //TODO snapping!
        } else if (evt.type == Input.TouchEvent.TOUCH_MOVE) {
            _lastPos = _pointerPos;
            _pointerPos = evt.getPos();

            _isDownGood = _isDownGood || _pointerPos.y - _downPos.y > 100;

        }


        return super.handleTouch(evt);
    }

    @Override
    public IDisplayObject addChild(IDisplayObject child) {

        if (child instanceof IScrollable)
            return super.addChild(child);
        else
            log("can't add child to scrollable :" + child.getName());

        return null;
    }

    private void log(String s) {
        Log.i(getClass().getSimpleName(), s);
    }

    @Override
    public void reset() {

    }

    @Override
    public int getScrollPos() {
        return scrollPos;
    }

    @Override
    public int getNumItems() {
        return _container.get_numChildren();
    }

    public int getItemIndex(DisplayObject displayObject) {
        int index = -1;
        int count = 0;
        do {
            DisplayObject dO = (DisplayObject) _container.get_childAt(count);
            if (dO == displayObject) {
                index = count;
                break;
            }

            count++;
        } while (count < _container.get_numChildren());

        return index;
    }

}
