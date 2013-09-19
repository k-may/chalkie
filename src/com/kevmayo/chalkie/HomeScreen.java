package com.kevmayo.chalkie;

import android.graphics.Point;

import com.kevmayo.chalkie.framework.AndroidGame;
import com.kevmayo.chalkie.framework.TextureDO;
import com.kevmayo.chalkie.framework.TextureInfo;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Screen;
import com.kevmayo.chalkie.view.ButtonDO;

public class HomeScreen extends Screen {

	private TextureDO _bg;
	private TextureDO _icon;
	private ButtonDO _saveBtn;
	
	public HomeScreen(Game game) {
		super(game, "home");
		
		//_bg = new TextureDO(new TextureInfo(Assets.startImage,new Point(200, 200), new Point(200, 200) ));
		_icon = new TextureDO(Assets.Icon);
		_icon.setPos(12, 10);
		addChild(_icon);
		
		_saveBtn = new ButtonDO(Assets.SaveButton, Assets.SaveButton);
		_saveBtn.setPos(AndroidGame.SCREEN_WIDTH - _saveBtn.getRect().width() - 10, AndroidGame.SCREEN_HEIGHT - _saveBtn.getRect().height() - 10);
		addChild(_saveBtn);
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
