package com.kevmayo.chalkie.view;

import android.graphics.Color;

import com.kevmayo.chalkie.Assets;
import com.kevmayo.chalkie.android.framework.AndroidGame;
import com.kevmayo.chalkie.android.framework.AndroidGraphics;
import com.kevmayo.chalkie.base.ButtonDO;
import com.kevmayo.chalkie.base.StrokeRenderer;
import com.kevmayo.chalkie.base.edges.BreakingEdge;
import com.kevmayo.chalkie.interfaces.Game;
import com.kevmayo.chalkie.interfaces.Screen;

public class ChalkBoardScreen extends Screen{

	private ButtonDO _saveBtn;
	private StrokeRenderer _controller;
	
	public ChalkBoardScreen(Game game) {
		super(game, Screen.CHALKBOARD);
		
		game.getGraphics().clearScreen(Color.argb(0, 255, 255, 255));
		
		_controller = new StrokeRenderer(((AndroidGraphics)game.getGraphics()).frameBuffer);
		//_controller.setEdge(new SimpleEdge());
		_controller.setEdge(new BreakingEdge());
		_controller.start();
		addChild(_controller);
		

		_saveBtn = new ButtonDO(Assets.SaveButton, Assets.SaveButton);
		_saveBtn.setPos(AndroidGame.SCREEN_WIDTH - _saveBtn.getRect().width()
				- 10, 15);
		addChild(_saveBtn);
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
	
	@Override
	public void update(float time) {
		// TODO Auto-generated method stub
		super.update(time);

	}

    @Override
    public void resize() {

    }

}
