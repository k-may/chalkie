package com.kevmayo.chalkie.android;

import java.util.List;

import android.graphics.Rect;

import com.kevmayo.chalkie.Assets;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.IDisplayObject;
import com.kevmayo.chalkie.interfaces.Input.TouchEvent;

public class TextureDO implements IDisplayObject {

	protected Rect _rect;
	protected String _name;
	protected IDisplayObject _parent;
	protected List<IDisplayObject> _children;
	protected TextureInfo _textureInfo;
	protected boolean _visible = true;

	public TextureDO(String textureName){
		this(Assets.instance.getTexture(textureName));
	}
	
	public TextureDO(TextureInfo texture){
		_textureInfo = texture;
		_rect = new Rect(0, 0, texture.size.x, texture.size.y);
	}
	
	@Override
	public Rect getAbsoluteRect() {
		
		Rect rect = new Rect(this._rect.left, this._rect.top, this._rect.right, this._rect.bottom);
		IDisplayObject parent = this;
		while (parent.getParent() != parent && parent.getParent() != null)
		{
			rect.offset(parent.getParent().getRect().left, parent.getParent().getRect().left);//Offset(new Point(parent.parent.rect.X, parent.parent.rect.Y));
			parent = parent.getParent();
		}

		return rect;
	}
	
	public void setPos(int x,int y){
		_rect.offset(x, y);
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
		g.drawImage(_textureInfo.image,_rect.left, _rect.top, _textureInfo.pos.x, _textureInfo.pos.y, _textureInfo.size.x, _textureInfo.size.y);
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
	

	@Override
	public void handleTouch(TouchEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
