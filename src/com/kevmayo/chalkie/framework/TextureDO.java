package com.kevmayo.chalkie.framework;

import java.util.List;

import android.graphics.Rect;

import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.IDisplayObject;
import com.kevmayo.chalkie.interfaces.Image;

public class TextureDO implements IDisplayObject {

	private Rect _rect;
	private String _name;
	private IDisplayObject _parent;
	private List<IDisplayObject> _children;
	private TextureInfo _textureInfo;
	
	private boolean _visible = true;

	public TextureDO(TextureInfo texture){
		_textureInfo = texture;
		_rect = new Rect(0, 0, texture.size.x, texture.size.y);
	}
	
	@Override
	public Rect getRect() {
		return _rect;
	}

	@Override
	public void setRect(Rect rect) {
		_rect = rect;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void update(float time) {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(_textureInfo.image,_rect.left, _rect.left, _textureInfo.pos.x, _textureInfo.pos.y, _textureInfo.size.x, _textureInfo.size.y);
	}

	@Override
	public IDisplayObject getParent() {
		return _parent;
	}

	@Override
	public void setParent(IDisplayObject parent) {
		_parent = parent;
	}

	@Override
	public IDisplayObject removeChild(IDisplayObject child) {
		throw new IllegalArgumentException("ImageDO can't have childs");
	}

}
