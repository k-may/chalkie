package com.kevmayo.chalkie.framework;

import com.kevmayo.chalkie.MainActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidFastRenderView extends SurfaceView implements Runnable {

	AndroidGame game;
	Bitmap frameBuffer;
	Thread renderThread = null;
	SurfaceHolder holder;
	volatile boolean running = true;

	public AndroidFastRenderView(AndroidGame game, Bitmap frameBuffer) {
		super(game);
		this.game = game;
		this.frameBuffer = frameBuffer;
		this.holder = getHolder();
	}

	public void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}

	public void pause(){
		running = false;
		while(true){
			try{
				renderThread.join();
				break;
			}catch(InterruptedException e){
				//try again
			}
		}
	}
	
	@Override
	public void run() {
		Rect dstRect = new Rect();
		long startTime = System.nanoTime();
		while(running){
			if(!holder.getSurface().isValid())
				continue;
			
			float deltaTime = (System.nanoTime() - startTime) /  10000000.000f;
			startTime = System.nanoTime();
			
			if(deltaTime > 3.15){
				deltaTime = (float) 3.15;
			}
			
			game.getCurrentScreen().update(deltaTime);
			game.getCurrentScreen().paint(deltaTime);
			
			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(dstRect);
			canvas.drawBitmap(frameBuffer,null, dstRect, null);
			holder.unlockCanvasAndPost(canvas);
		}
	}

}
