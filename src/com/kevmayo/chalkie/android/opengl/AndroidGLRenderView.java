package com.kevmayo.chalkie.android.opengl;

import android.opengl.GLSurfaceView;

import com.kevmayo.chalkie.android.framework.AndroidGame;

/**
 * Created by Kev on 24/06/2014.
 */
public class AndroidGLRenderView extends GLSurfaceView {

    AndroidGLRenderer renderer;

    public AndroidGLRenderView(AndroidGame game, AndroidGLRenderer renderer) {
        super(game);

        setEGLContextClientVersion(2);
        this.renderer = renderer;
        setRenderer(renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public void onPause() {
        super.onPause();
        renderer.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();
        renderer.onResume();
    }
}
