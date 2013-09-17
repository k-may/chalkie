package com.kevmayo.chalkie;

import android.graphics.Point;

import com.kevmayo.chalkie.framework.TextureDO;
import com.kevmayo.chalkie.framework.TextureInfo;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Screen;

public class HomeScreen extends Screen {

	private TextureDO _bg;
	
	public HomeScreen(Game game) {
		super(game, "home");
		
		_bg = new TextureDO(new TextureInfo(Assets.startImage,new Point(200, 200), new Point(200, 200) ));
	}

	//TODO add buttons
	
	@Override
	public void update(float time) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		//g.drawImage(Assets.startImage, 0, 0);
		_bg.draw(g);
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
