package com.kevmayo.chalkie.base.stroke;

import com.kevmayo.chalkie.base.math.Point;
import com.kevmayo.chalkie.interfaces.IEdge;

import java.util.List;

/**
 * Created by Kev on 26/06/2014.
 */
public interface IStrokeRenderer<T> {
    void start();
    void dispose();
    void drawStroke(T buffer, IEdge<Point> edge, List<StrokePoint> points);
}
