<<<<<<< HEAD:src/com/kevmayo/chalkie/android/AndroidFastRenderView.java
package com.kevmayo.chalkie.android;
=======
package com.kevmayo.chalkie.android.framework;
import com.kevmayo.chalkie.MainController;

>>>>>>> f58a37f1be74bcb5064c0a740bb326d906962f0d:src/com/kevmayo/chalkie/android/framework/AndroidFastRenderView.java
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.kevmayo.chalkie.MainController;

public class AndroidFastRenderView extends SurfaceView implements Runnable {

	AndroidGame game;
	Bitmap frameBuffer;
	Thread renderThread = null;
    volatile boolean running = true;
	SurfaceHolder holder;


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
			
			MainController.getInstance().update(deltaTime);
			game.getCurrentScreen().update(deltaTime);
			game.getCurrentScreen().draw(game.getGraphics());
			
			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(dstRect);	
			canvas.drawBitmap(frameBuffer,null, dstRect, null);
			holder.unlockCanvasAndPost(canvas);
		}
	}

}
