package com.javadocmd.actionTest.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/** A traffic light. Has three modes: green, yellow, or red. */
public class TrafficLightActor extends Actor {

	private TextureRegion[] lights;
	private int lightIndex = 2;
	
	public TrafficLightActor(TextureRegion[] lights) {
		this.lights = lights;
	}
	
	public void setGreen() {
		lightIndex = 0;
	}
	
	public void setYellow() {
		lightIndex = 1;
	}
	
	public void setRed() {
		lightIndex = 2;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(lights[lightIndex], getX(), getY());
	}
}
