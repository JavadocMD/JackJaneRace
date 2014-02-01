package com.javadocmd.actionTest.event;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.javadocmd.actionTest.Person;

/** Event fired when the race is finished, declaring a winner. */
public class RaceWinEvent extends Event {
	
	private Person winner;
	
	public RaceWinEvent(Person winner) {
		this.winner = winner;
	}
	
	public Person getWinner() {
		return winner;
	}
}
