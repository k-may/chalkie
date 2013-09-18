package com.kevmayo.chalkie.framework;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;

import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.Image;

public class AndroidGraphics implements Graphics {

	AssetManager assets;
	Bitmap frameBuffer;
	Canvas canvas;
	Paint paint;
	Rect srcRect = new Rect();
	Rect dstRect = new Rect();

	public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
		this.frameBuffer = frameBuffer;
		this.assets = assets;
		this.canvas = new Canvas(frameBuffer);
		this.paint = new Paint();
	}

	@Override
	public Image newImage(String fileName, ImageFormat format) {
		Config config = null;
		if (format == ImageFormat.RGB565)
			config = Config.RGB_565;
		else if (format == ImageFormat.ARGB4444)
			config = Config.ARGB_4444;
		else
			config = Config.ARGB_8888;

		Options options = new Options();
		options.inPreferredConfig = config;

		InputStream in = null;
		Bitmap bitmap = null;

		try {
			in = assets.open(fileName);
			bitmap = BitmapFactory.decodeStream(in, null, options);
			if (bitmap == null)
				throw new RuntimeException("couldn't load bitmap from asset : "
						+ fileName);
		} catch (IOException e) {
			throw new RuntimeException("couldn't load bitmap from asset : "
					+ fileName);
		} finally {
			if (in == null)
				;
			try {
				in.close();
			} catch (IOException e) {
			}
		}

		if (bitmap.getConfig() == Config.RGB_565)
			format = ImageFormat.RGB565;
		else if (bitmap.getConfig() == Config.ARGB_4444)
			format = ImageFormat.ARGB4444;
		else
			format = ImageFormat.ARGB8888;
		
		bitmap.setDensity(Bitmap.DENSITY_NONE);
		
		return new AndroidImage(bitmap, format);
	}

	@Override
	public void clearScreen(int color) {
		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
				(color & 0xff));
	}

	@Override
	public void drawLine(int x, int y, int x2, int y2, int color) {
		paint.setColor(color);
		canvas.drawLine(x, y, x2, y2, paint);
	}

	@Override
	public void drawPath(Path path, int color) {
		paint.setColor(color);
		canvas.drawPath(path, paint);
	}

	@Override
	public void drawRect(int x, int y, int width, int height, int color) {
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawRect(x, y, width, height, paint);
	}

	@Override
	public void drawImage(Image image, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight) {

		srcRect.left = srcX;
		srcRect.top = srcY;
		srcRect.right = srcX + srcWidth;
		srcRect.bottom = srcX + srcHeight;

		dstRect.left = x;
		dstRect.top = y;
		dstRect.right = x + srcWidth;
		dstRect.bottom = y + srcHeight;
		
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		
		canvas.drawBitmap(((AndroidImage) image).bitmap, srcRect, dstRect, paint);
	}

	@Override
	public void drawImage(Image image, int x, int y) {
		canvas.drawBitmap(((AndroidImage) image).bitmap, x, y, null);
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
	public void drawString(String text, int x, int y, Paint paint) {
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

}
