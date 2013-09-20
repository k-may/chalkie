package com.kevmayo.chalkie.android;

import android.graphics.Rect;
import com.kevmayo.chalkie.Assets;
import com.kevmayo.chalkie.interfaces.Graphics;

public class ButtonDO extends DisplayObject {

	protected TextureInfo _over;
	protected TextureInfo _out;
	private boolean _isOver = false;

	public ButtonDO(String overStr, String outStr) {
		this(Assets.instance.getTexture(overStr), Assets.instance
				.getTexture(outStr));
	}

	public ButtonDO(TextureInfo over, TextureInfo out) {
		super("button");
		_over = over;
		_out = out;
		_rect = new Rect(0, 0, _over.size.x, _over.size.y);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		super.draw(g);

		if (_isOver)
			g.drawImage(_over.image, _rect.left, _rect.top, _over.pos.x,
					_over.pos.y, _over.size.x, _over.size.y);
		else
			g.drawImage(_out.image, _rect.left, _rect.top, _out.pos.x,
					_out.pos.y, _out.size.x, _out.size.y);
	}

}
