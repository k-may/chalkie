package com.kevmayo.chalkie.view;

import com.kevmayo.chalkie.framework.DisplayObject;
import com.kevmayo.chalkie.framework.TextureInfo;
import com.kevmayo.chalkie.interfaces.Graphics;

public class ButtonDO extends DisplayObject {

	protected TextureInfo _over;
	protected TextureInfo _out;
	private boolean _isOver = false;
	
	public ButtonDO(TextureInfo over, TextureInfo out){
		super("button");
		_over = over;
		_out = out;
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		super.draw(g);
		
		if(_isOver)
			g.drawImage(_over.image, _rect.left, _rect.top, _over.pos.x, _over.pos.y, _over.size.x, _over.size.y);
		else
			g.drawImage(_out.image, _rect.left, _rect.top, _out.pos.x, _out.pos.y, _out.size.x, _out.size.y);
	}
}
