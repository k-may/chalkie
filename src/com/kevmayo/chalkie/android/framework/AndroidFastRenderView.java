
package com.kevmayo.chalkie.android.framework;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.kevmayo.chalkie.interfaces.Graphics;

public class AndroidFastRenderView extends SurfaceView implements Runnable {

	AndroidGame game;
	Bitmap frameBuffer;
	Thread renderThread = null;
    volatile boolean running = true;
	SurfaceHolder holder;


	public AndroidFastRenderView(AndroidGame game, Graphics graphics) {
		super(game);
		this.game = game;
		this.frameBuffer = ((AndroidGraphics)graphics).frameBuffer;
		this.holder = getHolder();
	}


    @Override
    public boolean isInEditMode() {
        return true;
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

            game.draw();

			
			Canvas canvas = holder.lockCanvas();
			canvas.getClipBounds(dstRect);	
			canvas.drawBitmap(frameBuffer,null, dstRect, null);
			holder.unlockCanvasAndPost(canvas);
		}
	}

}
