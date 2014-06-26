package com.kevmayo.chalkie.interfaces;

import com.kevmayo.chalkie.view.Screen;

public interface Game {
	public Audio getAudio();

	public Input getInput();

	public FileIO getFileIO();

    public void draw();

	public Graphics getGraphics();

	public void setScreen(Screen screen);

	public Screen getCurrentScreen();

	public Screen getInitScreen();

    public Screen getHomeScreen();

    public void saveImage();


}
