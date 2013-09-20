package com.kevmayo.chalkie.events;
import com.kevmayo.chalkie.MainController;

public class ChalkieEvent {

	EventType _type;
	String _message;
	
	public ChalkieEvent(EventType type, String message){
		_type = type;
		_message = message;
	}
	
	public void dispatch(){
		MainController.getInstance().eventRecieved(this);
	}

	public EventType getType(){
		return _type;
	}
	
	public String getMessage(){
		return _message;
	}
}
