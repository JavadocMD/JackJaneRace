package com.javadocmd.actionTest.event;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.javadocmd.actionTest.Person;

/** Event that is fired when any single racer reaches the finish line. */
public class RacerDoneEvent extends Event {

	private Person person;

	public RacerDoneEvent(Person person) {
		super();
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}
}
