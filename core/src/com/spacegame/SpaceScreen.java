package com.spacegame.screen;

import java.lang.System;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.ashley.core.PooledEngine;

import com.spacegame.game.SpaceGame;
import com.spacegame.game.Universe;
import com.spacegame.systems.MovementSystem;
import com.spacegame.systems.RenderingSystem;
import com.spacegame.systems.CameraSystem;

public class SpaceScreen extends ScreenAdapter {
    SpaceGame game;
    Universe universe;
    OrthographicCamera camera;

    public SpaceScreen (SpaceGame game) {
        this.game = game;

        camera = new OrthographicCamera(500, 500);

        PooledEngine engine = new PooledEngine();
        engine.addSystem(new MovementSystem());
        engine.addSystem(new RenderingSystem(game.batch));
        //engine.addSystem(new CameraSystem(camera));

        universe = new Universe(engine);
        universe.create();
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
		universe.update(deltaTime);
	}
}
