package com.spacegame.screen;

import java.lang.System;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.ashley.core.PooledEngine;

import com.spacegame.game.SpaceGame;
import com.spacegame.game.Universe;
import com.spacegame.systems.MovementSystem;
import com.spacegame.systems.RenderingSystem;

public class SpaceScreen extends ScreenAdapter {
    SpaceGame game;
    Universe universe;

    public SpaceScreen (SpaceGame game) {
        this.game = game;

        PooledEngine engine = new PooledEngine();
        engine.addSystem(new MovementSystem());
        engine.addSystem(new RenderingSystem(game.batch));

        this.universe = new Universe(engine);
        this.universe.create();
    }

	@Override
	public void render (float deltaTime) {
		this.universe.update(deltaTime);
	}
}
