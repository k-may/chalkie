package com.kevmayo.chalkie;

import android.os.Bundle;

import com.kevmayo.chalkie.android.AndroidGame;
import com.kevmayo.chalkie.interfaces.Screen;
import com.kevmayo.chalkie.view.LoadingScreen;


public class MainActivity extends AndroidGame {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		MainController.getInstance().registerGame(this);
	}


    @Override
	public Screen getInitScreen() {
		// TODO Auto-generated method stub
		return new LoadingScreen(this);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		getCurrentScreen().backButton();
	}
	
	
}
