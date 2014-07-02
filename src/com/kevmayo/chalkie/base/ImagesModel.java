package com.kevmayo.chalkie.base;

import com.kevmayo.chalkie.view.ImageView;

import java.util.List;

/**
 * Created by Kev on 28/06/2014.
 */
public class ImagesModel {

    private List<String> _paths;
    private int _currentIndex = 0;

    public void loadImage(ImageView view, String path){

        if(view.task != null){
            if(view.task.path != path && view.task.path != "")
                view.task.cancel(true);
        }
        view.dispose();
        view.task = new ImageLoaderTask(view);
        view.task.execute(path);
    }

    public String getPath(int index) {
        return _paths.get(index);
    }
}
