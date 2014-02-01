package com.javadocmd.actionTest.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/** Simple listener that quits when the escape key is pressed. */
public class EscapeToExitListener extends InputListener {
	
	public EscapeToExitListener() {
		
	}
	
	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		return (keycode == Input.Keys.ESCAPE);
	}

	@Override
	public boolean keyUp(InputEvent event, int keycode) {
		if (keycode == Input.Keys.ESCAPE) {
			Gdx.app.exit();
			return true;
		}
		return false;
	}
}
