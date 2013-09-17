package com.kevmayo.chalkie.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.view.View;

public class Shape extends View {

	Paint _paint;
	Path _path;
	
	public Shape(Context context) {
		super(context);

		init();
	}
	
	private void init() {
	 _paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	 _paint.setColor(0x000);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		_path.setFillType(FillType.WINDING);
		canvas.drawPath(_path, _paint);
		
		super.onDraw(canvas);
	}

}
