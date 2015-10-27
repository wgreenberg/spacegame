package com.spacegame.screen;

import java.lang.System;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.spacegame.game.SpaceGame;

public class SpaceScreen extends ScreenAdapter {
    SpaceGame game;
    OrthographicCamera camera;

    public SpaceScreen (SpaceGame game) {
        this.game = game;

        camera = new OrthographicCamera(500, 500);
    }

	@Override
	public void resize (int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
	}

	@Override
	public void render (float deltaTime) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
	}
}
