package com.kevmayo.chalkie.android.framework;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;

import com.kevmayo.chalkie.Assets;
import com.kevmayo.chalkie.base.TextureInfo;
import com.kevmayo.chalkie.interfaces.Image;
import com.kevmayo.chalkie.view.MainView;

public class AndroidGraphics extends AndroidGraphicsBase<Bitmap> {

    AssetManager assets;
    public Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();
    int _fillColor = 0x888;
    int _strokeColor = 0x000;
    float _strokeWidth = 1.f;

    private Path path;

    public AndroidGraphics(AssetManager assets) {
        super(assets);
        this.frameBuffer = Bitmap.createBitmap(MainView.SCREEN_WIDTH, MainView.SCREEN_HEIGHT,
                Bitmap.Config.ARGB_4444);

        this.assets = assets;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }

    @Override
    public void drawBuffer(Bitmap graphics) {
        //merge bitmaps
    }

    @Override
    public void clear() {


    }

    @Override
    public void image(Bitmap bgImage, int i, int i1) {

    }

    @Override
    public void strokeWeight(float width) {
        _strokeWidth = width;
    }

    @Override
    public void stroke(int color) {
        _strokeColor = color;
    }

    @Override
    public void stroke(int r, int g, int b) {
        _strokeColor = android.graphics.Color.rgb(r, g, b);
    }

    @Override
    public void fill(int color) {
        _fillColor = color;
    }

    @Override
    public void fill(int r, int g, int b) {
        _fillColor = android.graphics.Color.rgb(r, g, b);
    }

    @Override
    public void background(int r, int b, int g) {
        canvas.drawRGB(r, b, g);
    }

    @Override
    public void background(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
                (color & 0xff));
    }

    @Override
    public void line(float x, float y, float x2, float y2) {
        paint.setColor(_strokeColor);
        paint.setStrokeWidth(_strokeWidth);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    @Override
    public void drawPath(Path path, int color) {
        paint.setColor(color);
        canvas.drawPath(path, paint);
    }

    @Override
    public void rect(float x, float y, float width, float height) {
        paint.setColor(_fillColor);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x, y, width, height, paint);
    }

    private void drawImage(Image image, float x, float y, float srcX, float srcY,
                           float srcWidth, float srcHeight) {

        srcRect.left = (int) srcX;
        srcRect.top = (int) srcY;
        srcRect.right = (int) (srcX + srcWidth);
        srcRect.bottom = (int) (srcY + srcHeight);

        dstRect.left = (int) x;
        dstRect.top = (int) y;
        dstRect.right = (int) (x + srcWidth);
        dstRect.bottom = (int) (y + srcHeight);

        canvas.drawBitmap(((AndroidImage) image).bitmap, srcRect, dstRect, paint);
    }

    @Override
    public void drawImage(String name, float x, float y) {
        TextureInfo info = Assets.instance.getTexture(name);
        //canvas.drawBitmap(((AndroidImage) info.image).bitmap, x, y, null);
        drawImage(info.image, x, y, info.pos.x, info.pos.y, info.size.x, info.size.y);
    }

    public void drawScaledImage(Image Image, int x, int y, int width,
                                int height, int srcX, int srcY, int srcWidth, int srcHeight) {

        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + width;
        dstRect.bottom = y + height;

        canvas.drawBitmap(((AndroidImage) Image).bitmap, srcRect, dstRect, null);

    }

    @Override
    public void text(String text, float x, float y) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }

    @Override
    public void drawARGB(int i, int j, int k, int l) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moveTo(float x, float y) {
        if (path == null) {
            path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
        } else
            path.close();

        path.moveTo(x, y);
    }

    @Override
    public void lineTo(float x, float y) {
        if (path == null)
            log("wtf!, path null!");

    }

    @Override
    public void close() {
        path.close();
        drawPath(path, _fillColor);
        path = null;
    }

}
