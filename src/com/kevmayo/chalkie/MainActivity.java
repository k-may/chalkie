package com.kevmayo.chalkie;

import android.os.Bundle;

import com.kevmayo.chalkie.view.MainView;


public class MainActivity extends MainView {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		MainController.getInstance().registerGame(this);
	}

	
}
