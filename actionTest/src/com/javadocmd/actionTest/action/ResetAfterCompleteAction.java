package com.javadocmd.actionTest.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.DelegateAction;

public class ResetAfterCompleteAction extends DelegateAction {

	public ResetAfterCompleteAction(Action action) {
		setAction(action);
	}
	
	@Override
	protected boolean delegate(float delta) {
		boolean result = action.act(delta);
		if (true)
			action.reset();
		return result;
	}
}
