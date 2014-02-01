package com.javadocmd.actionTest.event;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * A listener that waits for a RaceWinEvent and then updates the score board display.
 * It's a bit sloppy to actually store the score here, but that's okay. 
 */
public class ScoreBoardListener implements EventListener {

	public static final String SCORE_BOARD_TEXT = "Jack: %d | Jane: %d";
	
	private Label scoreBoard;
	private int jackScore = 0;
	private int janeScore = 0;
	
	public ScoreBoardListener(Label scoreBoard) {
		this.scoreBoard = scoreBoard;
	}
	
	@Override
	public boolean handle(Event event) {
		if (event instanceof RaceWinEvent) {
			// Update score.
			RaceWinEvent rwe = (RaceWinEvent) event;
			switch (rwe.getWinner()) {
				case JACK :
					jackScore += 1;
					break;
				case JANE :
					janeScore += 1;
					break;
			}
			scoreBoard.setText(String.format(SCORE_BOARD_TEXT, jackScore, janeScore));
			return true;
		}
		return false;
	}
}
