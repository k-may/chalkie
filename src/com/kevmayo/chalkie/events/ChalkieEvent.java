package com.kevmayo.chalkie.events;

import com.kevmayo.chalkie.old.IManager;

public class ChalkieEvent {

	EventType _type;
	String _message;
	IManager _sender;
	
	public ChalkieEvent(IManager sender, EventType type, String message){
		_type = type;
		_message = message;
		_sender = sender;
	}
	
	public void dispatch(){
		_sender.getController().eventRecieved(this);
	}
	
	public IManager getSender(){
		return _sender;
	}
	
	public EventType getType(){
		return _type;
	}
	
	public String getMessage(){
		return _message;
	}
}
