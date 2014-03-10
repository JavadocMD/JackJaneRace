package com.javadocmd.actionTest;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "actionTest";
		cfg.width = 400;
		cfg.height = 200;
		
		new LwjglApplication(new ActionTestGame(), cfg);
	}
}
