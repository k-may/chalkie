package com.kevmayo.chalkie.base.stroke;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

import com.kevmayo.chalkie.android.framework.AndroidImage;
import com.kevmayo.chalkie.base.ChalkieHelper;
import com.kevmayo.chalkie.base.math.Point;
import com.kevmayo.chalkie.interfaces.IEdge;

import java.util.List;

public class StrokeRenderer extends StrokeRendererBase<Bitmap> {

	private AndroidImage _strokeBuffer;
	private Bitmap _boardBuffer;

	IEdge _edge;

    Bitmap buffer;

    public StrokeRenderer(Bitmap g) {
        super(g, "AndroidStrokeRenderer");
    }

    public void setEdge(IEdge edge) {
		_edge = edge;
	}

    public void start(){
		if (buffer != null )//|| _boardBuffer != null)
			dispose();

		_strokeBuffer = ChalkieHelper.CreateCanvas();
       // return (T) buffer;
	}

    @Override
	public void update(float time) {
		// TODO Auto-generated method stub
		super.update(time);

		// draw to buffer
		if (_invalidated) {
			_invalidated = false;
			drawStroke(_strokeBuffer.bitmap, _edge, _stroke.getPoints());

			if (_stroke.isClosed()) {
				//_boardBuffer.merge(_strokeBuffer);
				//mergeBuffers();
				_strokeBuffer.dispose();
				_strokeBuffer = ChalkieHelper.CreateCanvas();
				_stroke = null;
			}
		}
	}

	private void mergeBuffers(){
		// TODO Auto-generated method stub
		Bitmap src = _strokeBuffer.bitmap;
		Bitmap merged = Bitmap.createBitmap(_boardBuffer.getWidth(),
				_boardBuffer.getHeight(), _boardBuffer.getConfig());
		Canvas canvas = new Canvas(merged);
		canvas.drawBitmap(_boardBuffer, new Matrix(), null);
		canvas.drawBitmap(src, 0, 0, null);
		_boardBuffer.recycle();
		_boardBuffer = merged;
	}


	public void dispose() {
		_strokeBuffer.dispose();
		_strokeBuffer = null;
	}



	public void drawStroke(Bitmap buffer, IEdge<Point> edge,
			List<StrokePoint> points) {
		Bitmap bitmap = buffer;
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);

		for (int i = 0; i < points.size(); i++) {
			StrokePoint sPt = points.get(i);
			Path path = new Path();
			List<Point> shape = edge.getShape(sPt);
			Point pt = shape.get(0);
			path.moveTo(pt.x, pt.y);
			for (int j = 1; j < shape.size(); j++) {
				pt = shape.get(j);
				path.lineTo(pt.x, pt.y);
			}
			path.close();
			canvas.drawPath(path, paint);
		}
	}

}
