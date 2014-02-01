package com.javadocmd.actionTest.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Event;

/**
 * Fires the given event on the attaching actor and then completes.
 */
public class FireEventAction extends Action {

	private Event event;
	
	public FireEventAction(Event event) {
		this.event = event;
	}
	
	@Override
	public boolean act(float delta) {
		if (getActor() != null)
			getActor().fire(event);
		return true;
	}

}
