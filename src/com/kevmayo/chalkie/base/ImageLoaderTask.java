package com.kevmayo.chalkie.base;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.kevmayo.chalkie.view.ImageView;
import com.kevmayo.chalkie.view.MainView;

import java.lang.ref.WeakReference;

/**
 * Created by Kev on 28/06/2014.
 */
public class ImageLoaderTask extends AsyncTask<String, Void, Bitmap> {

    private WeakReference<ImageView> imageViewRef;
    public String path;

    public ImageLoaderTask(ImageView imageViewRef){
        this.imageViewRef = new WeakReference<ImageView>(imageViewRef);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        path = params[0];
        return decodeSampleBitmapFromFilePath(path,(int)( MainView.SCREEN_WIDTH * ImageView.RATIO),
                (int)(MainView.SCREEN_HEIGHT * ImageView.RATIO));
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(imageViewRef != null && bitmap != null){
            ImageView imageView = imageViewRef.get();
            if(imageView != null && imageView.path == path)
                imageView.setBitmap(bitmap);
        }
    }

    public static Bitmap decodeSampleBitmapFromFilePath(String path, int reqWidth, int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);

    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
