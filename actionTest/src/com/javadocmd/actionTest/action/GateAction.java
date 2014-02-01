package com.javadocmd.actionTest.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

/**
 * Action that waits until a particular type of event has been witnessed
 * a given amount of times, then continues.
 */
public class GateAction extends Action implements EventListener {

	/**
	 * The number of gate events we should witness before continuing.
	 */
	private int gateOccurrences;
	
	/**
	 * The class of the gate event. 
	 */
	private Class<? extends Event> eventClass;
	
	/**
	 * The number of gate events we have witnessed.
	 */
	private int count;
	
	/**
	 * Constructs a new GateAction.
	 * 
	 * @param eventClass the class to watch for.
	 * @param occurrences the number of times we should see this type of event
	 * before this action will complete.
	 */
	public GateAction(Class<? extends Event> eventClass, int occurrences) {
		this.eventClass = eventClass;
		this.gateOccurrences = occurrences;
		this.count = 0;
	}
	
	@Override
	public void restart() {
		super.restart();
		this.count = 0; // I guess I should do this in both of these?
	}
	
	@Override
	public void reset() {
		super.reset();
		this.count = 0; // I guess I should do this in both of these?
	}
	
	@Override
	public void setActor(Actor actor) {
		/* When we're added and/or removed from an 
		 * actor we need to add and/or remove ourself 
		 * as a listener on that actor.
		 */
		if (getActor() != null)
			getActor().removeListener(this);
		
		super.setActor(actor);
		
		if (actor != null)
			actor.addListener(this);
	}
	
	@Override
	public boolean handle(Event event) {
		// If this event is of our gated type, increment our event count.
		if (eventClass.isInstance(event)) {
			count += 1;
		}
		return true;
	}
	
	@Override
	public boolean act(float delta) {
		// If we've witnessed enough events, allow this action to complete.
		if (count >= gateOccurrences) {
			count = 0;
			return true;
		}
		return false;
	}
}
