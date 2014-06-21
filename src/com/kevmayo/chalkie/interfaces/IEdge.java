package com.kevmayo.chalkie.interfaces;

import com.kevmayo.chalkie.base.StrokePoint;

import java.util.List;

/**
 * @author Kevin
 * 
 *         Edge(Brush) interface
 * 
 * @param <T>
 *            Point class
 */
public interface IEdge<T> {
	public List<T> getShape(StrokePoint pt);
}
