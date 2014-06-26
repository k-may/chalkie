package com.kevmayo.chalkie.base.stroke;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kev
 *         <p/>
 *         Class for encapsulating data related to a single stroke
 *         <p/>
 *         A single stroke is defined but an index for the finger, and a list of
 *         data
 */
public class StrokeBitmap implements IStroke<Bitmap> {

    private float _dY;
    private float _dX;
    private StrokePoint _downPos;
    private int _pointerIndex;
    private List<StrokePoint> _points;
    public boolean closed = false;

    public StrokeBitmap(int x, int y, float pressure, int pointerIndex) {
        _downPos = new StrokePoint(x, y, pressure);
        _points = new ArrayList<StrokePoint>();
        _points.add(_downPos);
        _pointerIndex = pointerIndex;
    }

    @Override
    public List<StrokePoint> getPoints() {
        return _points;
    }


    @Override
    public void close() {
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public void setPointerIndex(int pointer) {
        _pointerIndex = pointer;
    }

    @Override
    public void update(float x, float y, float pressure) {
        if (_downPos == null) {
            _downPos = new StrokePoint(x, y, pressure);
            _points = new ArrayList<StrokePoint>();
            _points.add(_downPos);
            return;
        }

        if (y < 0)
            y = 0;
        if (x < 0)
            x = 0;

        _dX = x - _downPos.x;
        _dY = y - _downPos.y;

        if (Math.abs(_dY) > Math.abs(_dX)) {
            renderVerticalBias(x, y, pressure);
        } else {
            renderHorizontalBias(x, y, pressure);
        }
        _downPos = new StrokePoint(x, y, pressure);
    }

    private void renderVerticalBias(float x, float y, float pressure) {
        if (_dY != 0) {

            int Y;
            int X;
            float m = ((float) _dX / (float) _dY);
            int dir = (int) (_dY / Math.abs(_dY));
            int ceil = (int) Math.floor(Math.abs(_dY));
            for (int i = 0; i < ceil; i++) {
                Y = (int) (i * dir + _downPos.y);
                X = (int) ((int) (i * dir * m) + _downPos.x);
                _points.add(new StrokePoint(X, Y, pressure));
            }
        }
    }

    private void renderHorizontalBias(float x, float y, float pressure) {
        if (_dX != 0) {

            int Y;
            int X;
            float m = ((float) _dY / (float) _dX);
            int dir = (int) (_dX / Math.abs(_dX));

            for (int i = 0; i < Math.abs(_dX); i++) {
                X = (int) (i * dir + _downPos.x);
                Y = (int) ((i * dir * m) + _downPos.y);
                _points.add(new StrokePoint(X, Y, pressure));

            }
        }
    }
}
