package com.kevmayo.chalkie;

import android.graphics.Point;

import com.kevmayo.chalkie.framework.TextureDO;
import com.kevmayo.chalkie.framework.TextureInfo;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Screen;

public class HomeScreen extends Screen {

	private TextureDO _bg;
	private TextureDO _icon;
	
	
	public HomeScreen(Game game) {
		super(game, "home");
		
		//_bg = new TextureDO(new TextureInfo(Assets.startImage,new Point(200, 200), new Point(200, 200) ));
		_icon = new TextureDO(Assets.Icon);
		_icon.setPos(12, 10);
		addChild(_icon);
	}

	//TODO add buttons
	
	@Override
	public void update(float time) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub

	}

}
