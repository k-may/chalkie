package com.kevmayo.chalkie.old;

import android.graphics.Bitmap;

import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.events.StrokeAddedEvent;

public class Controller {

	ChalkBoard _chalkBoard;
	Model _model;

	public Controller(ChalkBoard chalkBoard, Model model) {
		_chalkBoard = chalkBoard;
		_model = model;
	}

	public void eventRecieved(ChalkieEvent evt) {

		if (evt.getType() == EventType.STROKE_ADDED) {
			// update main bitmap
			_chalkBoard.setBitmap(((StrokeAddedEvent)evt).getBitmap());
		}

		if (evt.getType() == EventType.MANAGER_READY) {
			_model.addManager(evt.getSender());
		}
	}

}
