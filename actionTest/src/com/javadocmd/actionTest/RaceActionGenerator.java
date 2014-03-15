package com.javadocmd.actionTest;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.javadocmd.actionTest.Person.JACK;
import static com.javadocmd.actionTest.Person.JANE;

import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.CountdownEventAction;
import com.javadocmd.actionTest.action.FireEventAction;
import com.javadocmd.actionTest.actor.RunnerActor;
import com.javadocmd.actionTest.actor.TrafficLightActor;
import com.javadocmd.actionTest.event.RaceWinEvent;
import com.javadocmd.actionTest.event.RacerDoneEvent;

/**
 * This class is just a place to put the code that generates the main action loop.
 */
public class RaceActionGenerator {

	private static final float DELAY_BEFORE_RACE = 1f;
	private static final float DISTANCE_X = 344f;
	
	private Random random = new Random();
	
	public float randomSpeed() {
		// Random between 1 and 2.
		return random.nextFloat() + 1f;
	}
	
	public void setActions(final Group group, final RunnerActor jane,
			final RunnerActor jack, final TrafficLightActor leftLight,
			final TrafficLightActor rightLight) {
		
		// Notice I'm using "group" as the single point of control for the action sequence.
		group.addAction(forever(sequence(
			// #1: a one second delay
			delay(DELAY_BEFORE_RACE),
			// #2: change the lights and set the movement actions on Jack and Jane
			run(new Runnable() {
				@Override
				public void run() {
					leftLight.setGreen();
					jane.addAction(sequence(
						moveBy(DISTANCE_X, 0, randomSpeed()),
						new FireEventAction(new RacerDoneEvent(JANE))
					));
					jack.addAction(sequence(
						moveBy(DISTANCE_X, 0, randomSpeed()),
						new FireEventAction(new RacerDoneEvent(JACK))
					));
				}
			}),
			// #3: use a CountdownEventAction to wait for the end of the race
			new CountdownEventAction<RaceWinEvent>(RaceWinEvent.class, 1),
			// #4: change the lights and turn Jack and Jane around so they can run the next race
			run(new Runnable() {
				@Override
				public void run() {
					leftLight.setRed();
					rightLight.setYellow();
					jane.turn();
					jack.turn();
				}
			}),
			// (Start of next race, now we're going in the other direction)
			delay(DELAY_BEFORE_RACE),
			run(new Runnable() {
				@Override
				public void run() {
					rightLight.setGreen();
					jane.addAction(sequence(
						moveBy(-DISTANCE_X, 0, randomSpeed()),
						new FireEventAction(new RacerDoneEvent(JANE))
					));
					jack.addAction(sequence(
						moveBy(-DISTANCE_X, 0, randomSpeed()),
						new FireEventAction(new RacerDoneEvent(JACK))
					));
				}
			}),
			new CountdownEventAction<RaceWinEvent>(RaceWinEvent.class, 1),
			run(new Runnable() {
				@Override
				public void run() {
					rightLight.setRed();
					leftLight.setYellow();
					jane.turn();
					jack.turn();
				}
			})
		)));
		
	}
}
