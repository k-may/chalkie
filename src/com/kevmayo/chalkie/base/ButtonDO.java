package com.kevmayo.chalkie.base;

import android.graphics.Rect;

import com.kevmayo.chalkie.Assets;
import com.kevmayo.chalkie.base.math.ButtonHandler;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Input;

public class ButtonDO extends DisplayObject {

	protected TextureInfo _over;
	protected TextureInfo _out;
	private boolean _isOver = false;


	public ButtonDO(String overStr, String outStr) {
		this(Assets.instance.getTexture(overStr), Assets.instance
				.getTexture(outStr));
	}

	public ButtonDO(TextureInfo over, TextureInfo out) {
		super(over.name);
		_over = over;
		_out = out;
		_rect = new Rect(0, 0, (int) _over.size.x, (int)_over.size.y);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		super.draw(g);

		if (_isOver)
			g.drawImage(_over.name, _rect.left, _rect.top);
		else
			g.drawImage(_out.name, _rect.left, _rect.top);
	}

    @Override
    public boolean handleTouch(Input.TouchEvent evt) {
        if (evt.type == Input.TouchEvent.TAPPED) {
            if (this._parent instanceof ButtonHandler)
                ((ButtonHandler) this._parent).pressed(_name);

            return true;
        }

        return false;
    }

}
