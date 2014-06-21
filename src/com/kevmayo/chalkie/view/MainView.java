package com.kevmayo.chalkie.view;

import com.kevmayo.chalkie.android.framework.AndroidGame;

/**
 * Created by Kev on 21/06/2014.
 */
public class MainView extends AndroidGame {

    private HomeScreen homeScreen;

    @Override
    public Screen getInitScreen() {
        // TODO Auto-generated method stub
        return new LoadingScreen(this);
    }

    @Override
    public Screen getHomeScreen() {
        if(homeScreen == null) {
            homeScreen = new HomeScreen(this);
        }

        return homeScreen;
    }
}
