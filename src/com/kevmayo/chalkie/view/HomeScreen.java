package com.kevmayo.chalkie.view;

import android.graphics.Color;

import com.kevmayo.chalkie.Assets;
import com.kevmayo.chalkie.android.AndroidGame;
import com.kevmayo.chalkie.android.ButtonDO;
import com.kevmayo.chalkie.android.TextureDO;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Screen;

public class HomeScreen extends Screen {

	private TextureDO _bg;
	private TextureDO _icon;
	private ButtonDO _saveBtn;

	public HomeScreen(Game game) {
		super(game, Screen.HOME);

		_icon = new TextureDO(Assets.Icon);
		_icon.setPos(30, 15);
		addChild(_icon);

		_saveBtn = new SaveButton();
		_saveBtn.setPos(AndroidGame.SCREEN_WIDTH - _saveBtn.getRect().width()
				- 10, 15);
		addChild(_saveBtn);
	}

	// TODO add buttons

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.clearScreen(Color.argb(255, 12, 13, 14));
		super.draw(g);
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
