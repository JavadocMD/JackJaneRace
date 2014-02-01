package com.javadocmd.actionTest.event;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.javadocmd.actionTest.Person;

/**
 * Listener that keeps track of who wins each race. Each racer fires
 * a RacerDoneEvent when they get to the finish line. After two of these
 * events, we know we can reset. The first one we see corresponds to the
 * winner of the race. When the race is over, fire a RaceWinEvent so 
 * other listeners can respond to that.
 */
public class FinishLineListener implements EventListener {

	/** The actor this listener is attached to (so we can fire events there) */
	private Actor actor;
	private Person doneFirst = null;

	public FinishLineListener(Actor actor) {
		this.actor = actor;
	}

	@Override
	public boolean handle(Event event) {
		if (!(event instanceof RacerDoneEvent))
			return false;

		RacerDoneEvent rde = (RacerDoneEvent) event;
		logRacer(rde.getPerson());
		return true;
	}

	private void logRacer(Person person) {
		if (doneFirst == null) {
			// This character is first!
			doneFirst = person;
		} else {
			// This character is second, fire the event and reset.
			actor.fire(new RaceWinEvent(doneFirst));
			doneFirst = null;
		}
	}
}
