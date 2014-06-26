package com.kevmayo.chalkie.base.stroke;

import java.util.ArrayList;
import java.util.List;

import processing.core.PGraphics;
import processing.core.PVector;

/**
 * Created by Kev on 26/06/2014.
 */
public class StrokeVertex implements IStroke<PGraphics> {

    boolean isDown = false;
    int _pointerIndex;
    StrokePoint _currentPosition;
    private List<StrokePoint> _points;
    public PVector direction;


    public StrokeVertex() {

    }

    @Override
    public boolean isClosed() {
        return !isDown;
    }

    @Override
    public List<StrokePoint> getPoints() {
        return _points;
    }

    @Override
    public void update(float x, float y, float pressure) {

        if (!isDown) {
            isDown = true;
            _points = new ArrayList<StrokePoint>();
            direction = new PVector();
            _currentPosition = new StrokePoint(x, y, pressure);
        } else {

            StrokePoint p = new StrokePoint(x, y, pressure);

            PVector newDirection = new PVector(p.x - _currentPosition.x, p.y - _currentPosition.y);
            newDirection.normalize();

            direction = PVector.lerp(direction, newDirection, 0.5f);

            _currentPosition = p;
        }

        _points.add(_currentPosition);
    }

    @Override
    public void close() {
        isDown = false;
    }

    @Override
    public void setPointerIndex(int pointer) {
        _pointerIndex = pointer;
    }
}
