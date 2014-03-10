package com.javadocmd.actionTest.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/** One of the racers: Jack or Jane. */
public class RunnerActor extends Actor {

	private float stateTime = 0;
	private boolean facingRight = true;
	private Animation animation;

	public RunnerActor(Animation animation) {
		super();
		this.animation = animation;
		TextureRegion texture = this.animation.getKeyFrame(stateTime);
		float w = texture.getRegionWidth();
		float h = texture.getRegionHeight();
		this.setSize(w, h);
	}

	public void turn() {
		this.facingRight = !facingRight;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		stateTime += delta;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		TextureRegion texture = animation.getKeyFrame(stateTime, true);
		float scaleX = (facingRight) ? 1 : -1;
		float x = (facingRight) ? getX() : getX() + getWidth();
		batch.draw(texture, x, getY(), 0, 0, getWidth(), getHeight(), scaleX,
				1, 0);
	}
}
