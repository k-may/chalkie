package com.kevmayo.chalkie.view;

import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;

import com.kevmayo.chalkie.Assets;
import com.kevmayo.chalkie.android.AndroidGame;
import com.kevmayo.chalkie.base.ButtonDO;
import com.kevmayo.chalkie.base.TextureDO;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Input;
import com.kevmayo.chalkie.interfaces.Screen;

public class HomeScreen extends Screen {

    private HomeBg _bg;
	private TextureDO _icon;
	private ButtonDO _saveBtn;

	public HomeScreen(Game game) {
		super(game, Screen.HOME);

        _bg = new HomeBg();
        _bg.setRect(new Rect(0,0,AndroidGame.SCREEN_WIDTH, AndroidGame.SCREEN_HEIGHT));
        addChild(_bg);

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
		g.clearScreen(Color.argb(255, 255, 255, 255));
		super.draw(g);
	}

    @Override
    public boolean handleTouch(Input.TouchEvent evt) {
        super.handleTouch(evt);

        Log.i("homescreen", "handle touch!");
        return true;
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
