package com.kevmayo.chalkie.base.edges;

import static com.kevmayo.chalkie.base.math.GeometryHelper.*;

import java.util.ArrayList;
import java.util.List;

import com.kevmayo.chalkie.base.math.Point;
import com.kevmayo.chalkie.interfaces.IEdge;
import com.kevmayo.chalkie.base.StrokePoint;

public class SimpleEdge implements IEdge<Point>{

	private List<Point> _shape;
	public SimpleEdge(){
		_shape = RandomTriangle(100);
		
		//translate shape to incenter
		Point pt1 = _shape.get(0);
		Point pt2 = _shape.get(1);
		Point pt3 = _shape.get(2);
		Point mid = new Point(pt1.x + (pt2.x - pt1.x)/2, pt1.y + (pt2.y - pt1.y)/2);
		Point incenter = new Point();
		incenter.x = (int)(pt3.x + (float)2/3*(mid.x - pt3.x));
		incenter.y = (int)(pt3.y + (float)2/3*(mid.x - pt3.y));
		_shape = new ArrayList<Point>();
		for(int i = 0; i < 3; i ++){
			Point p = _shape.get(i);
			_shape.set(1, new Point(incenter.x - p.x, incenter.y - p.y));
		}
	}
	@Override
	public List<Point> getShape(StrokePoint pt) {
		// TODO Auto-generated method stub
		List<Point> shape = new ArrayList<Point>();

		for(int i = 0 ;i < _shape.size(); i ++){
			Point p = _shape.get(i);
			//translate by location
			p = new Point(pt.x + p.x, pt.y + p.y);
			shape.add(p);
		}
		return shape;
	}

}
