package com.kevmayo.chalkie;

import com.kevmayo.chalkie.framework.AndroidGame;
import com.kevmayo.chalkie.interfaces.Screen;

/**
 * @author kev
 *
 * Using http://www.kilobolt.com/day-1-introduction-to-android.html, amazing game framework tutorial
 */

public class MainActivity extends AndroidGame {

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
