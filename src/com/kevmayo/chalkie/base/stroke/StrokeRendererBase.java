package com.kevmayo.chalkie.base.stroke;

import android.graphics.Rect;
import android.util.Log;

import com.kevmayo.chalkie.base.DisplayObject;
import com.kevmayo.chalkie.base.Pool;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Input;
import com.kevmayo.chalkie.view.MainView;

/**
 * Created by Kev on 26/06/2014.
 */
public abstract class StrokeRendererBase<T> extends DisplayObject implements IStrokeRenderer<T> {

    public static int MAX_STROKES = 100;
    protected Pool<StrokeVertex> strokePool;
    protected IStroke<T> _stroke;
    protected boolean _invalidated = false;
    protected T graphics;

    protected int currentIndex;
    protected int _currentIndex = 0;

    public StrokeRendererBase(T graphics, String name) {
        super("strokeController");

        this.graphics = graphics;

        Pool.PoolObjectFactory<StrokeVertex> factory = new Pool.PoolObjectFactory<StrokeVertex>() {
            @Override
            public StrokeVertex createObject() {
                // TODO Auto-generated method stub
                return new StrokeVertex();
            }
        };
        strokePool = new Pool<StrokeVertex>(factory, MAX_STROKES);

        _rect = new Rect(0, 0, MainView.SCREEN_WIDTH,
                MainView.SCREEN_HEIGHT);
    }

    @Override
    public void update(float time) {
        super.update(time);
    }

    @Override
    public void draw(Graphics g) {
        if(_invalidated){
            _invalidated = false;

            if(_currentIndex > 1)
                drawStroke(graphics, null, _stroke.getPoints());

            _currentIndex = _stroke.getPoints().size() - 1;
        }
        g.drawBuffer(graphics);
    }

    public boolean handleTouch(Input.TouchEvent evt) {
        //log(evt.toString());
        switch (evt.type) {
            case Input.TouchEvent.TOUCH_DOWN:
                // create new stroke
                currentIndex = 0;
                _stroke = (IStroke<T>) strokePool.newObject();
            case Input.TouchEvent.TOUCH_MOVE:
                if (_stroke != null) {
                    _stroke.update(evt.x, evt.y, evt.pressure);
                    // test pointer index?
                    _stroke.setPointerIndex( evt.pointer);
                    _invalidated = true;
                }
                break;
            case Input.TouchEvent.TOUCH_UP:
                // dispose stroke
                if (_stroke != null) {
                    _stroke.update(evt.x, evt.y, evt.pressure);
                    _stroke.close();
                    strokePool.free((StrokeVertex) _stroke);
                    _invalidated = true;
                    log("touch up : " + _stroke.getPoints().size());
                }
                break;
        }

        return true;
    }

    protected void log(String s) {

        Log.i(getClass().getSimpleName(), s);
    }
}
