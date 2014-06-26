package com.kevmayo.chalkie.base.stroke;

import java.util.List;

/**
 * Created by Kev on 26/06/2014.
 */
public interface IStroke<T> {
    List<StrokePoint> getPoints();
    void update(float x, float y, float pressure);
    void close();
    void setPointerIndex(int pointer);
    boolean isClosed();
}
