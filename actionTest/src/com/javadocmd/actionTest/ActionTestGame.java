package com.javadocmd.actionTest;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.javadocmd.actionTest.actor.RunnerActor;
import com.javadocmd.actionTest.actor.TrafficLightActor;
import com.javadocmd.actionTest.event.FinishLineListener;
import com.javadocmd.actionTest.event.ScoreBoardListener;
import com.javadocmd.actionTest.util.EscapeToExitListener;

/** The game screen. */
public class ActionTestGame implements ApplicationListener {
	
	private Stage stage;
	private Texture runnersTexture, lightsTexture;
	
	private Group group;
	private RunnerActor jack, jane;
	private TrafficLightActor leftLight, rightLight;
	private Label scoreBoard;
	
	@Override
	public void create() {
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addListener(new EscapeToExitListener());
		Gdx.input.setInputProcessor(stage);
		
		runnersTexture = new Texture(Gdx.files.internal("data/16x24rpgdb.png"));
		runnersTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] frames = TextureRegion.split(runnersTexture, 16, 24);
		TextureRegion[] jackFrames = new TextureRegion[] { frames[0][0], frames[1][0] };
		TextureRegion[] janeFrames = new TextureRegion[] { frames[0][1], frames[1][1] };
		
		lightsTexture = new Texture(Gdx.files.internal("data/traffic-lights.png"));
		lightsTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] lightsGrid = TextureRegion.split(lightsTexture, 17, 48);
		TextureRegion[] lights = new TextureRegion[] { lightsGrid[0][0], lightsGrid[0][1], lightsGrid[0][2] };
		
		group = new Group();
		group.setPosition(0, 0);
		group.addListener(new FinishLineListener(group));
		
		jack = new RunnerActor(new Animation(0.5f, jackFrames));
		jack.setPosition(20, 142);
		
		jane = new RunnerActor(new Animation(0.5f, janeFrames));
		jane.setPosition(20, 42);
		
		leftLight = new TrafficLightActor(lights);
		leftLight.setPosition(19, 81);
		
		rightLight = new TrafficLightActor(lights);
		rightLight.setPosition(364, 81);
		
		Skin defaultSkin = new Skin(Gdx.files.internal("data/uiskin.json"));
		scoreBoard = new Label(String.format(ScoreBoardListener.SCORE_BOARD_TEXT, 0, 0), defaultSkin);
		scoreBoard.setPosition(200, 10);
		stage.addListener(new ScoreBoardListener(scoreBoard));
		
		stage.addActor(group);
		group.addActor(scoreBoard);
		group.addActor(jack);
		group.addActor(jane);
		group.addActor(leftLight);
		group.addActor(rightLight);
		
		/* We've created all our actors, loaded all our resources, and set up our Scene2D Stage.
		 * Now all that's left is to use our action generator to start the main action loop
		 * that runs our races.
		 */
		leftLight.setYellow();
		new RaceActionGenerator().setActions(group, jane, jack, leftLight, rightLight);
	}

	@Override
	public void dispose() {
		stage.dispose();
		runnersTexture.dispose();
		lightsTexture.dispose();
	}
	
	@Override
	public void render() {		
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
